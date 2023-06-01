import {Delete, Download, Visibility} from "@mui/icons-material"
import {useNavigate, useParams} from "react-router-dom"
import {useState} from "react"
import {TypingDataFile} from "../../../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {useDeleteResourceBackdrop} from "../../../../Shared/DeleteResourceBackdrop"
import AdministrationService from "../../../../../Services/Administration/AdministrationService"
import {WebUiUris} from "../../../../../Pages/WebUiUris"
import {Problem} from "../../../../../Services/utils/Problem";

/**
 * Hook for the TypingDataFileTreeItem component.
 *
 * @param file the typing data file
 */
export function useTypingDataFileTreeItem(file: TypingDataFile) {
    const navigate = useNavigate()
    const {projectId} = useParams<{ projectId: string }>()
    const {deleteBackdropOpen, handleDeleteBackdropOpen, handleDeleteBackdropClose} = useDeleteResourceBackdrop()
    const [error, setError] = useState<string | null>(null)

    return {
        contextMenuItems: [
            {
                label: "View",
                icon: Visibility,
                onClick: () => navigate(WebUiUris.typingData(projectId!, file.typingDataId))
            },
            {
                label: "Export",
                icon: Download,
                onClick: () => {/*TODO: To be implemented*/
                }
            },
            {
                label: "Delete",
                icon: Delete,
                onClick: handleDeleteBackdropOpen
            }
        ],
        deleteBackdropOpen,
        handleDeleteBackdropClose,
        handleDelete: () => {
            AdministrationService.deleteTypingData(projectId!, file.typingDataId)
                .then(() => {
                    handleDeleteBackdropClose()
                    navigate(WebUiUris.project(projectId!))
                })
                .catch(error => {
                    if (error instanceof Problem && error.title === "Denied Deletion") {
                        setError("Cannot delete file. File is still being used in one or more datasets. Delete the datasets first.")
                    } else {
                        setError("Could not delete the file. An unexpected error occurred while trying to delete it.")
                    }
                })
        },
        error,
        clearError: () => setError(null)
    }
}
