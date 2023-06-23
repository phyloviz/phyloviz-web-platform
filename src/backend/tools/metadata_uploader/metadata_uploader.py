import argparse
import boto3
import configparser
import os
import uuid
from pymongo import MongoClient

# Obtain endpoint url from ~/.aws/config
# Read the custom endpoint URL from the config file
config = configparser.ConfigParser()
config.read(os.path.expanduser('~/.aws/config'))

custom_endpoint_url = config.get('default', 'endpoint_url')
bucket_name = "phyloviz-web-platform"

# Create an S3 client
s3 = boto3.client('s3', endpoint_url=custom_endpoint_url)

# Connect to MongoDB
mongo_uri = os.environ['MONGO_URI']
client = MongoClient(mongo_uri)
db = client['phyloviz-web-platform']


def create_metadata(original_url, s3_url, type, project_id, workflow_id):
    print(f"Project ID: {project_id}")
    print(f"Workflow ID: {workflow_id}")
    print(f"Original URL: {original_url}")
    print(f"URL: {s3_url}")
    print(f"Type: {type}")

    resource_id = str(uuid.uuid4())

    if type == "typing_data":
        metadata = {
            'projectId': project_id,
            'typingDataId': resource_id,
            'name': f'Typing Data - {original_url}',
            'repositorySpecificData': {
                's3': {
                    'url': s3_url
                }
            }
        }

        db["typing-data"].insert_one(metadata)

        print(
            f'Metadata created in the typing data collection with typingDataId: {resource_id}')

    elif type == "isolate_data":
        object_key = s3_url.split(bucket_name)[-1]

        if object_key[0] == "/":
            object_key = object_key[1:]

        print("Downloading isolate data file to get keys from first line")
        print("Bucket: " + bucket_name)
        print("Key: " + object_key)

        try:
            obj = s3.get_object(
                Bucket=bucket_name,
                Key=object_key
            )
            for el in obj['Body'].iter_lines():
                first_line = el
                break
        except NoSuchKey as e:
            obj = s3.get_object(
                Bucket=bucket_name,
                Key="/" + object_key
            )
            for el in obj['Body'].iter_lines():
                first_line = el
                break

        keys = first_line.decode('utf-8').split('\t')

        metadata = {
            'projectId': project_id,
            'isolateDataId': resource_id,
            'name': f'Isolate Data - {original_url}',
            'keys': keys,

            'repositorySpecificData': {
                's3': {
                    'url': s3_url
                }
            }
        }

        db["isolate-data"].insert_one(metadata)

        print(
            f'Metadata created in the isolate data collection with isolateDataId: {resource_id}')


if __name__ == '__main__':
    parser = argparse.ArgumentParser(
        description='Upload a file to Amazon S3 and create the metadata in MongoDB.')
    parser.add_argument('--original_url', help='The original source url', required=True)
    parser.add_argument('--url', help='The s3 url', required=True)
    parser.add_argument('--type', help='The type of the file (typing_data, isolate_data, etc)', required=True)
    parser.add_argument('--project-id', help='The project id', required=True)
    parser.add_argument('--workflow-id', help='The workflow ID to update in MongoDB', required=True)
    args = parser.parse_args()

    create_metadata(args.original_url, args.url, args.type, args.project_id, args.workflow_id)
