import {useState} from "react"
import {useNavigate, useParams} from "react-router-dom"
import {SelectChangeEvent} from "@mui/material"
import {useProjectContext} from "../../../useProject"
import {useCompute} from "../../useCompute"

export enum GoeBURSTConfigurationStep {
    DISTANCE = "Distance",
    LEVEL = "Level",
}

/**
 * Hook for the GoeBURST configuration page.
 */
export function useGoeBURSTConfig() {
    const [step, setStep] = useState(GoeBURSTConfigurationStep.DISTANCE)
    const [currStep, setCurrStep] = useState(0)

    const navigate = useNavigate()
    const {projectId, datasetId} = useParams<{ projectId: string, datasetId: string }>()
    const {project} = useProjectContext()

    const [selectedDistance, setSelectedDistance] = useState<string | null>(null)
    const distances = project?.datasets
        .find((dataset) => dataset.datasetId === datasetId)
        ?.distanceMatrices ?? []

    const {createWorkflow} = useCompute()
    const [error, setError] = useState<string | null>(null)

    return {
        step,
        currStep,
        distances,
        selectedDistance,
        handleDistanceChange: (event: SelectChangeEvent) => setSelectedDistance(event.target.value),
        handleCancel: () => navigate(-1),
        handleBack: () => {
            if (step === GoeBURSTConfigurationStep.LEVEL) {
                setStep(GoeBURSTConfigurationStep.DISTANCE)
                setCurrStep(0)
            }
        },
        handleNext: () => {
            if (step === GoeBURSTConfigurationStep.DISTANCE) {
                setStep(GoeBURSTConfigurationStep.LEVEL)
                setCurrStep(1)
                return
            }

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
                        algorithm: "goeburst"
                    }
                }
            )
        },
        error
    }
}