/**
 * Contains the URIs of the Web UI.
 */
export namespace WebUiUris { // TODO: I think some of these URIs are sus, we should probably change some of them
    export const HOME = "/"
    export const ABOUT = "/about"
    export const NEWS = "/news"
    export const API_INFO = "/api-info"
    export const PROFILE = "/profile"
    export const LOGIN = "/oauth2/authorization/phyloviz-web-platform-client"

    export const NEW_PROJECT = "/new-project"
    export const OPEN_PROJECT = "/open-project"

    export const PROJECT = "/projects/:projectId"
    export const LOAD_DATASET = `${PROJECT}/load-dataset`
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
}
