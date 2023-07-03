import argparse
import os
import requests
import uuid
from bson.objectid import ObjectId
from pymongo import MongoClient

base_url = os.environ.get("PHYLODB_URL")

json_headers = {
    'Content-Type': 'application/json'
}

# Connect to MongoDB
mongo_uri = os.environ['MONGO_URI']
client = MongoClient(mongo_uri)
db = client['phyloviz-web-platform']
trees_collection = db['trees']
datasets_collection = db['datasets']
projects_collection = db['projects']
typing_data_collection = db['typing-data']
workflows_collection = db['workflow-instances']


def upload_inference(project_id, dataset_id, inference_file_path):
    files = {
        'file': open(inference_file_path, 'rb'),
        'algorithm': 'goeburst',
        'format': 'newick',
    }

    response = requests.request(
        "POST", f"{base_url}/projects/{project_id}/datasets/{dataset_id}/inferences", files=files)

    if response.status_code not in range(200, 299):
        raise Exception(
            f"Inference upload failed with status_code {response.status_code} \n{response.text}")

    return response.json()["id"]


def index_tree(tree_file_path, project_id, dataset_id, workflow_id, source_type, algorithm, distance_matrix_id, parameters):
    # Generate a new tree ID
    tree_id = str(uuid.uuid4())

    print(f"Project ID: {project_id}")
    print(f"Dataset ID: {dataset_id}")
    print(f"Tree ID: {tree_id}")
    print(f"Tree File Path: {tree_file_path}")
    print(f"Workflow ID: {workflow_id}")

    # Project and Dataset Validation
    if projects_collection.find_one({'_id': ObjectId(project_id)}) is None:
        raise Exception(f"Project with ID {project_id} does not exist in PHYLOViZ Web Platform")

    dataset_metadata = datasets_collection.find_one({'_id': ObjectId(dataset_id), 'projectId': project_id})
    if dataset_metadata is None:
        raise Exception(
            f"Dataset with ID {dataset_id} and Project ID {project_id} does not exist in PHYLOViZ Web Platform")

    if typing_data_collection.find_one(
            {
                'typingDataId': dataset_metadata['typingDataId'],
                'repositorySpecificData.phylodb.datasetIds': {'$in': [dataset_id]}
            }
    ) is None:
        raise Exception(f"Dataset with ID {dataset_id} does not have Typing Data indexed in PhyloDB. Index it first.")

    # Validate source and generate information to put in metadata document
    if source_type == 'algorithm-distance-matrix':
        source_type = 'algorithm_distance_matrix'

        if distance_matrix_id is None:
            # Get the distance matrix ID from the workflow instance
            workflow_instance = workflows_collection.find_one(
                {
                    '_id': ObjectId(workflow_id)
                }
            )

            if workflow_instance is None:
                raise Exception(f'Workflow instance with ID {workflow_id} not found')

            distance_matrix_id = workflow_instance.get('data').get('distanceMatrixId')

            if distance_matrix_id is None:
                raise Exception('Distance matrix ID is required when source type is algorithm-distance-matrix')

        source = {
            'algorithm': algorithm,
            'distanceMatrixId': distance_matrix_id,
            'parameters': parameters
        }
        tree_count = trees_collection.count_documents({"projectId": project_id, "datasetId": dataset_id})
        name = f'Tree {tree_count + 1} - {algorithm}'
    elif source_type == 'algorithm-typing-data':
        source_type = 'algorithm_typing_data'
        dataset = datasets_collection.find_one(
            {
                '_id': ObjectId(dataset_id),
                'projectId': project_id,
            }
        )

        if dataset is None:
            raise Exception(f'Dataset with Project ID {project_id} and Dataset ID {dataset_id} not found')

        source = {
            'algorithm': algorithm,
            'typingDataId': dataset['typingDataId'],
            'parameters': parameters
        }
        tree_count = trees_collection.count_documents({"projectId": project_id, "datasetId": dataset_id})
        name = f'Tree {tree_count + 1} - {algorithm}'
    else:
        raise Exception(f'Unknown source type: {source_type}')

    # Upload inference
    inference_id = upload_inference(project_id, dataset_id, tree_file_path)
    print("Done uploading inference")

    # Create tree metadata
    tree_metadata = {
        'treeId': tree_id,
        'projectId': project_id,
        'datasetId': dataset_id,
        'name': name,
        'sourceType': source_type,
        'source': source,
        'repositorySpecificData': {
            'phylodb': {
                'projectId': project_id,
                'datasetId': dataset_id,
                'inferenceId': inference_id,
            }
        }
    }

    trees_collection.insert_one(tree_metadata)

    # Update workflow instance with tree ID
    workflows_collection.update_one(
        {'_id': ObjectId(workflow_id)},
        {'$set': {
            'data.treeId': tree_id
        }}
    )


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Index tree into PHYLODB')
    parser.add_argument('--tree-file-path', help='The tree file path', required=True)
    parser.add_argument('--project-id', help='The project Id', required=True)
    parser.add_argument('--dataset-id', help='The dataset Id', required=True)
    parser.add_argument('--workflow-id', help='The workflow Id', required=True)
    parser.add_argument('--source-type', help='The source type', required=True)
    parser.add_argument('--algorithm', help='The algorithm', required=False)
    parser.add_argument('--distance-matrix-id', help='The distance matrix id', required=False)
    parser.add_argument('--parameters', help='The parameters of the algorithm', required=False)

    args = parser.parse_args()
    index_tree(args.tree_file_path, args.project_id, args.dataset_id, args.workflow_id, args.source_type,
               args.algorithm, args.distance_matrix_id, args.parameters)
