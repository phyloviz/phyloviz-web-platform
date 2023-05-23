import * as React from "react"
import {useState} from "react"
import {useNavigate} from "react-router-dom"
import {SelectChangeEvent} from "@mui/material"
import {useProjectContext} from "../useProject"
import AdministrationService from "../../../Services/Administration/AdministrationService"
import FileTransferService from "../../../Services/FileTransfer/FileTransferService";

export enum CreateDatasetStep {
    INFO = "Dataset Info",
    TYPING_DATA = "Typing Data",
    ISOLATE_DATA = "Isolate Data",
}

/**
 * The type of typing data.
 */
export enum TypingDataType {
    MLST = "Multi-Locus Sequence Typing (MLST)",
    MLVA = "Multi-Locus Variable Number Tandem Repeat Analysis (MLVA)",
    FASTA = "Aligned Sequences (FASTA)",
    ALIGNED_SEQUENCES = "Aligned Sequences",
    SNP = "Single Nucleotide Polymorphism (SNP)",
    SNP_WITHOUT_EXPLICIT_ID = "Single Nucleotide Polymorphism (SNP) (without explicit ID)",
}


/**
 * Hook for the CreateDataset page.
 */
export function useCreateDataset() {
    const [name, setName] = useState("")
    const [description, setDescription] = useState("")
    const [datasetType, setDatasetType] = useState(TypingDataType.MLST)

    const [createDatasetStep, setCreateDatasetStep] = useState(CreateDatasetStep.INFO)
    const [currStep, setCurrStep] = useState(0)

    const navigate = useNavigate()

    const {project, onFileStructureUpdate} = useProjectContext()

    const [selectedTypingData, setSelectedTypingData] = useState<string | null>(null)
    const [typingDataFile, setTypingDataFile] = useState<File | null>(null)

    const [selectedIsolateData, setSelectedIsolateData] = useState<string | null>(null)
    const [isolateDataKeys, setIsolateDataKeys] = useState<string[]>([])
    const [selectedIsolateDataKey, setSelectedIsolateDataKey] = useState<string | null>(null)
    const [isolateDataFile, setIsolateDataFile] = useState<File | null>(null)

    const [error, setError] = useState<string | null>(null)

    return {
        datasetType,
        project,
        handleDatasetNameChange: (event: SelectChangeEvent) => setName(event.target.value),
        handleDatasetDescriptionChange: (event: SelectChangeEvent) => setDescription(event.target.value),
        handleDatasetTypeChange: (event: SelectChangeEvent) => setDatasetType(event.target.value as TypingDataType),
        selectedTypingData,
        handleTypingDataFileSelectorChange: (event: SelectChangeEvent) => {
            setSelectedTypingData(event.target.value)
            if (typingDataFile)
                setTypingDataFile(null)
        },
        handleTypingDataFileUploaderChange: (file: React.SetStateAction<File | null>) => {
            setTypingDataFile(file)
            if (selectedTypingData)
                setSelectedTypingData(null)
        },
        selectedIsolateData,
        handleIsolateDataFileSelectorChange: (event: SelectChangeEvent) => {
            setSelectedIsolateData(event.target.value)
            setIsolateDataKeys(project?.files.isolateData.find(data => data.isolateDataId === event.target.value)?.keys ?? [])
            if (isolateDataFile)
                setIsolateDataFile(null)
        },
        handleIsolateDataFileUploaderChange: (file: React.SetStateAction<File | null>) => {
            setIsolateDataFile(file)
            if (selectedIsolateData)
                setSelectedIsolateData(null)

            // Read and set the keys
            const fileToRead = file instanceof File ? file : file!(isolateDataFile)
            fileToRead!.text()
                .then(text => {
                    const lines = text.split("\n")
                    const keys = lines[0].split(/\s+/)
                    setIsolateDataKeys(keys)
                })
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
                let typingDataId = selectedTypingData
                let isolateDataId = selectedIsolateData

                if (typingDataFile) {
                    FileTransferService.uploadTypingData(project?.projectId!, typingDataFile, datasetType)
                        .then(res => typingDataId = res.typingDataId)
                        .catch(err => setError(err.message))
                }
                if (isolateDataFile) {
                    FileTransferService.uploadIsolateData(project?.projectId!, isolateDataFile)
                        .then(res => isolateDataId = res.isolateDataId)
                        .catch(err => setError(err.message))
                }

                AdministrationService.createDataset(
                    project?.projectId!,
                    {
                        name,
                        description,
                        typingDataId: typingDataId!,
                        isolateDataId: isolateDataId,
                        isolateDataKey: selectedIsolateDataKey
                    }
                )
                    .then(() => {
                        onFileStructureUpdate()
                        navigate(-1)
                    })
                    .catch(err => setError(err.message))
            }
        },
        createDatasetStep,
        currStep,
        error,
        clearError: () => setError(null),
    }
}