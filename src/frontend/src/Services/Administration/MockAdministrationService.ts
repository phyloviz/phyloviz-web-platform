import {CreateProjectInputModel} from "./models/projects/createProject/CreateProjectInputModel"
import {CreateProjectOutputModel} from "./models/projects/createProject/CreateProjectOutputModel"
import {GetProjectsOutputModel} from "./models/projects/getProjects/GetProjectsOutputModel"
import {DeleteProjectOutputModel} from "./models/projects/deleteProject/DeleteProjectOutputModel"
import {DeleteTypingDataOutputModel} from "./models/files/deleteTypingData/DeleteTypingDataOutputModel"
import {GetProjectOutputModel, Project} from "./models/projects/getProject/GetProjectOutputModel"
import {DeleteTreeViewOutputModel} from "./models/treeViews/deleteTreeView/DeleteTreeViewOutputModel"
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
import {UpdateProjectOutputModel} from "./models/projects/updateProject/UpdateProjectOutputModel";
import {UpdateProjectInputModel} from "./models/projects/updateProject/UpdateProjectInputModel";
import {UpdateTypingDataInputModel} from "./models/files/updateTypingData/UpdateTypingDataInputModel";
import {UpdateTypingDataOutputModel} from "./models/files/updateTypingData/UpdateTypingDataOutputModel";
import {UpdateIsolateDataInputModel} from "./models/files/updateIsolateData/UpdateIsolateDataInputModel";
import {UpdateIsolateDataOutputModel} from "./models/files/updateIsolateData/UpdateIsolateDataOutputModel";
import {
    UpdateDistanceMatrixOutputModel
} from "./models/distanceMatrices/updateDistanceMatrix/UpdateDistanceMatrixOutputModel";
import {
    UpdateDistanceMatrixInputModel
} from "./models/distanceMatrices/updateDistanceMatrix/UpdateDistanceMatrixInputModel";
import {UpdateTreeOutputModel} from "./models/trees/updateTree/UpdateTreeOutputModel";
import {UpdateTreeInputModel} from "./models/trees/updateTree/UpdateTreeInputModel";
import {UpdateTreeViewOutputModel} from "./models/treeViews/updateTreeView/UpdateTreeViewOutputModel";
import {UpdateTreeViewInputModel} from "./models/treeViews/updateTreeView/UpdateTreeViewInputModel";
import {UpdateDatasetInputModel} from "./models/datasets/updateDataset/UpdateDatasetInputModel";
import {UpdateDatasetOutputModel} from "./models/datasets/updateDataset/UpdateDatasetOutputModel";

export namespace MockAdministrationService {

    export const mockProjects = new Map<string, Project>(
        [
            ["project1", {
                projectId: "project1",
                name: "Project 1",
                description: "Project 1 description",
                owner: "user1",
                datasets: [
                    {
                        datasetId: "dataset1",
                        name: "Dataset 1",
                        description: "Dataset 1 description",
                        typingDataId: "typingData1",
                        isolateDataId: "isolateData1",
                        isolateDataKey: "continent",
                        distanceMatrices: [
                            {
                                distanceMatrixId: "distanceMatrix1",
                                name: "Hamming Distance",
                                sourceType: "function",
                                source: {
                                    function: "hamming"
                                }
                            }
                        ],
                        trees: [
                            {
                                treeId: "tree1",
                                name: "GoeBurst",
                                sourceType: "algorithmDistanceMatrix",
                                source: {
                                    algorithm: "goeBURST",
                                    distanceMatrixId: "distanceMatrix1",
                                    parameters: "{ level: 'SLV' }"
                                }
                            },
                            {
                                treeId: "tree2",
                                name: "GoeBurst Dynamic",
                                sourceType: "algorithmTypingData",
                                source: {
                                    algorithm: "goeBURST",
                                    parameters: "{ level: 'SLV' }"
                                }
                            },
                            {
                                treeId: "tree3",
                                name: "Newick",
                                sourceType: "file",
                                source: {
                                    fileType: "newick",
                                    fileName: "tree2023.tree"
                                }
                            }
                        ],
                        treeViews: [
                            {
                                treeViewId: "treeView1",
                                name: "Tree View 1",
                                layout: "force-directed",
                                source: {
                                    treeId: "tree1",
                                    typingDataId: "typingData1",
                                    isolateDataId: "isolateData1"
                                }
                            }
                        ]
                    }
                ],
                files: {
                    typingData: [
                        {
                            typingDataId: "typingData1",
                            name: "Typing Data 1",
                        }
                    ],
                    isolateData: [
                        {
                            isolateDataId: "isolateData1",
                            name: "Isolate Data 1",
                            keys: ["id", "isolate", "aliases", "country", "continent", "region", "town_or_city",
                                "year", "month", "isolation_date", "received_date", "age_yr", "age_mth", "sex"]
                        }
                    ]
                }
            }
            ]
        ]
    )
    const DELAY = 1000

    /**
     * Gets all projects belonging to a certain user.
     *
     * @return the projects
     */
    export async function getProjects(): Promise<GetProjectsOutputModel> {
        return new Promise(resolve => setTimeout(resolve, DELAY))
            .then(() => ({
                projects: Array.from(mockProjects.values()).map(project => ({
                    projectId: project.projectId,
                    name: project.name,
                    description: project.description
                }))
            }))
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
        return mockProjects.get(projectId)!
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
        const projectId = Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15)

        mockProjects.set(projectId, {
            projectId: projectId,
            name: createProjectInputModel.name,
            description: createProjectInputModel.description,
            owner: "user1",
            datasets: [],
            files: {
                typingData: [],
                isolateData: []
            }
        })

        return {
            projectId
        }
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
        mockProjects.delete(projectId)

        return {
            projectId
        }
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
        const project = mockProjects.get(projectId)!

        project.name = updateProjectInputModel.name
        project.description = updateProjectInputModel.description

        return {
            name: project.name,
            description: project.description
        }
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
        mockProjects.get(projectId)!.files.typingData = mockProjects.get(projectId)!.files.typingData.filter(
            typingData => typingData.typingDataId !== typingDataId
        )

        return {
            projectId,
            typingDataId
        }
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
        const typingData = mockProjects.get(projectId)!.files.typingData.find(
            typingData => typingData.typingDataId === typingDataId
        )!

        typingData.name = updateTypingDataInputModel.name

        return {
            name: typingData.name
        }
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
        mockProjects.get(projectId)!.files.isolateData = mockProjects.get(projectId)!.files.isolateData.filter(
            isolateData => isolateData.isolateDataId !== isolateDataId
        )

        return {
            projectId,
            isolateDataId
        }
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
        const isolateData = mockProjects.get(projectId)!.files.isolateData.find(
            isolateData => isolateData.isolateDataId === isolateDataId
        )!

        isolateData.name = updateIsolateDataInputModel.name

        return {
            name: isolateData.name
        }
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
        mockProjects.get(projectId)!.datasets = mockProjects.get(projectId)!.datasets.map(dataset => {
            if (dataset.datasetId === datasetId) {
                dataset.distanceMatrices = dataset.distanceMatrices.filter(
                    distanceMatrix => distanceMatrix.distanceMatrixId !== distanceMatrixId
                )
            }
            return dataset
        })

        return {
            projectId,
            datasetId,
            distanceMatrixId
        }
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
        const distanceMatrix = mockProjects.get(projectId)!.datasets
            .find(dataset => dataset.datasetId === datasetId)!
            .distanceMatrices.find(distanceMatrix => distanceMatrix.distanceMatrixId === distanceMatrixId)!

        distanceMatrix.name = updateDistanceMatrixInputModel.name

        return {
            name: distanceMatrix.name
        }
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
        mockProjects.get(projectId)!.datasets = mockProjects.get(projectId)!.datasets.map(dataset => {
            if (dataset.datasetId === datasetId) {
                dataset.trees = dataset.trees.filter(tree => tree.treeId !== treeId)
            }
            return dataset
        })

        return {
            projectId,
            datasetId,
            treeId
        }
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
        const tree = mockProjects.get(projectId)!.datasets
            .find(dataset => dataset.datasetId === datasetId)!
            .trees.find(tree => tree.treeId === treeId)!

        tree.name = updateTreeInputModel.name

        return {
            name: tree.name
        }
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
        mockProjects.get(projectId)!.datasets = mockProjects.get(projectId)!.datasets.map(dataset => {
            if (dataset.datasetId === datasetId) {
                dataset.treeViews = dataset.treeViews.filter(treeView => treeView.treeViewId !== treeViewId)
            }
            return dataset
        })

        return {
            projectId,
            datasetId,
            treeViewId
        }
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
        const treeView = mockProjects.get(projectId)!.datasets
            .find(dataset => dataset.datasetId === datasetId)!
            .treeViews.find(treeView => treeView.treeViewId === treeViewId)!

        treeView.name = updateTreeViewInputModel.name

        return {
            name: treeView.name
        }
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
        const datasetId = Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15)
        mockProjects.get(projectId)!.datasets.push({
            datasetId,
            name: createDatasetInputModel.name,
            description: createDatasetInputModel.description,
            typingDataId: createDatasetInputModel.typingDataId,
            isolateDataId: createDatasetInputModel.isolateDataId,
            isolateDataKey: createDatasetInputModel.isolateDataKey,
            distanceMatrices: [],
            trees: [],
            treeViews: []
        })

        return {
            projectId,
            datasetId
        }
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
        return mockProjects.get(projectId)!.datasets.find(dataset => dataset.datasetId === datasetId)!
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
        return {
            datasets: mockProjects.get(projectId)!.datasets
        }
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
        mockProjects.get(projectId)!.datasets = mockProjects.get(projectId)!.datasets.filter(
            dataset => dataset.datasetId !== datasetId
        )

        return {
            projectId,
            datasetId
        }
    }

    /**
     * Updates a dataset.
     *
     * @param projectId the id of the project to which the dataset belongs
     * @param datasetId the id of the dataset to be updated
     * @param updateDatasetInputModel the input model for updating a dataset
     * @return information about the update
     */
    export async function updateDataset(
        projectId: string,
        datasetId: string,
        updateDatasetInputModel: UpdateDatasetInputModel
    ): Promise<UpdateDatasetOutputModel> {
        const dataset = mockProjects.get(projectId)!.datasets.find(dataset => dataset.datasetId === datasetId)!

        dataset.name = updateDatasetInputModel.name
        dataset.description = updateDatasetInputModel.description

        return {
            name: dataset.name,
            description: dataset.description
        }
    }
}
