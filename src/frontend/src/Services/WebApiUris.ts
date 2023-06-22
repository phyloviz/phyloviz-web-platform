/**
 * Contains the URIs of the Web API.
 */
export namespace WebApiUris {
    const API_BASE_URL = `http://localhost:8083/api`

    export const getSession = `${API_BASE_URL}/session`
    export const logout = `${API_BASE_URL}/logout`

    // Administation
    export const getProjects = `${API_BASE_URL}/projects`
    export const getProject = (projectId: string) => `${API_BASE_URL}/projects/${projectId}`
    export const createProject = `${API_BASE_URL}/projects`
    export const deleteProject = (projectId: string) => `${API_BASE_URL}/projects/${projectId}`
    export const updateProject = (projectId: string) => `${API_BASE_URL}/projects/${projectId}`

    export const getDataset = (projectId: string, datasetId: string) => `${API_BASE_URL}/projects/${projectId}/datasets/${datasetId}`
    export const createDataset = (projectId: string) => `${API_BASE_URL}/projects/${projectId}/datasets`
    export const deleteDataset = (projectId: string, datasetId: string) => `${API_BASE_URL}/projects/${projectId}/datasets/${datasetId}`
    export const getDatasets = (projectId: string) => `${API_BASE_URL}/projects/${projectId}/datasets`
    export const updateDataset = (projectId: string, datasetId: string) => `${API_BASE_URL}/projects/${projectId}/datasets/${datasetId}`
    export const setIsolateDataOfDataset = (projectId: string, datasetId: string) => `${API_BASE_URL}/projects/${projectId}/datasets/${datasetId}/isolateData`

    export const deleteDistanceMatrix = (projectId: string, datasetId: string, distanceMatrixId: string) => `${API_BASE_URL}/projects/${projectId}/datasets/${datasetId}/distance-matrices/${distanceMatrixId}`
    export const updateDistanceMatrix = (projectId: string, datasetId: string, distanceMatrixId: string) => `${API_BASE_URL}/projects/${projectId}/datasets/${datasetId}/distance-matrices/${distanceMatrixId}`

    export const deleteTree = (projectId: string, datasetId: string, treeId: string) => `${API_BASE_URL}/projects/${projectId}/datasets/${datasetId}/trees/${treeId}`
    export const updateTree = (projectId: string, datasetId: string, treeId: string) => `${API_BASE_URL}/projects/${projectId}/datasets/${datasetId}/trees/${treeId}`

    export const deleteTreeView = (projectId: string, datasetId: string, treeViewId: string) => `${API_BASE_URL}/projects/${projectId}/datasets/${datasetId}/tree-views/${treeViewId}`
    export const updateTreeView = (projectId: string, datasetId: string, treeViewId: string) => `${API_BASE_URL}/projects/${projectId}/datasets/${datasetId}/tree-views/${treeViewId}`

    export const deleteTypingData = (projectId: string, typingDataId: string) => `${API_BASE_URL}/projects/${projectId}/files/typing-data/${typingDataId}`
    export const updateTypingData = (projectId: string, typingDataId: string) => `${API_BASE_URL}/projects/${projectId}/files/typing-data/${typingDataId}`

    export const deleteIsolateData = (projectId: string, isolateDataId: string) => `${API_BASE_URL}/projects/${projectId}/files/isolate-data/${isolateDataId}`
    export const updateIsolateData = (projectId: string, isolateDataId: string) => `${API_BASE_URL}/projects/${projectId}/files/isolate-data/${isolateDataId}`


    // Compute
    export const createWorkflow = (projectId: string) => `${API_BASE_URL}/projects/${projectId}/workflows`
    export const getWorkflowStatus = (projectId: string, workflowId: string) => `${API_BASE_URL}/projects/${projectId}/workflows/${workflowId}/status`
    export const getWorkflow = (projectId: string, workflowId: string) => `${API_BASE_URL}/projects/${projectId}/workflows/${workflowId}`
    export const getWorkflows = (projectId: string) => `${API_BASE_URL}/projects/${projectId}/workflows`


    // Visualization 
    export const getTypingDataSchema = (projectId: string, typingDataId: string) => `${API_BASE_URL}/projects/${projectId}/files/typing-data/${typingDataId}/schema`
    export const getTypingDataProfiles = (projectId: string, typingDataId: string, limit: number, offset: number) => `${API_BASE_URL}/projects/${projectId}/files/typing-data/${typingDataId}/profiles?limit=${limit}&offset=${offset}`

    export const getIsolateDataKeys = (projectId: string, isolateDataId: string) => `${API_BASE_URL}/projects/${projectId}/files/isolate-data/${isolateDataId}/keys`
    export const getIsolateDataRows = (projectId: string, isolateDataId: string, limit: number, offset: number) => `${API_BASE_URL}/projects/${projectId}/files/isolate-data/${isolateDataId}/rows?limit=${limit}&offset=${offset}`

    export const getDistanceMatrix = (projectId: string, datasetId: string, distanceMatrixId: string) => `${API_BASE_URL}/projects/${projectId}/datasets/${datasetId}/distance-matrices/${distanceMatrixId}`
    export const getTree = (projectId: string, datasetId: string, treeId: string) => `${API_BASE_URL}/projects/${projectId}/datasets/${datasetId}/trees/${treeId}`
    export const getTreeView = (projectId: string, datasetId: string, treeViewId: string) => `${API_BASE_URL}/projects/${projectId}/datasets/${datasetId}/tree-views/${treeViewId}`
    export const saveTreeView = (projectId: string, datasetId: string, treeViewId: string) => `${API_BASE_URL}/projects/${projectId}/datasets/${datasetId}/tree-views/${treeViewId}`

    // File Transfer
    export const uploadTypingData = (projectId: string) => `${API_BASE_URL}/projects/${projectId}/files/typing-data`
    export const downloadTypingData = (projectId: string, typingDataId: string) => `${API_BASE_URL}/projects/${projectId}/files/typing-data/${typingDataId}/file`
    export const uploadIsolateData = (projectId: string) => `${API_BASE_URL}/projects/${projectId}/files/isolate-data`
    export const downloadIsolateData = (projectId: string, isolateDataId: string) => `${API_BASE_URL}/projects/${projectId}/files/isolate-data/${isolateDataId}/file`
}