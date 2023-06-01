import {useState} from "react"
import ComputeService from "../../../Services/Compute/ComputeService"
import {CreateWorkflowInputModel} from "../../../Services/Compute/models/createWorkflow/CreateWorkflowInputModel"
import {useProjectContext} from "../useProject"
import {useNavigate} from "react-router-dom"

/**
 * Hook that handles computations.
 */
export function useCompute() {
    const navigate = useNavigate()
    const {project, onWorkflowsUpdate} = useProjectContext()
    const [error, setError] = useState<string | null>(null)

    /**
     * Creates a new workflow.
     *
     * @param workflow The workflow to create
     */
    function createWorkflow(workflow: CreateWorkflowInputModel) {
        ComputeService.createWorkflow(project?.projectId!, workflow)
            .catch((err) => setError(err.message))
            .finally(() => {
                onWorkflowsUpdate()
            })
    }

    return {
        createWorkflow,
        error,
        clearError: () => setError(null)
    }
}