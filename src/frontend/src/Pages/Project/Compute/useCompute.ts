import {useState} from "react"
import ComputeService from "../../../Services/compute/ComputeService"
import {CreateWorkflowInputModel} from "../../../Services/compute/models/createWorkflow/CreateWorkflowInputModel"
import {useProjectContext} from "../useProject"
import {useNavigate} from "react-router-dom"

/**
 * Hook that handles computations.
 */
export function useCompute() {
    const navigate = useNavigate()
    const {project, onFileStructureUpdate, onWorkflowsUpdate} = useProjectContext()
    const [error, setError] = useState<string | null>(null)

    /**
     * Creates a new workflow.
     *
     * @param workflow
     */
    function createWorkflow(workflow: CreateWorkflowInputModel) {
        ComputeService.createWorkflow(project?.projectId!, workflow)
            .catch((err) => setError(err.message))
            .finally(() => {
                onWorkflowsUpdate()
                navigate(-1)
            })
    }

    return {
        createWorkflow,
        error
    }
}