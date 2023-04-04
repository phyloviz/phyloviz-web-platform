/**
 * Contains the URIs of the Web API.
 */
export namespace WebApiUris {
    const API_BASE_URL = `http://localhost:8083/api`

    export const getSession = `${API_BASE_URL}/session`
    export const logout = `${API_BASE_URL}/logout`

    export const getProjects = `${API_BASE_URL}/projects`
    export const getProject = (projectId: string) => `${API_BASE_URL}/projects/${projectId}`
    export const createProject = `${API_BASE_URL}/projects`
    export const deleteProject = (projectId: string) => `${API_BASE_URL}/projects/${projectId}`

    export const getDataset = (projectId: string, datasetId: string) => `${API_BASE_URL}/projects/${projectId}/datasets/${datasetId}`
    export const createDataset = (projectId: string) => `${API_BASE_URL}/projects/${projectId}/datasets`
    export const deleteDataset = (projectId: string, datasetId: string) => `${API_BASE_URL}/projects/${projectId}/datasets/${datasetId}`
    export const getDatasets = (projectId: string) => `${API_BASE_URL}/projects/${projectId}/datasets`

    export const uploadTypingData = (projectId: string) => `${API_BASE_URL}/projects/${projectId}/files/typing-data`
    export const deleteTypingData = (projectId: string, typingDataId: string) => `${API_BASE_URL}/projects/${projectId}/typing-data/${typingDataId}`

    export const uploadIsolateData = (projectId: string) => `${API_BASE_URL}/projects/${projectId}/files/isolate-data`
    export const deleteIsolateData = (projectId: string, isolateDataId: string) => `${API_BASE_URL}/projects/${projectId}/isolate-data/${isolateDataId}`

    export const deleteDistanceMatrix = (projectId: string, datasetId: string, distanceMatrixId: string) => `${API_BASE_URL}/projects/${projectId}/datasets/${datasetId}/distance-matrices/${distanceMatrixId}`
    export const deleteTree = (projectId: string, datasetId: string, treeId: string) => `${API_BASE_URL}/projects/${projectId}/datasets/${datasetId}/trees/${treeId}`
    export const deleteTreeView = (projectId: string, datasetId: string, treeViewId: string) => `${API_BASE_URL}/projects/${projectId}/datasets/${datasetId}/tree-views/${treeViewId}`
}