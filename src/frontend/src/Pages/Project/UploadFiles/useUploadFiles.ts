import * as React from "react";
import {useState} from "react";
import {useNavigate} from "react-router-dom";

export enum FileType {
    TYPING_DATA = "Typing Data",
    ISOLATE_DATA = "Isolate Data",
}

/**
 * Hook for the UploadFiles page.
 */
export function useUploadFiles() {
    const [fileType, setfileType] = useState<FileType>(FileType.TYPING_DATA);
    const [file, setFile] = useState(null);
    const [error, setError] = useState<string | null>(null);

    const navigate = useNavigate();

    return {
        fileType,
        handleFileTypeChange: (value: FileType) => setfileType(value),
        handleFileChange: (file: React.SetStateAction<null>) => setFile(file),
        handleCancel: () => navigate(-1),
        handleSubmit: () => {
            // TODO
        },
        error
    }
}