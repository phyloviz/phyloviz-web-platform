import {useState} from "react";
import {useNavigate} from "react-router-dom";

export enum GoeBURSTConfigurationStep {
    DISTANCE = "Distance",
    LEVEL = "Level",
}

// TODO: Sus, criei para fazer o stepper
export const steps = [
    'Distance',
    'Level',
];

/**
 * Hook for the GoeBURST configuration page.
 */
export function useGoeBURSTConfig() {
    const [step, setStep] = useState(GoeBURSTConfigurationStep.DISTANCE);
    const [currStep, setCurrStep] = useState(0);
    const navigate = useNavigate();

    return {
        step,
        currStep,
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