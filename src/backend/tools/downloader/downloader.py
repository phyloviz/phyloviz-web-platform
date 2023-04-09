import argparse
import boto3
import configparser
import os
from botocore.exceptions import NoCredentialsError, ClientError
from pymongo import MongoClient
from urllib.parse import urlparse

resource_type_collection_map = {
    "tree": "trees",
    "distance-matrix": "distance-matrices",
    "typing-data": "typing-data",
    "isolate-data": "isolate-data",
}

resource_type_id_attr_map = {
    "tree": "treeId",
    "distance-matrix": "distanceMatrixId",
    "typing-data": "typingDataId",
    "isolate-data": "isolateDataId",
}


def get_collection_from_resource_type(resource_type):
    return resource_type_collection_map[resource_type]


def get_resource_id_attr_from_resource_type(resource_type):
    return resource_type_id_attr_map[resource_type]


def download_s3_object(resource_id, resource_type, out):
    print(f'Downloading {resource_id} from {resource_type} to {out}...')
    mongo_uri = os.environ['MONGO_URI']
    mongo_client = MongoClient(mongo_uri)
    db = mongo_client['phyloviz-web-platform']
    resource_type_collection = db[get_collection_from_resource_type(resource_type)]

    resource = resource_type_collection.find_one(
        {get_resource_id_attr_from_resource_type(resource_type): resource_id, 'adapterId': 's3'})

    s3_url = resource['url']

    # Get the S3 URL from the resourceType collection
    # Parse the S3 URL
    parsed_url = urlparse(s3_url)
    path = parsed_url.path.lstrip('/')
    bucket_name = path.split('/')[0]
    object_key = path.lstrip(f'{bucket_name}')

    print("Retrieving S3 object from bucket: " + bucket_name + " and key: " + object_key)
    # Obtain endpoint url from ~/.aws/config
    # Read the custom endpoint URL from the config file
    config = configparser.ConfigParser()
    config.read(os.path.expanduser('~/.aws/config'))

    custom_endpoint_url = config.get('default', 'endpoint_url')

    # Create an S3 client
    s3 = boto3.client('s3', endpoint_url=custom_endpoint_url)

    try:
        s3.download_file(bucket_name, object_key, out)
        print(f'Successfully downloaded {s3_url} to {out}')
    except NoCredentialsError:
        print('Error: AWS credentials not found')
    except ClientError as e:
        print(f'Error downloading the file: {e}')


if __name__ == '__main__':
    # Make it so the script always starts with download  argument
    parser = argparse.ArgumentParser(description='Download an S3 object to a specific folder')
    parser.add_argument('--out', help='The output file path', required=True)
    parser.add_argument(
        '--resource-id', help='The ID of the resource', required=True)
    parser.add_argument('--resource-type', help='The resource type', required=True)

    args = parser.parse_args()
    download_s3_object(args.resource_id, args.resource_type, args.out)
