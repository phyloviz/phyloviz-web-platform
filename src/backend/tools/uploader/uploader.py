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


def tree_handler(s3_output_path, project_id, dataset_id, tree_id):
    trees_collection = db['trees']

    # Tree should already be indexed, so its metadata exists
    # Add S3 repositorySpecificData to the metadata of the tree
    trees_collection.update_one(
        {
            'projectId': project_id,
            'datasetId': dataset_id,
            'treeId': tree_id
        },
        {'$set': {
            'repositorySpecificData.s3': {
                'url': f'http://localhost:9444/{bucket_name}{s3_output_path}'
            }
        }}
    )

    print(f'File uploaded to S3 and tree metadata updated of tree with treeId: {tree_id}')


def distance_matrix_handler(s3_output_path, project_id, dataset_id, workflow_id, distance_matrix_id, source_type,
                            function):
    distance_matrix_collection = db['distance-matrices']

    if source_type == 'function':
        source = {
            'function': function
        }
        distance_matrix_count = distance_matrix_collection.count_documents(
            {"projectId": project_id, "datasetId": dataset_id})
        name = f'Distance Matrix {distance_matrix_count + 1} - {function}'
    else:
        raise Exception(f'Unknown source type: {source_type}')

    distance_matrix_metadata = {
        'projectId': project_id,
        'datasetId': dataset_id,
        'distanceMatrixId': distance_matrix_id,
        'name': name,
        'sourceType': source_type,
        'source': source,
        'repositorySpecificData': {
            's3': {
                'url': f'http://localhost:9444/{bucket_name}{s3_output_path}'
            }
        }
    }

    distance_matrix_collection.insert_one(distance_matrix_metadata)

    workflows_collection.update_one(
        {'_id': ObjectId(workflow_id)},
        {'$set': {
            'data.distanceMatrixId': distance_matrix_id
        }}
    )

    print(
        f'File uploaded to S3 and metadata created in the distance matrix collection with distanceMatrixId: {distance_matrix_id}')


resource_type_collection_map = {
    "tree": "trees",
    "distance-matrix": "distance-matrices",
}


def get_collection_from_resource_type(resource_type):
    return resource_type_collection_map[resource_type]


def upload_file_to_s3(file_path, project_id, dataset_id, workflow_id, resource_id, resource_type, source_type,
                      algorithm, distance_matrix_id, parameters, function):
    print(f"Project ID: {project_id}")
    print(f"Dataset ID: {dataset_id}")
    print(f"Resource ID: {resource_id}")
    print(f"Resource Type: {resource_type}")
    print(f"File path: {file_path}")
    print(f"Workflow ID: {workflow_id}")

    # Get or generate resource_id if not provided
    if not resource_id:
        if source_type == 'tree':
            # If tree_id is not provided, get it from the workflow
            workflow = workflows_collection.find_one({'_id': ObjectId(workflow_id)})
            resource_id = workflow['data'].get('treeId')
            if resource_id is None:
                raise Exception(f"Workflow with ID {workflow_id} does not have a tree associated with it")
        else:
            resource_id = str(uuid.uuid4())

    collection_name = get_collection_from_resource_type(resource_type)

    s3_output_path = f'/{project_id}/{collection_name}/{resource_id}'
    # Upload the file to S3
    with open(file_path, 'rb') as file:
        s3.upload_fileobj(file, bucket_name, s3_output_path)

    if resource_type == "tree":
        tree_id = resource_id
        tree_handler(s3_output_path, project_id, dataset_id, tree_id)
    elif resource_type == "distance-matrix":
        distance_matrix_id = resource_id
        distance_matrix_handler(s3_output_path, project_id, dataset_id, workflow_id, distance_matrix_id, source_type,
                                function)
    else:
        raise Exception(f'Unknown resource type: {resource_type}')


if __name__ == '__main__':
    parser = argparse.ArgumentParser(
        description='Upload a file to Amazon S3 and update a metadata in MongoDB.')
    parser.add_argument('--file-path', help='The file path', required=True)
    parser.add_argument('--project-id', help='The project id', required=True)
    parser.add_argument('--dataset-id', help='The dataset id', required=True)
    parser.add_argument('--workflow-id', help='The workflow ID to update in MongoDB', required=True)
    parser.add_argument('--resource-type', help='The resource type', required=True)
    parser.add_argument('--resource-id', help='The optional ID of the resource', default=None)
    parser.add_argument('--source-type', help='The source type', required=False)
    parser.add_argument('--algorithm', help='The algorithm', required=False)
    parser.add_argument('--distance-matrix-id', help='The distance matrix id', required=False)
    parser.add_argument('--parameters', help='The parameters of the algorithm', required=False)
    parser.add_argument('--function', help='The function', required=False)
    args = parser.parse_args()

    upload_file_to_s3(args.file_path, args.project_id, args.dataset_id, args.workflow_id, args.resource_id,
                      args.resource_type, args.source_type, args.algorithm, args.distance_matrix_id,
                      args.parameters, args.function)
