export const API_BASE_URL = "http://localhost:8083/api" // TODO: Change this to the actual API endpoint

// TODO: Please, help me organize this file better, its a mess of URIs

/**
 * Contains the URIs of the Web API.
 */
export namespace WebApiUris {
    // TODO: Add the URIs of the Web API here to replace the ones hardcoded in the services
    export const getSession = `${API_BASE_URL}/session`;
    export const logout = `${API_BASE_URL}/logout`;
    export const login = '/oauth2/authorization/phyloviz-web-platform-client';

    export const getProjects = `${API_BASE_URL}/projects`;
    export const getProject = (projectId: string) => `${API_BASE_URL}/projects/${projectId}`;
    export const createProject = `${API_BASE_URL}/projects`;
    export const deleteProject = (projectId: string) => `${API_BASE_URL}/projects/${projectId}`;

    export const getDataset = (projectId: string, datasetId: string) => `${API_BASE_URL}/projects/${projectId}/datasets/${datasetId}`;
    export const createDataset = (projectId: string) => `${API_BASE_URL}/projects/${projectId}/datasets`;
    export const deleteDataset = (projectId: string, datasetId: string) => `${API_BASE_URL}/projects/${projectId}/datasets/${datasetId}`;

    export const uploadTypingData = (projectId: string) => `${API_BASE_URL}/projects/${projectId}/files/typing-data`;
    export const uploadIsolateData = (projectId: string) => `${API_BASE_URL}/projects/${projectId}/files/isolate-data`;
}