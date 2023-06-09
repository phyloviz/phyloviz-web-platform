import {TreeView} from "../../../../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {useNavigate, useParams} from "react-router-dom"
import {Delete, Visibility} from "@mui/icons-material"
import {WebUiUris} from "../../../../../../Pages/WebUiUris"
import {useDeleteResourceBackdrop} from "../../../../../Shared/DeleteResourceBackdrop"
import {useState} from "react"
import AdministrationService from "../../../../../../Services/Administration/AdministrationService"
import {useProjectContext} from "../../../../../../Pages/Project/useProject";

/**
 * Hook for the TreeViewTreeItem component.
 *
 * @param datasetId the dataset ID
 * @param treeView the tree view
 */
export function useTreeViewTreeItem(datasetId: string, treeView: TreeView) {
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
                onClick: () => navigate(WebUiUris.treeView(projectId!, datasetId, treeView.treeViewId))
            },
            /* TODO: To be implemented,
            {
                label: "Export",
                icon: Download,
                onClick: () => {TODO: To be implemented
                }
            }*/
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
                    onFileStructureUpdate()
                })
                .catch(error => {
                    setError("An unexpected error occurred while deleting the tree view.")
                })
        },
        error,
        clearError: () => setError(null)
    }
}