import {useParams} from "react-router-dom"
import {useEffect, useState} from "react"
import {Workflow} from "../../../../Services/compute/models/getWorkflowStatus/GetWorkflowStatusOutputModel"
import ComputeService from "../../../../Services/compute/ComputeService"

/**
 * Hook for the WorkflowManager.
 */
export function useWorkflowManager() {
    const {projectId} = useParams<{ projectId: string }>()
    const [workflows, setWorkflows] = useState<Workflow[]>([])
    const [loading, setLoading] = useState<boolean>(true)
    const [error, setError] = useState<string | null>(null)
    const [updated, setUpdated] = useState<boolean>(false)

    useEffect(() => {
        setLoading(true)
        ComputeService.getWorkflows(projectId!)
            .then(res => setWorkflows(res.workflows))
            .catch(error => setError(error))
            .finally(() => setLoading(false))
    }, [projectId, updated])

    return {
        workflows,
        setUpdated,
        loading,
        error
    }
}