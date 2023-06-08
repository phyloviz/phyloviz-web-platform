import {
    DistanceMatrix
} from "../../../../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {Delete, Download, Visibility} from "@mui/icons-material"
import {useNavigate, useParams} from "react-router-dom"
import {WebUiUris} from "../../../../../../Pages/WebUiUris"
import {useDeleteResourceBackdrop} from "../../../../../Shared/DeleteResourceBackdrop"
import {useState} from "react"
import AdministrationService from "../../../../../../Services/Administration/AdministrationService"
import {Problem} from "../../../../../../Services/utils/Problem";
import {useProjectContext} from "../../../../../../Pages/Project/useProject";

/**
 * Hook for the DistanceMatrixTreeItem component.
 */
export function useDistanceMatrixTreeItem(datasetId: string, distanceMatrix: DistanceMatrix) {
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
                onClick: () => navigate(WebUiUris.distanceMatrix(projectId!, datasetId, distanceMatrix.distanceMatrixId))
            },
            /*{
                label: "Export",
                icon: Download,
                onClick: () => {/!*TODO: To be implemented*!/
                }
            },*/
            {
                label: "Delete",
                icon: Delete,
                onClick: handleDeleteBackdropOpen
            }
        ],
        deleteBackdropOpen,
        handleDeleteBackdropClose,
        handleDelete: () => {
            AdministrationService.deleteDistanceMatrix(projectId!, datasetId, distanceMatrix.distanceMatrixId)
                .then(() => {
                    handleDeleteBackdropClose()
                    navigate(WebUiUris.project(projectId!))
                    onFileStructureUpdate()
                })
                .catch(error => {
                    if (error instanceof Problem && error.title === "Denied Deletion") {
                        setError("Cannot delete distance matrix. It is a dependency of a tree. Delete the tree first.")
                    } else {
                        setError("Could not delete the distance matrix. An unexpected error occurred while trying to delete it.")
                    }
                })
        },
        error,
        clearError: () => setError(null)
    }
}