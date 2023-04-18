import {CreateProjectInputModel} from "./models/createProject/CreateProjectInputModel"
import {CreateProjectOutputModel} from "./models/createProject/CreateProjectOutputModel"
import {GetProjectsOutputModel} from "./models/getProjects/GetProjectsOutputModel"
import {DeleteProjectOutputModel} from "./models/deleteProject/DeleteProjectOutputModel"
import {UploadTypingDataOutputModel} from "./models/uploadTypingData/UploadTypingDataOutputModel"
import {DeleteTypingDataOutputModel} from "./models/deleteTypingData/DeleteTypingDataOutputModel"
import {GetProjectOutputModel, Project} from "./models/getProject/GetProjectOutputModel"
import {DeleteTreeViewOutputModel} from "./models/deleteTreeView/DeleteTreeViewOutputModel"
import {UploadIsolateDataOutputModel} from "./models/uploadIsolateData/UploadIsolateDataOutputModel"
import {DeleteIsolateDataOutputModel} from "./models/deleteIsolateData/DeleteIsolateDataOutputModel"
import {DeleteTreeOutputModel} from "./models/deleteTree/DeleteTreeOutputModel"
import {DeleteDistanceMatrixOutputModel} from "./models/deleteDistanceMatrix/DeleteDistanceMatrixOutputModel"
import {CreateDatasetInputModel} from "./models/createDataset/CreateDatasetInputModel"
import {CreateDatasetOutputModel} from "./models/createDataset/CreateDatasetOutputModel"
import {GetDatasetOutputModel} from "./models/getDataset/GetDatasetOutputModel"
import {GetDatasetsOutputModel} from "./models/getDatasets/GetDatasetsOutputModel"
import {DeleteDatasetOutputModel} from "./models/deleteDataset/DeleteDatasetOutputModel"

export namespace MockAdministrationService {

    const projects = new Map<string, Project>(
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
                                    parameters: ""
                                }
                            },
                            {
                                treeId: "tree2",
                                name: "GoeBurst Dynamic",
                                sourceType: "algorithmTypingData",
                                source: {
                                    algorithm: "goeBURST",
                                    parameters: ""
                                }
                            },
                            {
                                treeId: "tree3",
                                name: "Newick",
                                sourceType: "file",
                                source: {
                                    fileType: "newick",
                                    fileName: "tree.nwk"
                                }
                            }
                        ],
                        treeViews: [
                            {
                                treeViewId: "treeView1",
                                name: "Radial",
                                layout: "radial",
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
                projects: Array.from(projects.values()).map(project => ({
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
        return projects.get(projectId)!
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

        projects.set(projectId, {
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
        projects.delete(projectId)

        return {
            projectId
        }
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
        const typingDataId = Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15)
        projects.get(projectId)!.files.typingData.push({
            typingDataId,
            name: file.name
        })

        return {
            projectId,
            typingDataId
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
        projects.get(projectId)!.files.typingData = projects.get(projectId)!.files.typingData.filter(
            typingData => typingData.typingDataId !== typingDataId
        )

        return {
            projectId,
            typingDataId
        }
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
        const isolateDataId = Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15)
        projects.get(projectId)!.files.isolateData.push({
            isolateDataId,
            name: file.name
        })

        return {
            projectId,
            isolateDataId
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
        projects.get(projectId)!.files.isolateData = projects.get(projectId)!.files.isolateData.filter(
            isolateData => isolateData.isolateDataId !== isolateDataId
        )

        return {
            projectId,
            isolateDataId
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
        projects.get(projectId)!.datasets = projects.get(projectId)!.datasets.map(dataset => {
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
        projects.get(projectId)!.datasets = projects.get(projectId)!.datasets.map(dataset => {
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
        projects.get(projectId)!.datasets = projects.get(projectId)!.datasets.map(dataset => {
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
        projects.get(projectId)!.datasets.push({
            datasetId,
            name: createDatasetInputModel.name,
            description: createDatasetInputModel.description,
            typingDataId: createDatasetInputModel.typingDataId,
            isolateDataId: createDatasetInputModel.isolateDataId,
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
        return projects.get(projectId)!.datasets.find(dataset => dataset.datasetId === datasetId)!
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
            datasets: projects.get(projectId)!.datasets
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
        projects.get(projectId)!.datasets = projects.get(projectId)!.datasets.filter(
            dataset => dataset.datasetId !== datasetId
        )

        return {
            projectId,
            datasetId
        }
    }
}
