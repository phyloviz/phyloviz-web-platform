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
workflows_collection = db['workflow-instances']
typing_data_collection = db['typing-data']
isolate_data_collection = db['isolate-data']
datasets_collection = db['datasets']
projects_collection = db['projects']


def upload_isolates(project_id, dataset_id, file):
    files = {
        'file': open(file, 'rb')
    }

    response = requests.request(
        "POST", f"{base_url}/projects/{project_id}/datasets/{dataset_id}/isolates/files", files=files)

    if response.status_code not in range(200, 299):
        raise Exception(
            f"Isolates upload failed with status_code {response.status_code} \n{response.text}")

    return response.json()


def project_exists(project_id):
    response = requests.request(
        "GET", f"{base_url}/projects/{project_id}", headers=json_headers)

    if response.status_code == 404:
        return False

    if response.status_code not in range(200, 299):
        raise Exception(f"Project get failed with status_code {response.status_code} \n{response.text}")

    return True


def dataset_exists(project_id, dataset_id):
    response = requests.request(
        "GET", f"{base_url}/projects/{project_id}/datasets/{dataset_id}", headers=json_headers)

    if response.status_code == 404:
        return False

    if response.status_code not in range(200, 299):
        raise Exception(
            f"Dataset get failed with status_code {response.status_code} \n{response.text}")

    return True


def index_isolate_data(isolate_data_file_path, project_id, dataset_id, workflow_id):
    print(f"Project ID: {project_id}")
    print(f"Dataset ID: {dataset_id}")
    print(f"Isolate data file path: {isolate_data_file_path}")
    print(f"Workflow ID: {workflow_id}")

    if projects_collection.find_one({'_id': ObjectId(project_id)}) is None:
        raise Exception(f"Project with ID {project_id} does not exist in PHYLOViZ Web Platform")

    dataset_metadata = datasets_collection.find_one({'_id': ObjectId(dataset_id), 'projectId': project_id})
    if dataset_metadata is None:
        raise Exception(
            f"Dataset with ID {dataset_id} and Project ID {project_id} does not exist in PHYLOViZ Web Platform")

    isolate_data_id = dataset_metadata.get('isolateDataId')
    if isolate_data_id is None:
        raise Exception(
            f"Dataset with ID {dataset_id} does not have an associated Isolate Data in PHYLOViZ Web Platform")

    if typing_data_collection.find_one(
            {
                'typingDataId': dataset_metadata['typingDataId'],
                'repositorySpecificData.phylodb.datasetIds': {'$in': [dataset_id]}
            }
    ) is None:
        raise Exception(
            f"Dataset with ID {dataset_id} does not have Typing Data indexed in PhyloDB. Indexing of Typing Data required")

    if isolate_data_collection.find_one(
            {
                'isolateDataId': isolate_data_id,
                'repositorySpecificData.phylodb.datasetIds': {'$in': [dataset_id]}
            }
    ) is not None:
        raise Exception(
            f"Dataset with ID {dataset_id} already has Isolate Data indexed in PhyloDB. No need to index again")

    if not project_exists(project_id):
        raise Exception(f"Project with ID {project_id} does not exist in PhyloDB yet. Indexing of Typing Data required")

    if not dataset_exists(project_id, dataset_id):
        raise Exception(f"Dataset with ID {dataset_id} does not exist in PhyloDB yet. Indexing of Typing Data required")

    upload_isolates(project_id, dataset_id, isolate_data_file_path)
    print("Done uploading isolates to dataset in PhyloDB")

    isolate_data_metadata = isolate_data_collection.find_one(
        {
            'isolateDataId': isolate_data_id,
            'repositorySpecificData.phylodb': {'$exists': True}
        }
    )

    if isolate_data_metadata is None:
        isolate_data_collection.update_one(
            {'isolateDataId': isolate_data_id},
            {'$set': {
                'repositorySpecificData.phylodb': {
                    'projectId': project_id,
                    'datasetIds': [dataset_id]
                }
            }}
        )
    else:
        isolate_data_collection.update_one(
            {'isolateDataId': isolate_data_id},
            {'$push': {
                'repositorySpecificData.phylodb.datasetIds': dataset_id
            }}
        )


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Index isolate data into PHYLODB')
    parser.add_argument('--isolate-data-file-path', help='The isolate data file path', required=True)
    parser.add_argument('--project-id', help='The project Id', required=True)
    parser.add_argument('--dataset-id', help='The dataset Id', required=True)
    parser.add_argument('--workflow-id', help='The workflow Id', required=True)

    args = parser.parse_args()
    index_isolate_data(args.isolate_data_file_path, args.project_id, args.dataset_id, args.workflow_id)
