import {useState} from "react"
import {useNavigate} from "react-router-dom"

export enum HierarchicalClusteringConfigStep {
    DISTANCE = "Distance",
    METHOD = "Method",
}

/**
 * Hook for the HierarchicalClusteringConfig page.
 */
export function useHierarchicalClusteringConfig() {
    const [step, setStep] = useState(HierarchicalClusteringConfigStep.DISTANCE)
    const [currStep, setCurrStep] = useState(0)
    const navigate = useNavigate()

    return {
        step,
        currStep,
        handleCancel: () => navigate(-1),
        handleBack: () => {
            if (step === HierarchicalClusteringConfigStep.METHOD) {
                setStep(HierarchicalClusteringConfigStep.DISTANCE)
                setCurrStep(0)
            }
        },
        handleNext: () => {
            if (step === HierarchicalClusteringConfigStep.DISTANCE) {
                setStep(HierarchicalClusteringConfigStep.METHOD)
                setCurrStep(1)
            }
            // TODO: else, finish
        }
    }
}