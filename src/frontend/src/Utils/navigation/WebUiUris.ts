/**
 * Contains the URIs of the Web UI.
 */
export namespace WebUiUris {
    export const HOME = "/"
    export const ABOUT = "/about"
    export const NEWS = "/news"
    export const API_INFO = "/api-info"
    export const PROFILE = "/profile"

    export const LOGIN = "/login"
    export const REGISTER = "/register"

    export const NEW_PROJECT = "/new-project"
    export const OPEN_PROJECT = "/open-project"
    export const PROJECT = "/projects/:projectId"
    export const LOAD_DATASET = "/projects/:projectId/load-dataset"
    export const UPLOAD_FILES = "/projects/:projectId/upload-files"
    export const TYPING_DATA = "/projects/:projectId/datasets/:datasetId/typing-data"
    export const ISOLATE_DATA = "/projects/:projectId/datasets/:datasetId/isolate-data"
    export const DISTANCE_MATRIX = "/projects/:projectId/datasets/:datasetId/distance-matrices/:distanceMatrixId"
    export const TREE = "/projects/:projectId/datasets/:datasetId/trees/:treeId"
    export const TREE_VIEW = "/projects/:projectId/datasets/:datasetId/tree-views/:treeViewId"

    export const project = (projectId: string) => `/projects/${projectId}`
}
