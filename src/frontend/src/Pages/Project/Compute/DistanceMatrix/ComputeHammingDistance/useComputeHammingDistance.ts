import {useNavigate, useParams} from "react-router-dom"
import ComputeService from "../../../../../Services/compute/ComputeService"
import {useState} from "react"
import {useInterval} from "../../../../../Components/Shared/Hooks/useInterval"
import {WebUiUris} from "../../../../WebUiUris"

/**
 * Hook for the ComputeHammingDistance page.
 */
export function useComputeHammingDistance() {
    const navigate = useNavigate()
    const {projectId, datasetId} = useParams<{ projectId: string, datasetId: string }>()
    const [workflowId, setWorkflowId] = useState<string | null>(null)
    const [computing, setComputing] = useState<boolean>(false)
    const [error, setError] = useState<string | null>(null)

    useInterval(checkIfWorkflowFinished, 1000, [workflowId])

    async function checkIfWorkflowFinished() {
        if (workflowId === null)
            return true

        const workflow = await ComputeService.getWorkflowStatus(projectId!, workflowId)
        if (workflow.status === "COMPLETED") {
            setComputing(false)
            navigate(WebUiUris.distanceMatrix(projectId!, datasetId!, workflow.data!.distanceMatrixId))
            return true
        }

        return false
    }

    return {
        handleCancel: () => navigate(-1),
        handleCompute: () => {
            setComputing(true)
            ComputeService.createWorkflow(
                projectId!,
                {
                    type: "compute-distance-matrix",
                    properties: {
                        datasetId: datasetId,
                        function: "hamming"
                    }
                }
            )
                .then((res) => setWorkflowId(res.workflowId))
                .catch((err) => setError(err.message))
        },
        computing,
        error
    }
}