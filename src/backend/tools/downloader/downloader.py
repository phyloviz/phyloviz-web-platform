import argparse
import boto3
import configparser
import os
from botocore.exceptions import NoCredentialsError, ClientError
from pymongo import MongoClient
from urllib.parse import urlparse
from bson.objectid import ObjectId

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

# Connect to MongoDB
mongo_uri = os.environ['MONGO_URI']
client = MongoClient(mongo_uri)
db = client['phyloviz-web-platform']
workflows_collection = db['workflow-instances']
datasets_collection = db['datasets']

def get_collection_from_resource_type(resource_type):
    return resource_type_collection_map[resource_type]


def get_resource_id_attr_from_resource_type(resource_type):
    return resource_type_id_attr_map[resource_type]


def download_s3_object(project_id, dataset_id, resource_id, resource_type, out, workflow_id):
    print(f'Downloading {resource_id} from {resource_type} to {out}...')
    resource_type_collection = db[get_collection_from_resource_type(resource_type)]

    if resource_type == 'tree' or resource_type == 'distance-matrix':
        # Obtain the resource metadata with a repositoryId 's3'
            resource = resource_type_collection.find_one(
                {
                'project_Id': project_id,
                'datasetId': dataset_id,
                get_resource_id_attr_from_resource_type(resource_type): resource_id,
                'repositoryId': 's3'
                }
            )
    elif resource_type == 'typing-data' or resource_type == 'isolate-data':
        dataset = datasets_collection.find_one(
            {
            'project_Id': project_id,
            'datasetId': dataset_id
            }
        )

        # Obtain the typing data or isolate data id from the dataset
        if resource_type == 'typing-data':
            resource_id = dataset['typingDataId']
        elif resource_type == 'isolate-data':
            resource_id = dataset['isolateDataId']

        # Obtain the resource metadata with a repositoryId 's3'
        resource = resource_type_collection.find_one(
            {
            'project_Id': project_id,
            get_resource_id_attr_from_resource_type(resource_type): resource_id,
            'repositoryId': 's3'
            }
        )

        if resource_type == 'typing-data':
            workflows_collection.update_one(
                {
                    '_id': workflow_id
                },
                {
                    '$set': {
                        'data.typingDataId': dataset['typingDataId'],
                        'data.typingDataType': resource['type']
                    }
                }
            )
        else:
            workflows_collection.update_one(
                {
                    'workflowInstanceId': workflow_id
                },
                {
                    '$set': {
                        'data.isolateDataId': dataset['isolateDataId']
                    }
                }
            )
    else:
        print(f'Error: Invalid resource type: {resource_type}')
        return

    # Obtain the S3 URL from the repositorySpecificData
    s3_url = resource['repositorySpecificData']['url']

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
    parser.add_argument('--project-id', help='The project id', required=True)
    parser.add_argument('--dataset-id', help='The dataset id', required=True)
    parser.add_argument('--resource-id', help='The optional ID of the resource', required=False)
    parser.add_argument('--resource-type', help='The resource type', required=True)
    parser.add_argument('--workflow-id', help='The workflow ID to update in MongoDB', required=True)

    args = parser.parse_args()
    download_s3_object(args.project_id, args.dataset_id, args.resource_id, args.resource_type, args.out,
                        args.workflow_id)
