import {TreeView} from "../../../../../../Services/administration/models/getProject/GetProjectOutputModel"
import {useNavigate, useParams} from "react-router-dom"
import {Delete, Download, Visibility} from "@mui/icons-material"
import {WebUiUris} from "../../../../../../Pages/WebUiUris"
import {useDeleteResourceBackdrop} from "../../../../../Shared/DeleteResourceBackdrop"
import {useState} from "react"
import AdministrationService from "../../../../../../Services/administration/AdministrationService"

/**
 * Hook for the TreeViewTreeItem component.
 */
export function useTreeViewTreeItem(datasetId: string, treeView: TreeView) {
    const navigate = useNavigate()
    const {projectId} = useParams<{ projectId: string }>()
    const {deleteBackdropOpen, handleDeleteBackdropOpen, handleDeleteBackdropClose} = useDeleteResourceBackdrop()
    const [error, setError] = useState<string | null>(null)

    return {
        contextMenuItems: [
            {
                label: "View",
                icon: Visibility,
                onClick: () => navigate(WebUiUris.treeView(projectId!, datasetId, treeView.treeViewId))
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
            AdministrationService.deleteTreeView(projectId!, datasetId, treeView.treeViewId)
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