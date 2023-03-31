/**
 * Contains the URIs of the Web API.
 */
export namespace WebApiUris {
    const API_BASE_URL = (port: number) => `http://localhost:${port}/api`;

    const GATEWAY_URL = API_BASE_URL(8083);
    const ADMINISTRATION_URL = API_BASE_URL(8088);
    const COMPUTE_URL = API_BASE_URL(8086);
    const VISUALIZATION_URL = API_BASE_URL(8085);


    export const getSession = `${GATEWAY_URL}/session`;
    export const logout = `${GATEWAY_URL}/logout`;

    export const getProjects = `${ADMINISTRATION_URL}/projects`;
    export const getProject = (projectId: string) => `${ADMINISTRATION_URL}/projects/${projectId}`;
    export const createProject = `${ADMINISTRATION_URL}/projects`;
    export const deleteProject = (projectId: string) => `${ADMINISTRATION_URL}/projects/${projectId}`;

    export const getDataset = (projectId: string, datasetId: string) => `${ADMINISTRATION_URL}/projects/${projectId}/datasets/${datasetId}`;
    export const createDataset = (projectId: string) => `${ADMINISTRATION_URL}/projects/${projectId}/datasets`;
    export const deleteDataset = (projectId: string, datasetId: string) => `${ADMINISTRATION_URL}/projects/${projectId}/datasets/${datasetId}`;
    export const getDatasets = (projectId: string) => `${ADMINISTRATION_URL}/projects/${projectId}/datasets`;

    export const uploadTypingData = (projectId: string) => `${ADMINISTRATION_URL}/projects/${projectId}/files/typing-data`;
    export const deleteTypingData = (projectId: string, typingDataId: string) => `${ADMINISTRATION_URL}/projects/${projectId}/typing-data/${typingDataId}`;

    export const uploadIsolateData = (projectId: string) => `${ADMINISTRATION_URL}/projects/${projectId}/files/isolate-data`;
    export const deleteIsolateData = (projectId: string, isolateDataId: string) => `${ADMINISTRATION_URL}/projects/${projectId}/isolate-data/${isolateDataId}`;

    export const deleteDistanceMatrix = (projectId: string, datasetId: string, distanceMatrixId: string) => `${ADMINISTRATION_URL}/projects/${projectId}/datasets/${datasetId}/distance-matrices/${distanceMatrixId}`;
    export const deleteTree = (projectId: string, datasetId: string, treeId: string) => `${ADMINISTRATION_URL}/projects/${projectId}/datasets/${datasetId}/trees/${treeId}`;
    export const deleteTreeView = (projectId: string, datasetId: string, treeViewId: string) => `${ADMINISTRATION_URL}/projects/${projectId}/datasets/${datasetId}/tree-views/${treeViewId}`;
}