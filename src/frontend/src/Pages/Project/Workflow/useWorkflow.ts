import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import ComputeService from "../../../Services/Compute/ComputeService";
import {Workflow} from "../../../Services/Compute/models/getWorkflow/GetWorkflowOutputModel";


/**
 * Hook for Workflow page.
 */
export function useWorkflow() {
    const pathParams = useParams<{ projectId: string, workflowId: string }>()
    const projectId = pathParams.projectId!
    const workflowId = pathParams.workflowId!

    const [workflow, setWorkflow] = useState<Workflow | undefined>(undefined)
    const [loading, setLoading] = useState<boolean>(true)
    const [error, setError] = useState<string | null>(null)

    useEffect(() => {
        setLoading(true)
        ComputeService.getWorkflow(projectId, workflowId)
            .then(res => setWorkflow(res))
            .catch(err => setError(err.message))
            .finally(() => setLoading(false))
    }, [projectId, workflowId])

    return {
        workflow,
        loading,
        error,
        clearError: () => setError(null)
    }
}