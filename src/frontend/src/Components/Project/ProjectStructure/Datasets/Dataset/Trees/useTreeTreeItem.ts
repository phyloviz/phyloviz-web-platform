import {Tree} from "../../../../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {useNavigate, useParams} from "react-router-dom"
import {Delete, Visibility} from "@mui/icons-material"
import {WebUiUris} from "../../../../../../Pages/WebUiUris"
import {useDeleteResourceBackdrop} from "../../../../../Shared/DeleteResourceBackdrop"
import {useState} from "react"
import AdministrationService from "../../../../../../Services/Administration/AdministrationService"
import {TreeViewIcon} from "../../../../../Shared/Icons";
import {Problem} from "../../../../../../Services/utils/Problem";
import {useProjectContext} from "../../../../../../Pages/Project/useProject";
import {useCompute} from "../../../../../../Pages/Project/Compute/useCompute";

/**
 * Hook for the TreeTreeItem component.
 */
export function useTreeTreeItem(datasetId: string, tree: Tree) {
    const navigate = useNavigate()
    const {projectId} = useParams<{ projectId: string }>()
    const {deleteBackdropOpen, handleDeleteBackdropOpen, handleDeleteBackdropClose} = useDeleteResourceBackdrop()
    const [error, setError] = useState<string | null>(null)
    const {createWorkflow} = useCompute()
    const {onFileStructureUpdate} = useProjectContext()

    const layoutOptions = [
        {
            label: "Force Directed Layout",
            id: "force-directed",
        },
/*        {
            label: "Radial Layout",
            id: "radial",
        },
        {
            label: "Rectangular Layout",
            id: "rectangular",
        }*/
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
                            createWorkflow(
                                {
                                    type: "compute-tree-view",
                                    properties: {
                                        datasetId: datasetId,
                                        treeId: tree.treeId,
                                        layout: option.id
                                    }
                                }
                            )
                        }
                    }
                })
            },
            /* TODO: To be implemented
            {
                label: "Export",
                icon: Download,
                onClick: () => {
                }
            },
            */
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
                    onFileStructureUpdate()
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