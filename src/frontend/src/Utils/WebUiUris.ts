/**
 * Contains the URIs of the Web UI.
 */
export namespace WebUiUris {
    export const HOME = "/"
    export const ABOUT = "/about"
    export const NEWS = "/news"
    export const API_INFO = "/api-info"
    export const PROFILE = "/profile"
    export const LOGIN = "/oauth2/authorization/phyloviz-web-platform-client"

    export const NEW_PROJECT = "/new-project"
    export const OPEN_PROJECT = "/open-project"

    export const PROJECT = "/projects/:projectId"
    export const CREATE_DATASET = `${PROJECT}/create-dataset`
    export const UPLOAD_FILES = `${PROJECT}/upload-files`
    export const DATASET = `${PROJECT}/datasets/:datasetId`
    export const TYPING_DATA = `${DATASET}/typing-data`
    export const ISOLATE_DATA = `${DATASET}/isolate-data`
    export const DISTANCE_MATRIX = `${DATASET}/distance-matrices/:distanceMatrixId`
    export const TREE = `${DATASET}/trees/:treeId`
    export const TREE_VIEW = `${DATASET}/tree-views/:treeViewId`

    export const COMPUTE_CONFIG_GOEBURST = `${DATASET}/compute-configs/goeburst`
    export const COMPUTE_CONFIG_GOEBURST_FULL_MST = `${DATASET}/compute-configs/goeburst-full-mst`
    export const COMPUTE_CONFIG_HIERARCHICAL_CLUSTERING = `${DATASET}/compute-configs/hierarchical-clustering`
    export const COMPUTE_CONFIG_NEIGHBOR_JOINING = `${DATASET}/compute-configs/neighbor-joining`
    export const COMPUTE_CONFIG_NLV_GRAPH = `${DATASET}/compute-configs/nlv-graph`

    export const project = (projectId: string) => `/projects/${projectId}`
    export const dataset = (projectId: string, datasetId: string) => `/projects/${projectId}/datasets/${datasetId}`
    export const distanceMatrix = (projectId: string, datasetId: string, distanceMatrixId: string) => `/projects/${projectId}/datasets/${datasetId}/distance-matrices/${distanceMatrixId}`
    export const tree = (projectId: string, datasetId: string, treeId: string) => `/projects/${projectId}/datasets/${datasetId}/trees/${treeId}`
    export const treeView = (projectId: string, datasetId: string, treeViewId: string) => `/projects/${projectId}/datasets/${datasetId}/tree-views/${treeViewId}`

    export const createDataset = (projectId: string) => `/projects/${projectId}/create-dataset`
    export const uploadFiles = (projectId: string) => `/projects/${projectId}/upload-files`

    export const computeConfigGoeburst = (projectId: string, datasetId: string) => `/projects/${projectId}/datasets/${datasetId}/compute-configs/goeburst`
    export const computeConfigGoeburstFullMst = (projectId: string, datasetId: string) => `/projects/${projectId}/datasets/${datasetId}/compute-configs/goeburst-full-mst`
    export const computeConfigHierarchicalClustering = (projectId: string, datasetId: string) => `/projects/${projectId}/datasets/${datasetId}/compute-configs/hierarchical-clustering`
    export const computeConfigNeighborJoining = (projectId: string, datasetId: string) => `/projects/${projectId}/datasets/${datasetId}/compute-configs/neighbor-joining`
    export const computeConfigNlvGraph = (projectId: string, datasetId: string) => `/projects/${projectId}/datasets/${datasetId}/compute-configs/nlv-graph`
}
