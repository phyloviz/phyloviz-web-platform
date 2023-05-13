import argparse
import csv
import json
import os
import requests
import uuid
from bson.objectid import ObjectId
from concurrent.futures import ThreadPoolExecutor
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
dataset_collection = db['datasets']
projects_collection = db['projects']


def create_project(project_id):
    payload = json.dumps({
        "id": project_id,
        "name": project_id,
        "visibility": "public",
        "description": "",
        "users": []
    })

    response = requests.request("PUT", f"{base_url}/projects/{project_id}", headers=json_headers, data=payload)

    if response.status_code not in range(200, 299):
        raise Exception(f"Project creation failed with status_code {response.status_code} \n{response.text}")


def create_taxon(taxon_id):
    payload = json.dumps({
        "id": taxon_id,
        "description": taxon_id
    })

    response = requests.request("PUT", f"{base_url}/taxa/{taxon_id}", headers=json_headers, data=payload)

    if response.status_code not in range(200, 299):
        raise Exception(f"Taxon creation failed with status_code {response.status_code} \n{response.text}")


def create_locus(taxon_id, locus_id):
    payload = json.dumps({
        "id": locus_id,
        "description": locus_id
    })

    url = f"{base_url}/taxa/{taxon_id}/loci/{locus_id}"
    response = requests.request("PUT", url, headers=json_headers, data=payload)

    if response.status_code not in range(200, 299):
        raise Exception(f"Locus creation failed with status_code {response.status_code} \n{response.text}")


def create_mlst_schema(taxon_id, schema_id, loci_list):
    payload = json.dumps({
        "taxon_id": taxon_id,
        "id": schema_id,
        "type": "mlst",
        "description": schema_id,
        "loci": loci_list
    })

    response = requests.request(
        "PUT",
        f"{base_url}/taxa/{taxon_id}/schemas/{schema_id}", headers=json_headers, data=payload
    )

    if response.status_code not in range(200, 299):
        raise Exception(f"Schema creation failed with status_code {response.status_code} \n{response.text}")


def create_dataset(project_id, dataset_id):
    taxon_id = str(uuid.uuid4())
    create_taxon(taxon_id)
    print("Done creating taxon")

    loci_list = upload_loci(taxon_id, typing_data_file_path)
    print("Done uploading loci")

    allele_ids = {}

    with open(typing_data_file_path, 'r') as file:
        reader = csv.reader(file, delimiter='\t')
        headers = next(reader)

        for row in reader:
            for i, allele_id in enumerate(row[1:], start=1):
                gene = headers[i]
                if gene not in allele_ids:
                    allele_ids[gene] = set()

                allele_ids[gene].add(allele_id)

    for gene in allele_ids.keys():
        fasta_file = f"./fasta_{gene}.fasta"
        with open(fasta_file, 'w') as outfile:
            for allele_id in allele_ids[gene]:
                outfile.write(f">{gene}_{allele_id}\nA\n")

    for gene in allele_ids.keys():
        loci_id = gene.split('.')[0]
        print(f"Uploading {loci_id} sequences")
        upload_allelic_sequences(taxon_id, loci_id, f'./fasta_{gene}.fasta')

    schema_id = str(uuid.uuid4())
    create_mlst_schema(taxon_id, schema_id, loci_list)
    print("Done creating schema")

    payload = json.dumps({
        "id": dataset_id,
        "taxon_id": taxon_id,
        "schema_id": schema_id,
        "description": ""
    })

    response = requests.request(
        "PUT", f"{base_url}/projects/{project_id}/datasets/{dataset_id}", headers=json_headers,
        data=payload)

    if response.status_code not in range(200, 299):
        raise Exception(f"Dataset creation failed with status_code {response.status_code} \n{response.text}")


def project_exists(project_id):
    response = requests.request(
        "GET", f"{base_url}/projects/{project_id}", headers=json_headers)

    if response.status_code == 404:
        return False

    if response.status_code not in range(200, 299):
        raise Exception(f"Project get failed with status_code {response.status_code} \n{response.text}")

    return True


def upload_loci(taxon_id, typing_data_file_path):
    with open(typing_data_file_path, 'r') as f:
        first_row = f.readline()

    loci_list = first_row.split('\t')

    loci_list.pop(0)

    loci_list = [locus_id for locus_id in loci_list if not locus_id.isspace() and locus_id]
    loci_list = [locus_id.strip() for locus_id in loci_list]

    # Create thread pool to create loci in parallel
    with ThreadPoolExecutor() as executor:
        futures = []
        for locus_id in loci_list:
            print(repr(locus_id))
            future = executor.submit(create_locus, taxon_id, locus_id)
            futures.append(future)

        for i, future in enumerate(futures):
            future.result()
    print("Done creating loci")
    return loci_list


def upload_allelic_sequences(taxon_id, loci_id, file):
    files = {
        'file': open(file, 'rb')
    }

    response = requests.request(
        "PUT", f"{base_url}/taxa/{taxon_id}/loci/{loci_id}/alleles/files", files=files)

    if response.status_code not in range(200, 299):
        raise Exception(
            f"Allelic sequences upload failed with status_code {response.status_code} \n{response.text}")

    return response.json()


def upload_allele_profiles(project_id, dataset_id, file):
    files = {
        'file': open(file, 'rb')
    }

    response = requests.request(
        "PUT", f"{base_url}/projects/{project_id}/datasets/{dataset_id}/profiles/files", files=files)

    if response.status_code not in range(200, 299):
        raise Exception(
            f"Allelic profiles upload failed with status_code {response.status_code} \n{response.text}")

    return response.json()


def dataset_exists(project_id, dataset_id):
    response = requests.request(
        "GET", f"{base_url}/projects/{project_id}/datasets/{dataset_id}", headers=json_headers)

    if response.status_code == 404:
        return False

    if response.status_code not in range(200, 299):
        raise Exception(
            f"Dataset get failed with status_code {response.status_code} \n{response.text}")

    return True


def index_typing_data(typing_data_file_path, project_id, dataset_id, workflow_id):
    print(f"Project ID: {project_id}")
    print(f"Dataset ID: {dataset_id}")
    print(f"Typing data file path: {typing_data_file_path}")
    print(f"Workflow ID: {workflow_id}")

    if projects_collection.find_one({'_id': ObjectId(project_id)}) is None:
        raise Exception(f"Project with ID {project_id} does not exist in PHYLOViZ Web Platform")

    dataset_metadata = datasets_collection.find_one({'_id': ObjectId(dataset_id), 'projectId': project_id})
    if dataset_metadata is None:
        raise Exception(
            f"Dataset with ID {dataset_id} and Project ID {project_id} does not exist in PHYLOViZ Web Platform")

    if typing_data_collection.find_one(
            {
                'typingDataId': dataset_metadata['typingDataId'],
                'repositorySpecificData.phylodb.datasetIds': {'in': [dataset_id]}
            }
    ) is not None:
        raise Exception(f"Dataset with ID {dataset_id} already has Typing Data indexed in PhyloDB")

    if not project_exists(project_id):
        create_project(project_id)
        print("Done creating project in PhyloDB")
    else:
        print("Project already exists in PhyloDB")

    if not dataset_exists(project_id, dataset_id):
        create_dataset(project_id, dataset_id)
        print("Done creating dataset in PhyloDB")
    else:
        raise Exception("Dataset already exists in PhyloDB (typing data already indexed), no need to index again")

    upload_allele_profiles(project_id, dataset_id, typing_data_file_path)
    print("Done uploading allele profiles to dataset in PhyloDB")

    typing_data_id = dataset_metadata['typingDataId']

    typing_data_metadata = typing_data_collection.find_one(
        {
            'typingDataId': typing_data_id,
            'repositorySpecificData.phylodb': {'$exists': True}
        }
    )

    if typing_data_metadata is None:
        typing_data_collection.update_one(
            {'typingDataId': typing_data_id},
            {'$set': {
                'repositorySpecificData.phylodb': {
                    'projectId': project_id,
                    'datasetIds': [dataset_id]
                }
            }}
        )
    else:
        typing_data_collection.update_one(
            {'typingDataId': typing_data_id},
            {'$push': {
                'repositorySpecificData.phylodb.datasetIds': dataset_id
            }}
        )


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Index typing data into PHYLODB')
    parser.add_argument('--typing-data-file-path', help='The typing data file path', required=True)
    parser.add_argument('--project-id', help='The project Id', required=True)
    parser.add_argument('--dataset-id', help='The dataset Id', required=True)
    parser.add_argument('--workflow-id', help='The workflow Id', required=True)

    args = parser.parse_args()
    index_typing_data(args.typing_data_file_path, args.project_id, args.dataset_id, args.workflow_id)
