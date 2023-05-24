import {useNavigate, useOutletContext, useParams} from "react-router-dom"
import {useEffect, useState} from "react"
import {Project} from "../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import AdministrationService from "../../Services/Administration/AdministrationService"
import {Workflow} from "../../Services/Compute/models/getWorkflowStatus/GetWorkflowStatusOutputModel"
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
    const {projectId} = useParams<{ projectId: string }>()

    const [project, setProject] = useState<Project | null>(null)
    const [workflows, setWorkflows] = useState<Workflow[]>([])

    const [filesUpdated, setFilesUpdated] = useState<boolean>(false)
    const [loadingFiles, setLoadingFiles] = useState<boolean>(true)

    const [workflowsUpdated, setWorkflowsUpdated] = useState<boolean>(false)
    const [loadingWorkflows, setLoadingWorkflows] = useState<boolean>(true)

    const [error, setError] = useState<string | null>(null)
    const navigate = useNavigate()

    // TODO implement seamless loading / refresh of project file structure
    useEffect(() => {
        if (projectId === undefined)
            throw new Error("Project id is undefined")

        setLoadingFiles(true)

        AdministrationService.getProject(projectId)
            .then((res) => setProject(res))
            .catch((err: Error) => setError("Could not load project: " + err.message))
            .finally(() => setLoadingFiles(false))
    }, [projectId, filesUpdated])

    useEffect(() => {
        setLoadingWorkflows(true)
        ComputeService.getWorkflows(projectId!)
            .then(res => setWorkflows(res.workflows))
            .catch((err: Error) => setError("Could not load workflows: " + err.message))
            .finally(() => setLoadingWorkflows(false))
    }, [projectId, workflowsUpdated])


    useInterval(updateWorkflows, 1000, [projectId, workflows, workflowsUpdated])

    /**
     * Updates the workflows.
     */
    async function updateWorkflows() {
        if (workflows.length === 0)
            return false

        // TODO implement something with better performance

        const updatedWorkflows = await ComputeService.getWorkflows(projectId!)
        for (const updatedWorkflow of updatedWorkflows.workflows) {
            const workflow = workflows.find(w => w.workflowId === updatedWorkflow.workflowId)
            if (workflow === undefined || workflow.status !== updatedWorkflow.status) {
                console.log("NEED UPDATE WORKFLOWS")
                setWorkflowsUpdated(workflowsUpdated => !workflowsUpdated)
                setFilesUpdated(filesUpdated => !filesUpdated)
                return false
            }
        }

        return false
    }

    return {
        project,

        loadingFiles,
        onFileStructureUpdate: () => setFilesUpdated(filesUpdated => !filesUpdated),
        handleEditProject: () => navigate(WebUiUris.editProject(projectId!)),

        workflows,
        loadingWorkflows,
        onWorkflowsUpdate: () => setWorkflowsUpdated(workflowsUpdated => !workflowsUpdated),

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