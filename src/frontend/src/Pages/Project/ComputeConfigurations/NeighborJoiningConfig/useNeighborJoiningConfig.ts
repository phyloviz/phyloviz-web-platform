import {useState} from "react";
import {useNavigate} from "react-router-dom";

export enum NeighborJoiningConfigurationStep {
    DISTANCE = "Distance",
    METHOD = "Method",
}

// TODO: Sus, criei para fazer o stepper
export const steps = [
    'Distance',
    'Method',
];

/**
 * Hook for the NeighborJoiningConfig page.
 */
export function useNeighborJoiningConfig() {
    const [step, setStep] = useState(NeighborJoiningConfigurationStep.DISTANCE);
    const [currStep, setCurrStep] = useState(0);
    const navigate = useNavigate();

    return {
        step,
        currStep,
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