import {useNavigate, useParams} from "react-router-dom"
import {ComputeService} from "../../../../../Services/compute/ComputeService";
import {useState} from "react";

/**
 * Hook for the HammingDistanceConfig page.
 */
export function useComputeHammingDistance() {
    const navigate = useNavigate()
    const {projectId, datasetId} = useParams<{ projectId: string, datasetId: string }>()
    const [workflowId, setWorkflowId] = useState<string | null>(null)
    const [computing, setComputing] = useState<boolean>(false)
    const [error, setError] = useState<string | null>(null)

    async function handleCompute() {
        setComputing(true)
        try {
            const res = await ComputeService.createWorkflow(
                projectId!,
                {
                    type: "compute-distance-matrix",
                    properties: {
                        datasetId: datasetId,
                        function: "hamming"
                    }
                }
            )
            setWorkflowId(res.workflowId)

            // TODO: This file is a mess, I need help

        } catch (err) {
            setError((err as Error).message)
        } finally {
            setComputing(false)
        }
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
                .then((res) => setWorkflowId(res.workflowId))// TODO: Get status until finished
                .catch((err) => console.error(err)) // TODO: Handle Error
        }
    }
}