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
    export const EDIT_PROJECT = `${PROJECT}/edit`
    export const CREATE_DATASET = `${PROJECT}/create-dataset`
    export const UPLOAD_FILES = `${PROJECT}/upload-files`

    export const TYPING_DATA = `${PROJECT}/typing-data/:typingDataId`
    export const ISOLATE_DATA = `${PROJECT}/isolate-data/:isolateDataId`

    export const DATASET = `${PROJECT}/datasets/:datasetId`
    export const EDIT_DATASET = `${DATASET}/edit`

    export const DISTANCE_MATRIX = `${DATASET}/distance-matrices/:distanceMatrixId`
    export const TREE = `${DATASET}/trees/:treeId`
    export const TREE_VIEW = `${DATASET}/tree-views/:treeViewId`
    export const TREE_VIEW_TYPING_DATA = `${TREE_VIEW}/typing-data`
    export const TREE_VIEW_ISOLATE_DATA = `${TREE_VIEW}/isolate-data`
    export const REPORT = `${DATASET}/report`

    export const COMPUTE = `${DATASET}/compute`
    export const COMPUTE_DISTANCE_MATRIX = `${COMPUTE}/distance-matrix`
    export const COMPUTE_TREE = `${COMPUTE}/tree`
    export const COMPUTE_TREE_VIEW = `${COMPUTE}/tree-view`

    export const COMPUTE_HAMMING_DISTANCE = `${COMPUTE_DISTANCE_MATRIX}/hamming-distance`

    export const COMPUTE_GOEBURST = `${COMPUTE_TREE}/goeburst`
    export const COMPUTE_GOEBURST_FULL_MST = `${COMPUTE_TREE}/goeburst-full-mst`
    export const COMPUTE_HIERARCHICAL_CLUSTERING = `${COMPUTE_TREE}/hierarchical-clustering`
    export const COMPUTE_NEIGHBOR_JOINING = `${COMPUTE_TREE}/neighbor-joining`
    export const COMPUTE_NLV_GRAPH = `${COMPUTE_TREE}/nlv-graph`

    export const project = (projectId: string) => `/projects/${projectId}`
    export const editProject = (projectId: string) => `/projects/${projectId}/edit`
    export const createDataset = (projectId: string) => `/projects/${projectId}/create-dataset`
    export const uploadFiles = (projectId: string) => `/projects/${projectId}/upload-files`

    export const typingData = (projectId: string, typingDataId: string) => `/projects/${projectId}/typing-data/${typingDataId}`
    export const isolateData = (projectId: string, isolateDataId: string) => `/projects/${projectId}/isolate-data/${isolateDataId}`

    export const dataset = (projectId: string, datasetId: string) => `/projects/${projectId}/datasets/${datasetId}`
    export const editDataset = (projectId: string, datasetId: string) => `/projects/${projectId}/datasets/${datasetId}/edit`

    export const distanceMatrix = (projectId: string, datasetId: string, distanceMatrixId: string) => `/projects/${projectId}/datasets/${datasetId}/distance-matrices/${distanceMatrixId}`
    export const tree = (projectId: string, datasetId: string, treeId: string) => `/projects/${projectId}/datasets/${datasetId}/trees/${treeId}`
    export const treeView = (projectId: string, datasetId: string, treeViewId: string) => `/projects/${projectId}/datasets/${datasetId}/tree-views/${treeViewId}`
    export const treeViewTypingData = (projectId: string, datasetId: string, treeViewId: string) => `/projects/${projectId}/datasets/${datasetId}/tree-views/${treeViewId}/typing-data`
    export const treeViewIsolateData = (projectId: string, datasetId: string, treeViewId: string) => `/projects/${projectId}/datasets/${datasetId}/tree-views/${treeViewId}/isolate-data`
    export const report = (projectId: string, datasetId: string) => `/projects/${projectId}/datasets/${datasetId}/report`

    export const computeHammingDistance = (projectId: string, datasetId: string) => `/projects/${projectId}/datasets/${datasetId}/compute/distance-matrix/hamming-distance`

    export const computeGoeburst = (projectId: string, datasetId: string) => `/projects/${projectId}/datasets/${datasetId}/compute/tree/goeburst`
    export const computeGoeburstFullMst = (projectId: string, datasetId: string) => `/projects/${projectId}/datasets/${datasetId}/compute/tree/goeburst-full-mst`
    export const computeHierarchicalClustering = (projectId: string, datasetId: string) => `/projects/${projectId}/datasets/${datasetId}/compute/tree/hierarchical-clustering`
    export const computeNeighborJoining = (projectId: string, datasetId: string) => `/projects/${projectId}/datasets/${datasetId}/compute/tree/neighbor-joining`
    export const computeNlvGraph = (projectId: string, datasetId: string) => `/projects/${projectId}/datasets/${datasetId}/compute/tree/nlv-graph`

    export const computeTreeView = (projectId: string, datasetId: string) => `/projects/${projectId}/datasets/${datasetId}/compute/tree-view`
}
