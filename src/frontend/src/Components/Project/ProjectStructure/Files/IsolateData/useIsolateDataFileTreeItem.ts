import {Delete, Download, Visibility} from "@mui/icons-material"
import {useNavigate, useParams} from "react-router-dom"
import {useState} from "react"
import {IsolateDataFile} from "../../../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {useDeleteResourceBackdrop} from "../../../../Shared/DeleteResourceBackdrop"
import AdministrationService from "../../../../../Services/Administration/AdministrationService"
import {WebUiUris} from "../../../../../Pages/WebUiUris"
import {Problem} from "../../../../../Services/utils/Problem";
import {useProjectContext} from "../../../../../Pages/Project/useProject";

/**
 * Hook for the IsolateDataFileTreeItem component.
 *
 * @param file the isolate data file
 */
export function useIsolateDataFileTreeItem(file: IsolateDataFile) {
    const navigate = useNavigate()
    const {projectId} = useParams<{ projectId: string }>()
    const {deleteBackdropOpen, handleDeleteBackdropOpen, handleDeleteBackdropClose} = useDeleteResourceBackdrop()
    const [error, setError] = useState<string | null>(null)
    const {onFileStructureUpdate} = useProjectContext()

    return {
        contextMenuItems: [
            {
                label: "View",
                icon: Visibility,
                onClick: () => navigate(WebUiUris.isolateData(projectId!, file.isolateDataId))
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
            AdministrationService.deleteIsolateData(projectId!, file.isolateDataId)
                .then(() => {
                    handleDeleteBackdropClose()
                    navigate(WebUiUris.project(projectId!))
                    onFileStructureUpdate()
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