import {useState} from "react"
import {useNavigate, useParams} from "react-router-dom"
import {useProjectContext} from "../../../useProject";
import {SelectChangeEvent} from "@mui/material";
import {GoeBURSTConfigurationStep} from "../GoeBURSTConfig/useGoeBURSTConfig";

export enum NeighborJoiningConfigurationStep {
    DISTANCE = "Distance",
    METHOD = "Method",
}

/**
 * Hook for the NeighborJoiningConfig page.
 */
export function useNeighborJoiningConfig() {
    const [step, setStep] = useState(NeighborJoiningConfigurationStep.DISTANCE)
    const [currStep, setCurrStep] = useState(0)

    const navigate = useNavigate()
    const {projectId, datasetId} = useParams<{ projectId: string, datasetId: string }>()
    const {project, onProjectUpdate} = useProjectContext()

    const [selectedDistance, setSelectedDistance] = useState<string | null>(null)
    const distances = project?.datasets
        .find((dataset) => dataset.datasetId === datasetId)
        ?.distanceMatrices ?? []

    const [workflowId, setWorkflowId] = useState<string | null>(null)

    return {
        step,
        currStep,

        distances,
        selectedDistance,
        handleDistanceChange: (event: SelectChangeEvent) => setSelectedDistance(event.target.value),

        handleCancel: () => navigate(-1),
        handleBack: () => {
            if (step === NeighborJoiningConfigurationStep.METHOD) {
                setStep(NeighborJoiningConfigurationStep.DISTANCE)
                setCurrStep(0)
            }
        },
        handleNext: () => {
            if (step === NeighborJoiningConfigurationStep.DISTANCE) {
                setStep(NeighborJoiningConfigurationStep.METHOD)
                setCurrStep(1)
            }
            // TODO: else, finish
        }
    }
}