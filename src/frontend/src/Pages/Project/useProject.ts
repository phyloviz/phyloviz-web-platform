import {useOutletContext, useParams} from "react-router-dom"
import {useEffect, useState} from "react"
import {Project} from "../../Services/administration/models/getProject/GetProjectOutputModel"
import AdministrationService from "../../Services/administration/AdministrationService"
import {Workflow} from "../../Services/compute/models/getWorkflowStatus/GetWorkflowStatusOutputModel"
import ComputeService from "../../Services/compute/ComputeService"
import {useInterval} from "../../Components/Shared/Hooks/useInterval"

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
        console.log("Updating workflows: " + workflows.length)
        if (workflows.length === 0)
            return false

        for (const workflow of workflows) {
            const updatedWorkflow = await ComputeService.getWorkflowStatus(projectId!, workflow.workflowId)
            if (updatedWorkflow.status !== workflow.status) {
                console.log("Workflow status changed")
                setWorkflowsUpdated(workflowsUpdated => !workflowsUpdated)
                setFilesUpdated(filesUpdated => !filesUpdated)
            }
        }

        return false
    }

    return {
        project,

        loadingFiles,
        onFileStructureUpdate: () => setFilesUpdated(filesUpdated => !filesUpdated),

        workflows,
        loadingWorkflows,
        onWorkflowsUpdate: () => setWorkflowsUpdated(workflowsUpdated => !workflowsUpdated),

        error
    }
}

/**
 * Hook to use the project context.
 */
export function useProjectContext() {
    return useOutletContext<ProjectContext>()
}