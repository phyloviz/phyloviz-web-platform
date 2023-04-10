import {useState} from "react"
import {useNavigate} from "react-router-dom"
import {SelectChangeEvent} from "@mui/material";

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

    const [selectedDistance, setSelectedDistance] = useState<string | null>(null)

    return {
        step,
        currStep,
        distances: [], // TODO: To be implemented
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
            }
            // TODO: else, finish
        }
    }
}