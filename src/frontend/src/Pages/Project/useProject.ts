import {useNavigate, useOutletContext, useParams} from "react-router-dom"
import {useEffect, useState} from "react"
import {Project} from "../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import AdministrationService from "../../Services/Administration/AdministrationService"
import {GetWorkflowStatusOutputModel, Workflow} from "../../Services/Compute/models/getWorkflow/GetWorkflowOutputModel"
import ComputeService from "../../Services/Compute/ComputeService"
import {useInterval} from "../../Components/Shared/Hooks/useInterval"
import {WebUiUris} from "../WebUiUris";

/**
 * Context for the Project page.
 *
 * @property project the project to display
 * @property onFileStructureUpdate callback when the file structure has been updated
 * @property onWorkflowsUpdate callback when the workflows have been updated
 */
interface ProjectContext {
    project: Project | null,
    onFileStructureUpdate: () => void
    onWorkflowsUpdate: () => void
}


/**
 * Hook for the Project page.
 */
export function useProject() {
    const projectId = (useParams<{ projectId: string }>().projectId)!

    const [project, setProject] = useState<Project | null>(null)
    const [workflows, setWorkflows] = useState<GetWorkflowStatusOutputModel[]>([])

    const [loadingFiles, setLoadingFiles] = useState<boolean>(true)
    const [loadingWorkflows, setLoadingWorkflows] = useState<boolean>(true)

    const [error, setError] = useState<string | null>(null)
    const navigate = useNavigate()

    // TODO implement project structure only updating the needed places -> workflows only changing workflows folder, same for project
    useEffect(() => {
        loadProject()
        loadWorkflows()
    }, [projectId])

    function loadProject() {
        setLoadingFiles(true)
        AdministrationService.getProject(projectId)
            .then((res) => setProject(res))
            .catch((err: Error) => setError("Could not load project: " + err.message))
            .finally(() => setLoadingFiles(false))
    }

    function loadWorkflows() {
        setLoadingWorkflows(true)
        ComputeService.getWorkflows(projectId)
            .then(res => setWorkflows(res.workflows))
            .catch((err: Error) => {
                setError("Could not load workflows: Unexpected error.")
            })
            .finally(() => setLoadingWorkflows(false))
    }

    useInterval(pollWorkflows, 1000, [projectId, workflows, loadingWorkflows])

    /**
     * Polls the workflows for any changes.
     */
    async function pollWorkflows() {
        if (loadingWorkflows)
            return false

        // TODO implement something with better performance

        const updatedWorkflows = await ComputeService.getWorkflows(projectId!)
            .catch((err: Error) => {
                return null
            })

        if (updatedWorkflows === null)
            return false

        for (const updatedWorkflow of updatedWorkflows.workflows) {
            const workflow = workflows.find(w => w.workflowId === updatedWorkflow.workflowId)
            if (workflow === undefined || workflow.status !== updatedWorkflow.status) {
                loadWorkflows()
                return false
            }
        }

        return false
    }

    return {
        project,

        loadingFiles,
        onFileStructureUpdate: () => loadProject,
        handleEditProject: () => navigate(WebUiUris.editProject(projectId!)),

        workflows,
        loadingWorkflows,
        onWorkflowsUpdate: () => loadWorkflows,

        error,
        clearError: () => setError(null)
    }
}

/**
 * Hook to use the project context.
 */
export function useProjectContext() {
    return useOutletContext<ProjectContext>()
}