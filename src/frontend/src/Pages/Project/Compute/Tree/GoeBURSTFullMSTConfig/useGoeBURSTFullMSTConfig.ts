import {useNavigate, useParams} from "react-router-dom"
import {useState} from "react"
import {SelectChangeEvent} from "@mui/material"
import ComputeService from "../../../../../Services/compute/ComputeService"
import {useProjectContext} from "../../../useProject"

/**
 * Hook for the GoeBURSTFullMSTConfig page.
 */
export function useGoeBURSTFullMSTConfig() {
    const navigate = useNavigate()
    const {projectId, datasetId} = useParams<{ projectId: string, datasetId: string }>()
    const {project, onProjectUpdate} = useProjectContext()

    const [selectedDistance, setSelectedDistance] = useState<string | null>(null)
    const distances = project?.datasets
        .find((dataset) => dataset.datasetId === datasetId)
        ?.distanceMatrices ?? []

    const [workflowId, setWorkflowId] = useState<string | null>(null)

    return {
        distances,
        selectedDistance,
        handleDistanceChange: (event: SelectChangeEvent) => setSelectedDistance(event.target.value),

        handleCancel: () => navigate(-1),
        handleFinish: () => {
            ComputeService.createWorkflow(
                projectId!,
                {
                    type: "compute-tree",
                    properties: {
                        datasetId: datasetId,
                        distanceMatrixId: selectedDistance,
                        algorithm: "goeburst-full-mst"
                    }
                }
            )
                .then((res) => setWorkflowId(res.workflowId))// TODO: Get status until finished
                .catch((err) => console.error(err)) // TODO: Handle Error
        }
    }
}