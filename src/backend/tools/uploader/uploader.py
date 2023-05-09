import argparse
import boto3
import configparser
import os
import uuid
from bson.objectid import ObjectId
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
workflows_collection = db['workflow-instances']
datasets_collection = db['datasets']


def tree_handler(s3_output_path, project_id, dataset_id, tree_id, workflow_id, source_type):
    tree_collection = db['trees']

    workflows_collection.update_one(
        {'_id': ObjectId(workflow_id)},
        {'$set': {
            'data.treeId': tree_id
        }})

    workflow = workflows_collection.find_one({'_id': ObjectId(workflow_id)})

    source = {
        'algorithm': workflow['data']['algorithm'],
        'distanceMatrixId': workflow['data']['distanceMatrixId'],
        'parameters': workflow['data']['parameters']
    } if source_type == 'algorithm_distance_matrix' else {
        'algorithm': workflow['data']['algorithm'],
        'typingDataId': workflow['data']['typingDataId'],
        'parameters': workflow['data']['parameters']
    } if source_type == 'algorithm_typing_data'

    tree_metadata = {
        'treeId': tree_id,
        'projectId': project_id,
        'datasetId': dataset_id,
        'name': f'Tree {tree_id}',
        'sourceType': source_type,
        'source': source,
        'repositoryId': 's3',
        'repositorySpecificData': {
            'url': f'http://localhost:9444/{bucket_name}{s3_output_path}',
        }
    }

    tree_collection.insert_one(tree_metadata)

    print(f'File uploaded to S3 and metadata created in the tree collection with treeId: {tree_id}')


def distance_matrix_handler(s3_output_path, project_id, dataset_id, distance_matrix_id, workflow_id):
    distance_matrix_collection = db['distance-matrices']

    workflows_collection.update_one(
        {'_id': ObjectId(workflow_id)},
        {'$set': {
            'data.distanceMatrixId': distance_matrix_id
        }})

    workflow = workflows_collection.find_one({'_id': ObjectId(workflow_id)})

    distance_matrix_metadata = {
        'projectId': project_id,
        'datasetId': dataset_id,
        'distanceMatrixId': distance_matrix_id,
        'repositoryId': 's3',
        'name': f'Distance Matrix {distance_matrix_id}',
        'sourceType': 'function',
        'source': {
            'function': workflow['data']['function']
        },
        'repositorySpecificData': {
            'url': f'http://localhost:9444/{bucket_name}{s3_output_path}',
        }
    }

    distance_matrix_collection.insert_one(distance_matrix_metadata)

    print(
        f'File uploaded to S3 and metadata created in the distance matrix collection with distanceMatrixId: {distance_matrix_id}')


handler_map = {
    "tree": tree_handler,
    "distance-matrix": distance_matrix_handler,
}

resource_type_collection_map = {
    "tree": "trees",
    "distance-matrix": "distance-matrices",
}


def get_collection_from_resource_type(resource_type):
    return resource_type_collection_map[resource_type]


def upload_file_to_s3(file_path, project_id, dataset_id, resource_id, resource_type, workflow_id, source_type):
    # Generate a unique resource_id if not provided
    if not resource_id:
        resource_id = str(uuid.uuid4())

    collection_name = get_collection_from_resource_type(resource_type)

    s3_output_path = f'/{project_id}/{collection_name}/{resource_id}'
    # Upload the file to S3
    with open(file_path, 'rb') as file:
        s3.upload_fileobj(file, bucket_name, s3_output_path)

    handler_map[resource_type](s3_output_path, project_id, dataset_id, resource_id, workflow_id, source_type)


if __name__ == '__main__':
    parser = argparse.ArgumentParser(
        description='Upload a file to Amazon S3 and update a workflow in MongoDB.')
    parser.add_argument('--file-path', help='The file path', required=True)
    parser.add_argument('--project-id', help='The project id', required=True)
    parser.add_argument('--dataset-id', help='The dataset id', required=True)
    parser.add_argument(
        '--workflow-id', help='The workflow ID to update in MongoDB', required=True)
    parser.add_argument(
        '--resource-id', help='The optional ID of the resource', default=None)
    parser.add_argument('--resource-type', help='The resource type', required=True)
    parser.add_argument('--source-type', help='The source type', required=False)
    args = parser.parse_args()

    upload_file_to_s3(args.file_path, args.project_id, args.dataset_id, args.resource_id, args.resource_type,
                      args.workflow_id, args.source_type)
