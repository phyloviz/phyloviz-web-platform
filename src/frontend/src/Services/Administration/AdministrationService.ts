import {CreateProjectInputModel} from "./models/projects/createProject/CreateProjectInputModel"
import {CreateProjectOutputModel} from "./models/projects/createProject/CreateProjectOutputModel"
import {GetProjectsOutputModel} from "./models/projects/getProjects/GetProjectsOutputModel"
import {DeleteProjectOutputModel} from "./models/projects/deleteProject/DeleteProjectOutputModel"
import {DeleteTypingDataOutputModel} from "./models/files/deleteTypingData/DeleteTypingDataOutputModel"
import {GetProjectOutputModel} from "./models/projects/getProject/GetProjectOutputModel"
import {DeleteTreeViewOutputModel} from "./models/treeViews/deleteTreeView/DeleteTreeViewOutputModel"
import {WebApiUris} from "../WebApiUris"
import {DeleteIsolateDataOutputModel} from "./models/files/deleteIsolateData/DeleteIsolateDataOutputModel"
import {DeleteTreeOutputModel} from "./models/trees/deleteTree/DeleteTreeOutputModel"
import {
    DeleteDistanceMatrixOutputModel
} from "./models/distanceMatrices/deleteDistanceMatrix/DeleteDistanceMatrixOutputModel"
import {CreateDatasetInputModel} from "./models/datasets/createDataset/CreateDatasetInputModel"
import {CreateDatasetOutputModel} from "./models/datasets/createDataset/CreateDatasetOutputModel"
import {GetDatasetOutputModel} from "./models/datasets/getDataset/GetDatasetOutputModel"
import {GetDatasetsOutputModel} from "./models/datasets/getDatasets/GetDatasetsOutputModel"
import {DeleteDatasetOutputModel} from "./models/datasets/deleteDataset/DeleteDatasetOutputModel"
import {del, get, patch, post} from "../utils/apiFetch"
import {MockAdministrationService} from "./MockAdministrationService"
import {UpdateProjectOutputModel} from "./models/projects/updateProject/UpdateProjectOutputModel";
import {UpdateProjectInputModel} from "./models/projects/updateProject/UpdateProjectInputModel";
import {UpdateTypingDataInputModel} from "./models/files/updateTypingData/UpdateTypingDataInputModel";
import {UpdateTypingDataOutputModel} from "./models/files/updateTypingData/UpdateTypingDataOutputModel";
import {UpdateIsolateDataInputModel} from "./models/files/updateIsolateData/UpdateIsolateDataInputModel";
import {UpdateIsolateDataOutputModel} from "./models/files/updateIsolateData/UpdateIsolateDataOutputModel";
import {UpdateDatasetInputModel} from "./models/datasets/updateDataset/UpdateDatasetInputModel";
import {UpdateDatasetOutputModel} from "./models/datasets/updateDataset/UpdateDatasetOutputModel";
import {
    UpdateDistanceMatrixInputModel
} from "./models/distanceMatrices/updateDistanceMatrix/UpdateDistanceMatrixInputModel";
import {
    UpdateDistanceMatrixOutputModel
} from "./models/distanceMatrices/updateDistanceMatrix/UpdateDistanceMatrixOutputModel";
import {UpdateTreeInputModel} from "./models/trees/updateTree/UpdateTreeInputModel";
import {UpdateTreeOutputModel} from "./models/trees/updateTree/UpdateTreeOutputModel";
import {UpdateTreeViewInputModel} from "./models/treeViews/updateTreeView/UpdateTreeViewInputModel";
import {UpdateTreeViewOutputModel} from "./models/treeViews/updateTreeView/UpdateTreeViewOutputModel";
import {
    SetIsolateDataOfDatasetInputModel
} from "./models/datasets/setIsolateDataOfDataset/SetIsolateDataOfDatasetInputModel";
import {
    SetIsolateDataOfDatasetOutputModel
} from "./models/datasets/setIsolateDataOfDataset/SetIsolateDataOfDatasetOutputModel";

namespace AdministrationService {

    /**
     * Gets all projects belonging to a certain user.
     *
     * @return the projects
     */
    export async function getProjects(): Promise<GetProjectsOutputModel> {
        return await get<GetProjectsOutputModel>(WebApiUris.getProjects)
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
        return await get<GetProjectOutputModel>(WebApiUris.getProject(projectId))
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
        )
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
        return await del<DeleteProjectOutputModel>(WebApiUris.deleteProject(projectId))
    }

    /**
     * Updates a project.
     *
     * @param projectId the id of the project to be updated
     * @param updateProjectInputModel the project to be updated following the UpdateProjectModel format
     * @return a promise that resolves to the updated project information
     */
    export async function updateProject(
        projectId: string,
        updateProjectInputModel: UpdateProjectInputModel
    ): Promise<UpdateProjectOutputModel> {
        return await patch<UpdateProjectOutputModel>(
            WebApiUris.updateProject(projectId),
            JSON.stringify(updateProjectInputModel)
        )
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
        return await del<DeleteTypingDataOutputModel>(WebApiUris.deleteTypingData(projectId, typingDataId))
    }

    /**
     * Updates a typing data file.
     *
     * @param projectId    the id of the project to which the typing data file belongs
     * @param typingDataId the id of the typing data file to be updated
     * @param updateTypingDataInputModel the typing data file to be updated following the UpdateTypingDataModel format
     * @return information about the updated typing data file
     */
    export async function updateTypingData(
        projectId: string,
        typingDataId: string,
        updateTypingDataInputModel: UpdateTypingDataInputModel
    ): Promise<UpdateTypingDataOutputModel> {
        return await patch<UpdateTypingDataOutputModel>(
            WebApiUris.updateTypingData(projectId, typingDataId),
            JSON.stringify(updateTypingDataInputModel)
        )
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
        return await del<DeleteIsolateDataOutputModel>(WebApiUris.deleteIsolateData(projectId, isolateDataId))
    }

    /**
     * Updates an isolate data file.
     *
     * @param projectId    the id of the project to which the isolate data file belongs
     * @param isolateDataId the id of the isolate data file to be updated
     * @param updateIsolateDataInputModel the isolate data file to be updated following the UpdateIsolateDataModel format
     * @return information about the updated isolate data file
     */
    export async function updateIsolateData(
        projectId: string,
        isolateDataId: string,
        updateIsolateDataInputModel: UpdateIsolateDataInputModel
    ): Promise<UpdateIsolateDataOutputModel> {
        return await patch<UpdateIsolateDataOutputModel>(
            WebApiUris.updateIsolateData(projectId, isolateDataId),
            JSON.stringify(updateIsolateDataInputModel)
        )
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
        return await del<DeleteDistanceMatrixOutputModel>(WebApiUris.deleteDistanceMatrix(projectId, datasetId, distanceMatrixId))
    }

    /**
     * Updates a distance matrix.
     *
     * @param projectId        the id of the project that contains the distance matrix
     * @param datasetId        the id of the dataset that contains the distance matrix
     * @param distanceMatrixId the id of the distance matrix to be updated
     * @param updateDistanceMatrixInputModel the distance matrix to be updated following the UpdateDistanceMatrixModel format
     * @return information about the updated distance matrix
     */
    export async function updateDistanceMatrix(
        projectId: string,
        datasetId: string,
        distanceMatrixId: string,
        updateDistanceMatrixInputModel: UpdateDistanceMatrixInputModel
    ): Promise<UpdateDistanceMatrixOutputModel> {
        return await patch<UpdateDistanceMatrixOutputModel>(
            WebApiUris.updateDistanceMatrix(projectId, datasetId, distanceMatrixId),
            JSON.stringify(updateDistanceMatrixInputModel)
        )
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
        return await del<DeleteTreeOutputModel>(WebApiUris.deleteTree(projectId, datasetId, treeId))
    }

    /**
     * Updates a tree.
     *
     * @param projectId the id of the project that contains the tree
     * @param datasetId the id of the dataset that contains the tree
     * @param treeId    the id of the tree to be updated
     * @param updateTreeInputModel the tree to be updated following the UpdateTreeModel format
     * @return information about the updated tree
     */
    export async function updateTree(
        projectId: string,
        datasetId: string,
        treeId: string,
        updateTreeInputModel: UpdateTreeInputModel
    ): Promise<UpdateTreeOutputModel> {
        return await patch<UpdateTreeOutputModel>(
            WebApiUris.updateTree(projectId, datasetId, treeId),
            JSON.stringify(updateTreeInputModel)
        )
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
        return await del<DeleteTreeViewOutputModel>(WebApiUris.deleteTreeView(projectId, datasetId, treeViewId))
    }

    /**
     * Updates a tree view.
     *
     * @param projectId    the id of the project that contains the tree view
     * @param datasetId    the id of the dataset that contains the tree view
     * @param treeViewId the id of the tree view to be updated
     * @param updateTreeViewInputModel the tree view to be updated following the UpdateTreeViewModel format
     * @return information about the updated tree view
     */
    export async function updateTreeView(
        projectId: string,
        datasetId: string,
        treeViewId: string,
        updateTreeViewInputModel: UpdateTreeViewInputModel
    ): Promise<UpdateTreeViewOutputModel> {
        return await patch<UpdateTreeViewOutputModel>(
            WebApiUris.updateTreeView(projectId, datasetId, treeViewId),
            JSON.stringify(updateTreeViewInputModel)
        )
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
        )
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
        return await get<GetDatasetOutputModel>(WebApiUris.getDataset(projectId, datasetId))
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
        return await get<GetDatasetsOutputModel>(WebApiUris.getDatasets(projectId))
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
        return await del<DeleteDatasetOutputModel>(WebApiUris.deleteDataset(projectId, datasetId))
    }

    /**
     * Updates a dataset.
     *
     * @param projectId the id of the project to which the dataset belongs
     * @param datasetId the id of the dataset to be updated
     * @param inputModel the input model with the dataset information to be updated
     * @return information about the update
     */
    export async function updateDataset(
        projectId: string,
        datasetId: string,
        inputModel: UpdateDatasetInputModel
    ): Promise<UpdateDatasetOutputModel> {
        return await patch<UpdateDatasetOutputModel>(
            WebApiUris.updateDataset(projectId, datasetId),
            JSON.stringify(inputModel)
        )
    }

    /**
     * Sets the isolate data of a dataset.
     *
     * @param projectId  the id of the project to which the dataset belongs
     * @param datasetId  the id of the dataset to be updated
     * @param inputModel the input model with the isolate data information to be set
     * @return information about the update
     */
    export async function setIsolateDataOfDataset(
        projectId: string,
        datasetId: string,
        inputModel: SetIsolateDataOfDatasetInputModel
    ): Promise<SetIsolateDataOfDatasetOutputModel> {
        return await patch<SetIsolateDataOfDatasetOutputModel>(
            WebApiUris.setIsolateDataOfDataset(projectId, datasetId),
            JSON.stringify(inputModel)
        )
    }
}

const env = process.env.MOCK_ENV

export default env === "true" ? MockAdministrationService : AdministrationService