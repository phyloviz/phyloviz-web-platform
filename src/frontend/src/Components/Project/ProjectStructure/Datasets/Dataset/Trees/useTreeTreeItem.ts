import {Tree} from "../../../../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {useNavigate, useParams} from "react-router-dom"
import {Delete, Download, Visibility} from "@mui/icons-material"
import {WebUiUris} from "../../../../../../Pages/WebUiUris"
import {useDeleteResourceBackdrop} from "../../../../../Shared/DeleteResourceBackdrop"
import {useState} from "react"
import AdministrationService from "../../../../../../Services/Administration/AdministrationService"
import {TreeViewIcon} from "../../../../../Shared/Icons";
import {Problem} from "../../../../../../Services/utils/Problem";

/**
 * Hook for the TreeTreeItem component.
 */
export function useTreeTreeItem(datasetId: string, tree: Tree) {
    const navigate = useNavigate()
    const {projectId} = useParams<{ projectId: string }>()
    const {deleteBackdropOpen, handleDeleteBackdropOpen, handleDeleteBackdropClose} = useDeleteResourceBackdrop()
    const [error, setError] = useState<string | null>(null)

    const layoutOptions = [
        {
            id: "force-directed",
            label: "Force Directed"
        },
        {
            id: "radial",
            label: "Radial"
        },
        {
            id: "dendrogram",
            label: "Dendrogram"
        }
    ]

    return {
        contextMenuItems: [
            {
                label: "View",
                icon: Visibility,
                onClick: () => navigate(WebUiUris.tree(projectId!, datasetId, tree.treeId))
            },
            {
                label: "Compute View",
                icon: TreeViewIcon,
                nestedItems: layoutOptions.map((option) => {
                    return {
                        label: option.label,
                        icon: TreeViewIcon,
                        onClick: () => {
                            // TODO: To be implemented
                        }
                    }
                })
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
            AdministrationService.deleteTree(projectId!, datasetId, tree.treeId)
                .then(() => {
                    handleDeleteBackdropClose()
                    navigate(WebUiUris.project(projectId!))
                })
                .catch(error => {
                    if (error instanceof Problem && error.title === "Denied Deletion") {
                        setError("Cannot delete tree. It is a dependency of a tree view. Delete the tree view first.")
                    } else {
                        setError("Could not delete the tree. An unexpected error occurred while trying to delete it.")
                    }
                })
        },
        error,
        clearError: () => setError(null)
    }
}