import {CreateProjectInputModel} from "./models/createProject/CreateProjectInputModel";
import {CreateProjectOutputModel} from "./models/createProject/CreateProjectOutputModel";
import {GetProjectsOutputModel} from "./models/getProjects/GetProjectsOutputModel";
import {DeleteProjectOutputModel} from "./models/deleteProject/DeleteProjectOutputModel";
import {UploadTypingDataOutputModel} from "./models/uploadTypingData/UploadTypingDataOutputModel";
import {DeleteTypingDatasetOutputModel} from "./models/deleteTypingDataset/DeleteTypingDatasetOutputModel";
import {GetProjectOutputModel} from "./models/getProject/GetProjectOutputModel";
import {DeleteInferenceTreeOutputModel} from "./models/deleteInferenceTree/DeleteInferenceTreeOutputModel";
import {DeleteTreeViewOutputModel} from "./models/deleteTreeView/DeleteTreeViewOutputModel";
import {API_BASE_URL, WebApiUris} from "../../Utils/navigation/WebApiUris";
import {UploadIsolateDataOutputModel} from "./models/uploadIsolateData/UploadIsolateDataOutputModel";

export namespace AdministrationService {

    /**
     * Gets all projects belonging to a certain user.
     *
     * @return the projects
     */
    export async function getProjects(): Promise<GetProjectsOutputModel> {
        return await fetch(WebApiUris.getProjects, {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res => {
            if (res.ok)
                return res.json();
            else
                throw new Error(res.statusText);
        });
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
        return await fetch(WebApiUris.getProject(projectId), {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res => {
            if (res.ok)
                return res.json();
            else
                throw new Error(res.statusText);
        });
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
        return await fetch(WebApiUris.createProject, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(createProjectInputModel)
        }).then(res => {
            if (res.ok)
                return res.json();
            else
                throw new Error(res.statusText);
        });
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
        return await fetch(WebApiUris.deleteProject(projectId), {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res => {
            if (res.ok)
                return res.json();
            else
                throw new Error(res.statusText);
        });
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

        return await fetch(WebApiUris.uploadTypingData(projectId), {
            method: "POST",
            headers: {
                "Content-Type": "multipart/form-data"
            },
            body: formData
        }).then(res => {
            if (res.ok)
                return res.json();
            else
                throw new Error(res.statusText);
        });
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

        return await fetch(WebApiUris.uploadIsolateData(projectId), {
            method: "POST",
            headers: {
                "Content-Type": "multipart/form-data"
            },
            body: formData
        }).then(res => {
            if (res.ok)
                return res.json();
            else
                throw new Error(res.statusText);
        });
    }

    /**
     * Deletes a typing dataset.
     *
     * @param projectId the name of the project to which the typing dataset will be deleted
     * @param id   the id of the typing dataset to be deleted
     */
    export async function deleteTypingDataset(
        projectId: string,
        id: string
    ): Promise<DeleteTypingDatasetOutputModel> {
        return await fetch(`${API_BASE_URL}/projects/${projectId}/typing-datasets/${id}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res => {
            if (res.ok)
                return res.json();
            else
                throw new Error(res.statusText);
        });
    }

    /**
     * Deletes an inference tree.
     *
     * @param projectId the name of the project to which the inference tree will be deleted
     * @param id   the id of the inference tree to be deleted
     */
    export async function deleteInferenceTree(
        projectId: string,
        id: string
    ): Promise<DeleteInferenceTreeOutputModel> {
        return await fetch(`${API_BASE_URL}/projects/${projectId}/inference-trees/${id}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res => {
            if (res.ok)
                return res.json();
            else
                throw new Error(res.statusText);
        });
    }

    /**
     * Deletes a tree view.
     *
     * @param projectId the name of the project to which the tree view will be deleted
     * @param id   the id of the tree view to be deleted
     */
    export async function deleteTreeView(
        projectId: string,
        id: string
    ): Promise<DeleteTreeViewOutputModel> {
        return await fetch(`${API_BASE_URL}/projects/${projectId}/tree-views/${id}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res => {
            if (res.ok)
                return res.json();
            else
                throw new Error(res.statusText);
        });
    }
}
