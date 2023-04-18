import {useNavigate, useParams} from "react-router-dom"
import {useState} from "react"
import {SelectChangeEvent} from "@mui/material"
import {useProjectContext} from "../../../useProject"
import {useCompute} from "../../useCompute"

/**
 * Hook for the GoeBURSTFullMSTConfig page.
 */
export function useGoeBURSTFullMSTConfig() {
    const navigate = useNavigate()
    const {projectId, datasetId} = useParams<{ projectId: string, datasetId: string }>()
    const {project} = useProjectContext()
    const {createWorkflow} = useCompute()

    const [selectedDistance, setSelectedDistance] = useState<string | null>(null)
    const distances = project?.datasets
        .find((dataset) => dataset.datasetId === datasetId)
        ?.distanceMatrices ?? []

    const [error, setError] = useState<string | null>(null)

    return {
        distances,
        selectedDistance,
        handleDistanceChange: (event: SelectChangeEvent) => setSelectedDistance(event.target.value),

        handleCancel: () => navigate(-1),
        handleFinish: () => {
            if (selectedDistance === null) {
                setError("Please select a distance matrix.")
                return
            }

            createWorkflow(
                {
                    type: "compute-tree",
                    properties: {
                        datasetId: datasetId,
                        distanceMatrixId: selectedDistance,
                        algorithm: "goeburst-full-mst"
                    }
                }
            )
        },
        error
    }
}