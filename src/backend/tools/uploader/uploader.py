import argparse
import os
import uuid

import boto3
from pymongo import MongoClient
import configparser

def upload_file_to_s3(file_path, project_id, resource_id, resource_type, workflow_id):
    # Obtain endpoint url from ~/.aws/config
    # Read the custom endpoint URL from the config file
    config = configparser.ConfigParser()
    config.read(os.path.expanduser('~/.aws/config'))

    custom_endpoint_url = config.get('default', 'endpoint_url')

    # Create an S3 client
    s3 = boto3.client('s3', endpoint_url=custom_endpoint_url)

    bucket_name = os.environ['S3_BUCKET_NAME']

    # Generate a unique resource_id if not provided
    if not resource_id:
        resource_id = str(uuid.uuid4())

    s3_output_path = f'{project_id}/{workflow_id}/{resource_id}'
    # Upload the file to S3
    with open(file_path, 'rb') as file:
        s3.upload_fileobj(file, bucket_name, s3_output_path)

    # TODO: Maybe move this code into a separate tool
    # Connect to MongoDB
    mongo_uri = os.environ['MONGO_URI']
    client = MongoClient(mongo_uri)
    db = client['your_database']
    workflows_collection = db['workflows']
    resource_type_collection = db[resource_type]

    resource_type = resource_type.replace('-', ' ').title().replace(' ', '')
    resource_type = resource_type[0].lower() + resource_type[1:]

    # Update the workflow with the resource_id
    workflows_collection.update_one(
        {'_id': workflow_id},
        {'$set': {
            'data': {
                f'{resource_type}Id': resource_id
            }
        }})

    # Create the metadata in the resourceType collection
    resource_metadata = {
        'url': s3_output_path,
        'resourceId': resource_id
    }

    resource_type_collection.insert_one(resource_metadata)

    print(
        f'File uploaded to S3 and metadata created in the {resource_type} collection with resource_id: {resource_id}')


if __name__ == '__main__':
    parser = argparse.ArgumentParser(
        description='Upload a file to Amazon S3 and update a workflow in MongoDB.')
    parser.add_argument('--file-path', help='The file path', required=True)
    parser.add_argument('--project-id', help='The project id', required=True)
    parser.add_argument(
        '--workflow-id', help='The workflow ID to update in MongoDB', required=True)
    parser.add_argument(
        '--resource-id', help='The optional ID of the resource', default=None)
    parser.add_argument('--resource-type', help='The resource type', required=True)
    args = parser.parse_args()

    upload_file_to_s3(args.file_path, args.project_id, args.resource_id, args.resource_type, args.workflow_id)
