import * as React from "react";
import {useState} from "react";
import {DatasetType} from "../../../Domain/DatasetType";
import {useNavigate} from "react-router-dom";
import {SelectChangeEvent} from "@mui/material";
import {useProjectContext} from "../useProject";
import {AdministrationService} from "../../../Services/administration/AdministrationService";

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
    const [name, setName] = useState("");
    const [description, setDescription] = useState("");
    const [datasetType, setDatasetType] = useState(DatasetType.MLST);

    const [createDatasetStep, setCreateDatasetStep] = useState(CreateDatasetStep.INFO);
    const [currStep, setCurrStep] = useState(0);

    const navigate = useNavigate();

    const {project, onUpdated} = useProjectContext();

    const [selectedTypingData, setSelectedTypingData] = useState<string | null>(null);
    const [typingDataFile, setTypingDataFile] = useState<File | null>(null);

    const [selectedIsolateData, setSelectedIsolateData] = useState<string | null>(null);
    const [isolateDataKeys, setIsolateDataKeys] = useState<string[]>([]);
    const [selectedIsolateDataKey, setSelectedIsolateDataKey] = useState<string | null>(null);
    const [isolateDataFile, setIsolateDataFile] = useState<File | null>(null);

    return {
        datasetType,
        project,
        handleDatasetNameChange: (event: SelectChangeEvent) => setName(event.target.value as string),
        handleDatasetTypeChange: (event: SelectChangeEvent) => setDatasetType(event.target.value as DatasetType),
        selectedTypingData,
        handleTypingDataFileSelectorChange: (event: SelectChangeEvent) => setSelectedTypingData(event.target.value),
        handleTypingDataFileUploaderChange: (file: React.SetStateAction<File | null>) => setTypingDataFile(file),
        selectedIsolateData,
        handleIsolateDataFileSelectorChange: (event: SelectChangeEvent) => {
            setSelectedIsolateData(event.target.value);
            // TODO: get keys
        },
        handleIsolateDataFileUploaderChange: (file: React.SetStateAction<File | null>) => {
            setIsolateDataFile(file)
            // TODO: get keys
        },
        isolateDataKeys,
        selectedIsolateDataKey,
        handleIsolateDataKeyChange: (event: SelectChangeEvent) => setSelectedIsolateDataKey(event.target.value),
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
            } else {
                // TODO: Validate data
                AdministrationService.createDataset(
                    project?.projectId!,
                    {
                        name,
                        description,
                        typingDataId: selectedTypingData!,
                        isolateDataId: selectedIsolateData, // TODO: And the key? Maybe we should send the key here
                    }
                )
                    .then(() => {
                        onUpdated();
                        navigate(-1);
                    })
                    .catch(console.error);
            }
        },
        createDatasetStep,
        currStep
    }
}