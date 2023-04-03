import {CreateProjectInputModel} from "./models/createProject/CreateProjectInputModel";
import {CreateProjectOutputModel} from "./models/createProject/CreateProjectOutputModel";
import {GetProjectsOutputModel} from "./models/getProjects/GetProjectsOutputModel";
import {DeleteProjectOutputModel} from "./models/deleteProject/DeleteProjectOutputModel";
import {UploadTypingDataOutputModel} from "./models/uploadTypingData/UploadTypingDataOutputModel";
import {DeleteTypingDataOutputModel} from "./models/deleteTypingData/DeleteTypingDataOutputModel";
import {GetProjectOutputModel} from "./models/getProject/GetProjectOutputModel";
import {DeleteTreeViewOutputModel} from "./models/deleteTreeView/DeleteTreeViewOutputModel";
import {WebApiUris} from "../WebApiUris";
import {UploadIsolateDataOutputModel} from "./models/uploadIsolateData/UploadIsolateDataOutputModel";
import {DeleteIsolateDataOutputModel} from "./models/deleteIsolateData/DeleteIsolateDataOutputModel";
import {DeleteTreeOutputModel} from "./models/deleteTree/DeleteTreeOutputModel";
import {DeleteDistanceMatrixOutputModel} from "./models/deleteDistanceMatrix/DeleteDistanceMatrixOutputModel";
import {CreateDatasetInputModel} from "./models/createDataset/CreateDatasetInputModel";
import {CreateDatasetOutputModel} from "./models/createDataset/CreateDatasetOutputModel";
import {GetDatasetOutputModel} from "./models/getDataset/GetDatasetOutputModel";
import {GetDatasetsOutputModel} from "./models/getDatasets/GetDatasetsOutputModel";
import {DeleteDatasetOutputModel} from "./models/deleteDataset/DeleteDatasetOutputModel";
import {del, get, post} from "../utils/apiFetch";

export namespace AdministrationService {

    /**
     * Gets all projects belonging to a certain user.
     *
     * @return the projects
     */
    export async function getProjects(): Promise<GetProjectsOutputModel> {
        return await get<GetProjectsOutputModel>(WebApiUris.getProjects);
    }

    /**
     * Gets a project.
     *
     * @param projectId the id of the project to be retrieved
     * @return the project
     */
    export async function getProject(
        projectId: string
    ): Promise<GetProjectOutputModel> {
        return await get<GetProjectOutputModel>(WebApiUris.getProject(projectId));
    }

    /**
     * Creates a project.
     *
     * @param createProjectInputModel the project to be created following the CreateProjectModel format
     * @return a promise that resolves to the created project information
     */
    export async function createProject(
        createProjectInputModel: CreateProjectInputModel
    ): Promise<CreateProjectOutputModel> {
        return await post<CreateProjectOutputModel>(
            WebApiUris.createProject,
            JSON.stringify(createProjectInputModel)
        );
    }

    /**
     * Deletes a project.
     *
     * @param projectId   the id of the project to be deleted
     * @return a promise that resolves to the deleted project information
     */
    export async function deleteProject(
        projectId: string
    ): Promise<DeleteProjectOutputModel> {
        return await del<DeleteProjectOutputModel>(WebApiUris.deleteProject(projectId));
    }

    /**
     * Uploads a typing data file to a project.
     *
     * @param projectId the name of the project to which the typing data will be uploaded
     * @param file      the file to be uploaded
     * @return a promise that resolves to the uploaded typing data information
     */
    export async function uploadTypingData(
        projectId: string,
        file: File
    ): Promise<UploadTypingDataOutputModel> {
        const formData = new FormData();
        formData.append("file", file);

        return await post<UploadTypingDataOutputModel>(
            WebApiUris.uploadTypingData(projectId),
            formData,
            {
                "Content-Length": file.size.toString()
            }
        );
    }

    /**
     * Deletes a typing data file.
     *
     * @param projectId the name of the project to which the typing data will be deleted
     * @param typingDataId   the id of the typing data to be deleted
     */
    export async function deleteTypingData(
        projectId: string,
        typingDataId: string
    ): Promise<DeleteTypingDataOutputModel> {
        return await del<DeleteTypingDataOutputModel>(WebApiUris.deleteTypingData(projectId, typingDataId));
    }

    /**
     * Uploads an isolate data file to a project.
     *
     * @param projectId the name of the project to which the isolate data will be uploaded
     * @param file      the file to be uploaded
     * @return a promise that resolves to the uploaded isolate data information
     */
    export async function uploadIsolateData(
        projectId: string,
        file: File
    ): Promise<UploadIsolateDataOutputModel> {
        const formData = new FormData();
        formData.append("file", file);

        return await post<UploadIsolateDataOutputModel>(
            WebApiUris.uploadIsolateData(projectId),
            formData,
            {
                "Content-Length": file.size.toString()
            }
        );
    }

    /**
     * Deletes an isolate data file.
     *
     * @param projectId the name of the project to which the isolate data will be deleted
     * @param isolateDataId   the id of the isolate data to be deleted
     * @return a promise that resolves to the deleted isolate data information
     */
    export async function deleteIsolateData(
        projectId: string,
        isolateDataId: string
    ): Promise<DeleteIsolateDataOutputModel> {
        return await del<DeleteIsolateDataOutputModel>(WebApiUris.deleteIsolateData(projectId, isolateDataId));
    }

    /**
     * Deletes a distance matrix.
     *
     * @param projectId the name of the project to which the distance matrix will be deleted
     * @param datasetId the id of the dataset to which the distance matrix will be deleted
     * @param distanceMatrixId the id of the distance matrix to be deleted
     * @return a promise that resolves to the deleted distance matrix information
     */
    export async function deleteDistanceMatrix(
        projectId: string,
        datasetId: string,
        distanceMatrixId: string
    ): Promise<DeleteDistanceMatrixOutputModel> {
        return await del<DeleteDistanceMatrixOutputModel>(WebApiUris.deleteDistanceMatrix(projectId, datasetId, distanceMatrixId));
    }

    /**
     * Deletes a tree.
     *
     * @param projectId the name of the project to which the tree will be deleted
     * @param datasetId the id of the dataset to which the tree will be deleted
     * @param treeId the id of the tree to be deleted
     * @return a promise that resolves to the deleted tree information
     */
    export async function deleteTree(
        projectId: string,
        datasetId: string,
        treeId: string
    ): Promise<DeleteTreeOutputModel> {
        return await del<DeleteTreeOutputModel>(WebApiUris.deleteTree(projectId, datasetId, treeId));
    }

    /**
     * Deletes a tree view.
     *
     * @param projectId the name of the project to which the tree view will be deleted
     * @param datasetId the id of the dataset to which the tree view will be deleted
     * @param treeViewId the id of the tree view to be deleted
     * @return a promise that resolves to the deleted tree view information
     */
    export async function deleteTreeView(
        projectId: string,
        datasetId: string,
        treeViewId: string
    ): Promise<DeleteTreeViewOutputModel> {
        return await del<DeleteTreeViewOutputModel>(WebApiUris.deleteTreeView(projectId, datasetId, treeViewId));
    }

    /**
     * Create a dataset.
     *
     * @param projectId the name of the project to which the dataset will be created
     * @param createDatasetInputModel the input model for creating a dataset
     * @return a promise that resolves to the created dataset information
     */
    export async function createDataset(
        projectId: string,
        createDatasetInputModel: CreateDatasetInputModel
    ): Promise<CreateDatasetOutputModel> {
        return await post<CreateDatasetOutputModel>(
            WebApiUris.createDataset(projectId),
            JSON.stringify(createDatasetInputModel)
        );
    }

    /**
     * Get a dataset.
     *
     * @param projectId the name of the project to which the dataset belongs
     * @param datasetId the id of the dataset to be retrieved
     * @return a promise that resolves to the retrieved dataset information
     */
    export async function getDataset(
        projectId: string,
        datasetId: string
    ): Promise<GetDatasetOutputModel> {
        return await get<GetDatasetOutputModel>(WebApiUris.getDataset(projectId, datasetId));
    }

    /**
     * Get a list of datasets.
     *
     * @param projectId the name of the project to which the datasets belong
     * @return a promise that resolves to the retrieved list of datasets
     */
    export async function getDatasets(
        projectId: string
    ): Promise<GetDatasetsOutputModel> {
        return await get<GetDatasetsOutputModel>(WebApiUris.getDatasets(projectId));
    }

    /**
     * Delete a dataset.
     *
     * @param projectId the name of the project to which the dataset belongs
     * @param datasetId the id of the dataset to be deleted
     * @return a promise that resolves to the deleted dataset information
     */
    export async function deleteDataset(
        projectId: string,
        datasetId: string
    ): Promise<DeleteDatasetOutputModel> {
        return await del<DeleteDatasetOutputModel>(WebApiUris.deleteDataset(projectId, datasetId));
    }
}
