import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import ComputeService from "../../../Services/Compute/ComputeService";
import {Workflow} from "../../../Services/Compute/models/getWorkflowStatus/GetWorkflowStatusOutputModel";


/**
 * Hook for Workflow page.
 */
export function useWorkflow() {
    const pathParams = useParams<{ projectId: string, workflowId: string }>()
    const projectId = pathParams.projectId!
    const workflowId = pathParams.workflowId!

    const [workflow, setWorkflow] = useState<Workflow | undefined>(undefined)
    const [error, setError] = useState<string | undefined>(undefined)

    useEffect(() => {
        ComputeService.getWorkflowStatus(projectId, workflowId)
            .then(res => {
                setWorkflow(res)
            })
            .catch(err => {
                setError(err.message)
            })
    }, [projectId, workflowId])

    return {
        workflow,
        error
    }
}