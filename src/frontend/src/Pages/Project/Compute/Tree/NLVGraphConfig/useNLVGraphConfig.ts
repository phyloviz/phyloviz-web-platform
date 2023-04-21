import {useNavigate, useParams} from "react-router-dom"
import * as React from "react"
import {useState} from "react"
import {SelectChangeEvent} from "@mui/material"
import {useProjectContext} from "../../../useProject"
import {useCompute} from "../../useCompute"

/**
 * Hook for the NLVGraphConfig page.
 */
export function useNLVGraphConfig() {
    const navigate = useNavigate()
    const {projectId, datasetId} = useParams<{ projectId: string, datasetId: string }>()
    const {project} = useProjectContext()

    const [selectedDistance, setSelectedDistance] = useState<string | null>(null)
    const distances = project?.datasets
        .find((dataset) => dataset.datasetId === datasetId)
        ?.distanceMatrices ?? []

    const [currentMaxNLVLevel, setCurrentMaxNLVLevel] = useState<number>(1)
    const [innerEdges, setInnerEdges] = useState<boolean>(false)

    const {createWorkflow} = useCompute()

    return {
        distances,
        selectedDistance,
        handleDistanceChange: (event: SelectChangeEvent) => setSelectedDistance(event.target.value),

        currentMaxNLVLevel,
        handleMaxNLVLevelChange: (event: React.ChangeEvent<HTMLInputElement>) => setCurrentMaxNLVLevel(parseInt(event.target.value)),

        innerEdges,
        handleInnerEdgesChange: (event: React.ChangeEvent<HTMLInputElement>) => setInnerEdges(event.target.checked),

        handleCancel: () => navigate(-1),
        handleFinish: () => {
            // TODO:finish
        }
    }
}