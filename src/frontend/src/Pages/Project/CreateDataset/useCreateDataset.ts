import {ChangeEvent, useState} from "react";
import {DatasetType} from "../../../Domain/DatasetType";
import {useNavigate} from "react-router-dom";
import {SelectChangeEvent} from "@mui/material";

export enum CreateDatasetStep {
    INFO = "Dataset Info",
    TYPING_DATA = "Typing Data",
    ISOLATE_DATA = "Isolate Data",
}

// TODO: Sus, criei para fazer o stepper
export const steps = [
    'Dataset Info',
    'Typing Data',
    'Isolate Data',
];

/**
 * Hook for the CreateDataset page.
 */
export function useCreateDataset() {
    const [datasetType, setDatasetType] = useState(DatasetType.MLST);
    const [createDatasetStep, setCreateDatasetStep] = useState(CreateDatasetStep.INFO);
    const [currStep, setCurrStep] = useState(0);
    const navigate = useNavigate();

    return {
        datasetType,
        handleDatasetTypeChange: (event: SelectChangeEvent) => {
            setDatasetType(event.target.value as DatasetType);
        },
        handleTypingDataChange: (event: ChangeEvent<{ value: unknown }>) => {
            // TODO
        },
        handleIsolateDataChange: (event: SelectChangeEvent) => {
            // TODO
        },
        handleCancel: () => navigate(-1),
        handleBack: () => {
            if (createDatasetStep === CreateDatasetStep.TYPING_DATA) {
                setCreateDatasetStep(CreateDatasetStep.INFO)
                setCurrStep(0)
            } else if (createDatasetStep === CreateDatasetStep.ISOLATE_DATA) {
                setCreateDatasetStep(CreateDatasetStep.TYPING_DATA)
                setCurrStep(1)
            }
        },
        handleNext: () => {
            if (createDatasetStep === CreateDatasetStep.INFO) {
                setCreateDatasetStep(CreateDatasetStep.TYPING_DATA)
                setCurrStep(1)
            } else if (createDatasetStep === CreateDatasetStep.TYPING_DATA) {
                setCreateDatasetStep(CreateDatasetStep.ISOLATE_DATA)
                setCurrStep(2)
            }
            // TODO: else, finish
        },
        createDatasetStep,
        currStep
    }
}