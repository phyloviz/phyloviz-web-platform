import {useNavigate, useParams} from "react-router-dom"
import * as React from "react"
import {useContext, useEffect, useState} from "react"
import {Project} from "../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import AdministrationService from "../../Services/Administration/AdministrationService"
import {GetWorkflowStatusOutputModel} from "../../Services/Compute/models/getWorkflow/GetWorkflowOutputModel"
import ComputeService from "../../Services/Compute/ComputeService"
import {useInterval} from "../../Components/Shared/Hooks/useInterval"
import {WebUiUris} from "../WebUiUris";

/**
 * Properties of the context for the Project page.
 *
 * @property project the project to display
 * @property onFileStructureUpdate callback when the file structure has been updated
 * @property onWorkflowsUpdate callback when the workflows have been updated
 */
export interface ProjectContextProps {
    project: Project | null
    onFileStructureUpdate: () => void
    onWorkflowsUpdate: () => void
}

/**
 * Context for the Project page.
 *
 * @property project the project to display
 * @property onFileStructureUpdate callback when the file structure has been updated
 * @property onWorkflowsUpdate callback when the workflows have been updated
 */
export const ProjectContext = React.createContext<ProjectContextProps>({
    project: null,
    onFileStructureUpdate: () => {
        console.log("being called for some reason")
    },
    onWorkflowsUpdate: () => {
    }
})


/**
 * Hook for the Project page.
 */
export function useProject() {
    const projectId = (useParams<{ projectId: string }>().projectId)!

    const [project, setProject] = useState<Project | null>(null)
    const [workflows, setWorkflows] = useState<GetWorkflowStatusOutputModel[]>([])

    const [loadingProject, setLoadingProject] = useState<boolean>(true)
    const [loadingWorkflows, setLoadingWorkflows] = useState<boolean>(true)

    const [error, setError] = useState<string | null>(null)
    const navigate = useNavigate()

    useEffect(() => {
        setLoadingProject(true)
        loadProject().finally(() => setLoadingProject(false))

        setLoadingWorkflows(true)
        loadWorkflows().finally(() => setLoadingWorkflows(false))
    }, [projectId])

    function loadProject() {
        return AdministrationService.getProject(projectId)
            .then((res) => setProject(res))
            .catch((err: Error) => setError("Could not load project: " + err.message))
    }

    function loadWorkflows() {
        return ComputeService.getWorkflows(projectId)
            .then(res => {
                setWorkflows(res.workflows)
                return res.workflows
            })
            .catch((err: Error) => {
                setError("Could not load workflows: Unexpected error.")
            })
    }

    useInterval(poll, 5000, [projectId])

    /**
     * Polls the workflows for any changes.
     */
    async function poll() {
        const updatedWorkflows = await ComputeService.getWorkflows(projectId)
            .then(res => {
                setWorkflows(res.workflows)
                return res.workflows
            })
            .catch((err: Error) => {
                setError("Could not load workflows: Unexpected error.")
            })

        if(updatedWorkflows === undefined) return false

        for (const updatedWorkflow of updatedWorkflows) {
            const workflow = workflows.find(w => w.workflowId === updatedWorkflow.workflowId)
            if (workflow === undefined || workflow.status !== updatedWorkflow.status) {
                loadProject()
                return false
            }
        }

        return false
    }

    return {
        project,

        loadingFiles: loadingProject,
        onFileStructureUpdate: () => loadProject(),
        handleEditProject: () => navigate(WebUiUris.editProject(projectId!)),

        workflows,
        loadingWorkflows,
        onWorkflowsUpdate: () => loadWorkflows(),

        error,
        clearError: () => setError(null)
    }
}

/**
 * Hook to use the project context.
 */
export function useProjectContext() {
    return useContext(ProjectContext)
}