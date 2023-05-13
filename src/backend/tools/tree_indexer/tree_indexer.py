import argparse
import os
import requests
from bson.objectid import ObjectId
from pymongo import MongoClient

base_url = os.environ.get("PHYLODB_URL")

json_headers = {
    'Content-Type': 'application/json'
}


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
    inference_id = upload_inference(project_id, dataset_id, tree_file_path)
    print("Done uploading inference")

    # Connect to MongoDB
    mongo_uri = os.environ['MONGO_URI']
    client = MongoClient(mongo_uri)
    db = client['phyloviz-web-platform']
    trees_collection = db['trees']

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
