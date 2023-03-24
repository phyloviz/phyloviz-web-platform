/**
 * Contains the URIs of the web UI.
 */
export namespace Uris {
    export const HOME = "/"
    export const ABOUT = "/about"

    export const LOGIN = "/login"
    export const REGISTER = "/register"

    export const NEWS = "/news"
    export const API_INFO = "/apiInfo"
    export const LOAD_DATASET = "/loadDataset"
    export const NEW_PROJECT = "/newProject"
    export const OPEN_PROJECT = "/openProject"
    export const PROJECT = "/project/:projectId"
    export const PROFILE = "/profile"

    export const project = (projectId: string) => `/project/${projectId}`
}
