import * as React from "react"
import {useState} from "react"
import {useNavigate, useParams} from "react-router-dom"
import {AdministrationService} from "../../../Services/administration/AdministrationService"
import {WebUiUris} from "../../../Utils/WebUiUris"
import {useProjectContext} from "../useProject"

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
    const {onProjectUpdate} = useProjectContext()
    const [fileType, setfileType] = useState<FileType>(FileType.TYPING_DATA)
    const [file, setFile] = useState<File | null>(null)
    const [error, setError] = useState<string | null>(null)

    const navigate = useNavigate()

    return {
        fileType,
        handleFileTypeChange: (value: FileType) => setfileType(value),
        handleFileChange: (file: React.SetStateAction<File | null>) => setFile(file),
        handleCancel: () => navigate(-1),
        handleSubmit: () => {
            if (!file) {
                setError("Please select a file to upload.")
                return
            }

            if (fileType === FileType.TYPING_DATA)
                AdministrationService.uploadTypingData(projectId!, file)
                    .then(() => {
                        onProjectUpdate()
                        navigate(WebUiUris.project(projectId!))
                    })
                    .catch((err) => setError(err.message))
            else
                AdministrationService.uploadIsolateData(projectId!, file)
                    .then(() => {
                        onProjectUpdate()
                        navigate(WebUiUris.project(projectId!))
                    })
                    .catch((err) => setError(err.message))
        },
        error
    }
}