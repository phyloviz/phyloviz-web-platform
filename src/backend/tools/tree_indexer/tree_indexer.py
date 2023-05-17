import argparse
import os
import requests
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


def index_tree(tree_file_path, project_id, dataset_id, tree_id, workflow_id):
    print(f"Project ID: {project_id}")
    print(f"Dataset ID: {dataset_id}")
    print(f"Tree ID: {tree_id}")
    print(f"Tree File Path: {tree_file_path}")
    print(f"Workflow ID: {workflow_id}")

    if projects_collection.find_one({'_id': ObjectId(project_id)}) is None:
        raise Exception(f"Project with ID {project_id} does not exist in PHYLOViZ Web Platform")

    dataset_metadata = datasets_collection.find_one({'_id': ObjectId(dataset_id), 'projectId': project_id})
    if dataset_metadata is None:
        raise Exception(
            f"Dataset with ID {dataset_id} and Project ID {project_id} does not exist in PHYLOViZ Web Platform")

    tree_metadata = trees_collection.find_one(
        {
            'projectId': project_id,
            'datasetId': dataset_id,
            'treeId': tree_id
        }
    )

    if tree_metadata is None:
        raise Exception(f"Tree with ID {tree_id} does not exist in PHYLOViZ Web Platform")

    if tree_metadata["repositorySpecificData"].get("phylodb") is not None:
        raise Exception(f"Tree with ID {tree_id} is already indexed in PhyloDB")

    if typing_data_collection.find_one(
            {
                'typingDataId': dataset_metadata['typingDataId'],
                'repositorySpecificData.phylodb.datasetIds': {'$in': [dataset_id]}
            }
    ) is None:
        raise Exception(f"Dataset with ID {dataset_id} does not have Typing Data indexed in PhyloDB. Index it first.")

    inference_id = upload_inference(project_id, dataset_id, tree_file_path)
    print("Done uploading inference")

    # Add repositorySpecificData to the metadata of the tree
    trees_collection.update_one(
        {'treeId': tree_id},
        {'$set': {
            'repositorySpecificData.phylodb': {
                'projectId': project_id,
                'datasetId': dataset_id,
                'inferenceId': inference_id,
            }
        }}
    )


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Index tree into PHYLODB')
    parser.add_argument('--tree-file-path', help='The tree file path', required=True)
    parser.add_argument('--project-id', help='The project Id', required=True)
    parser.add_argument('--dataset-id', help='The dataset Id', required=True)
    parser.add_argument('--tree-id', help='The tree Id', required=True)
    parser.add_argument('--workflow-id', help='The workflow Id', required=True)

    args = parser.parse_args()
    index_tree(args.tree_file_path, args.project_id, args.dataset_id, args.tree_id, args.workflow_id)
