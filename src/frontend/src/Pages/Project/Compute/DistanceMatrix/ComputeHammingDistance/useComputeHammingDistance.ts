import {useNavigate, useParams} from "react-router-dom"
import {useCompute} from "../../useCompute"

/**
 * Hook for the ComputeHammingDistance page.
 */
export function useComputeHammingDistance() {
    const navigate = useNavigate()
    const {projectId, datasetId} = useParams<{ projectId: string, datasetId: string }>()
    const {createWorkflow, error, clearError} = useCompute()

    return {
        handleCancel: () => navigate(-1),
        handleCompute: () => createWorkflow(
            {
                type: "compute-distance-matrix",
                properties: {
                    datasetId: datasetId,
                    function: "hamming"
                }
            }
        ),
        error,
        clearError
    }
}