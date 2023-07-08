import * as React from "react"
import {useState} from "react"
import {useNavigate, useParams} from "react-router-dom"
import {WebUiUris} from "../../WebUiUris"
import {useProjectContext} from "../useProject"
import FileTransferService from "../../../Services/FileTransfer/FileTransferService";
import {TypingDataType} from "../CreateDataset/useCreateDataset";
import {useCompute} from "../Compute/useCompute";

export enum FileType {
    // noinspection JSUnusedGlobalSymbols
    TYPING_DATA = "Typing Data",
    ISOLATE_DATA = "Isolate Data",
}


/**
 * Hook for the UploadFiles page.
 */
export function useUploadFiles() {
    const {projectId} = useParams<{ projectId: string }>()
    const {onFileStructureUpdate} = useProjectContext()
    const [fileType, setFileType] = useState<FileType>(FileType.TYPING_DATA)
    const [typingDataType, setTypingDataType] = useState<TypingDataType>(TypingDataType.MLST)
    const [fileUrl, setFileUrl] = useState<string | null>(null)
    const [file, setFile] = useState<File | null>(null)
    const [error, setError] = useState<string | null>(null)
    const [isUploading, setIsUploading] = useState(false)

    const navigate = useNavigate()

    const {createWorkflow} = useCompute()

    return {
        fileType,
        fileUrl,
        onFileUrlChange: (event: React.ChangeEvent<HTMLInputElement>) => setFileUrl(event.target.value),
        typingDataType,
        handleFileTypeChange: (value: FileType) => setFileType(value),
        handleTypingDataTypeChange: (value: TypingDataType) => setTypingDataType(value),
        handleFileChange: (file: React.SetStateAction<File | null>) => setFile(file),
        handleCancel: () => navigate(-1),
        handleSubmit: () => {
            if (!file && !fileUrl) {
                setError("Please select a file to upload or provide a URL to a file.")
                return
            }

            setIsUploading(true)

            if (file) {
                if (fileType === FileType.TYPING_DATA)
                    FileTransferService.uploadTypingData(projectId!, file, typingDataType)
                        .then(() => {
                            onFileStructureUpdate()
                            navigate(WebUiUris.project(projectId!))
                        })
                        .catch((err) => setError(err.message))
                        .finally(() => setIsUploading(false))
                else
                    FileTransferService.uploadIsolateData(projectId!, file)
                        .then(() => {
                            onFileStructureUpdate()
                            navigate(WebUiUris.project(projectId!))
                        })
                        .catch((err) => setError(err.message))
                        .finally(() => setIsUploading(false))
            }
            else if (fileUrl) {
                createWorkflow(
                    {
                        type: "file-upload-by-url",
                        properties: {
                            url: fileUrl,
                            fileType: fileType,
                        }
                    }
                )
            }
        },
        isUploading,
        error,
        clearError: () => setError(null)
    }
}