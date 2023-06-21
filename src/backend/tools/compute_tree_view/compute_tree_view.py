import argparse
import os
import requests
import time
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
tree_views_collection = db['tree-views']
trees_collection = db['trees']
datasets_collection = db['datasets']
projects_collection = db['projects']


def create_visualization_job(project_id, dataset_id, inference_id):
    data = {
        "analysis": "visualization",
        "algorithm": "radial",
        "parameters": [dataset_id, inference_id]
    }

    response = requests.request(
        "POST", f"{base_url}/projects/{project_id}/jobs", headers=json_headers, json=data)

    if response.status_code not in range(200, 299):
        raise Exception(
            f"Visualization Job creation failed with status_code {response.status_code} \n{response.text}")

    data = response.json()

    return data["job_id"], data["analysis_id"]


def get_jobs(project_id):
    response = requests.request(
        "GET", f"{base_url}/projects/{project_id}/jobs?provider=google", headers=json_headers)

    if response.status_code not in range(200, 299):
        raise Exception(
            f"Get jobs failed with status_code {response.status_code} \n{response.text}")

    return response.json()


def get_job(project_id, job_id):
    jobs = get_jobs(project_id)
    print(jobs)
    for job in jobs:
        if job["id"] == job_id:
            return job

    return None


def compute_tree_view(project_id, dataset_id, tree_id, workflow_id, layout):
    print(f"Project ID: {project_id}")
    print(f"Dataset ID: {dataset_id}")
    print(f"Tree ID: {tree_id}")
    print(f"Layout: {layout}")
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

    if tree_metadata["repositorySpecificData"].get("phylodb") is None:
        raise Exception(f"Tree with ID {tree_id} is not indexed in PhyloDB. Index it first.")

    inference_id = tree_metadata["repositorySpecificData"]["phylodb"]["inferenceId"]

    (vis_job_id, visualization_id) = create_visualization_job(project_id, dataset_id, inference_id)

    print(vis_job_id, visualization_id)
    print("Done creating Job")

    # Wait for the job to finish
    start_time = time.time()

    i = 0
    # Loop until job is done or failed
    while True:
        # Send GET request to status URL
        job_status = get_job(project_id, vis_job_id)

        if job_status is None:
            print("Job not found (probably was completed)")
            break

        print(job_status, "Trial number: ", i)
        time.sleep(5)
        if job_status["completed"] is True:
            print("Job done")
            break

        elif job_status["cancelled"] is True:
            raise Exception("Visualization Job was cancelled")

        i += 1

    end_time = time.time()
    print("Time taken: ", end_time - start_time, " seconds +- 5 seconds")

    tree_view_count = tree_views_collection.count_documents({"projectId": project_id, "datasetId": dataset_id})
    name = f"Tree View {tree_view_count + 1} - {layout}"

    # Create the metadata in the tree collection
    tree_view_metadata = {
        'projectId': project_id,
        'datasetId': dataset_id,
        'treeViewId': visualization_id,
        'name': name,
        'source': {
            'treeId': tree_id
        },
        'layout': layout,
        'repositorySpecificData': {
            'phylodb': {
                'projectId': project_id,
                'datasetId': dataset_id,
                'inferenceId': inference_id,
                'visualizationId': visualization_id
            }
        }
    }

    tree_views_collection.insert_one(tree_view_metadata)

    workflows_collection.update_one(
        {'_id': ObjectId(workflow_id)},
        {'$set': {
            'data.treeViewId': visualization_id
        }})


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Index typing data into PHYLODB')
    parser.add_argument('--project-id', help='The project Id', required=True)
    parser.add_argument('--dataset-id', help='The dataset Id', required=True)
    parser.add_argument('--tree-id', help='The tree Id', required=True)
    parser.add_argument('--workflow-id', help='The workflow Id', required=True)
    parser.add_argument('--layout', help='The layout', required=True)

    args = parser.parse_args()
    compute_tree_view(args.project_id, args.dataset_id, args.tree_id, args.workflow_id, args.layout)
