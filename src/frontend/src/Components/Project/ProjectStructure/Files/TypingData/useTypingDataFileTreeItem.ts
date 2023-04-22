import {Delete, Download, Visibility} from "@mui/icons-material"
import {useNavigate, useParams} from "react-router-dom"
import {useState} from "react"
import {TypingDataFile} from "../../../../../Services/Administration/models/getProject/GetProjectOutputModel"
import {useDeleteResourceBackdrop} from "../../../../Shared/DeleteResourceBackdrop"
import AdministrationService from "../../../../../Services/Administration/AdministrationService"
import {WebUiUris} from "../../../../../Pages/WebUiUris"

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
                .catch(error => setError(error.message))
        },
        error,
        clearError: () => setError(null)
    }
}