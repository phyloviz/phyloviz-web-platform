import {Dataset} from "../../../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {useNavigate, useParams} from "react-router-dom"
import {WebUiUris} from "../../../../../Pages/WebUiUris"
import {Delete, Edit, Info, Summarize} from "@mui/icons-material"
import {useDeleteResourceBackdrop} from "../../../../Shared/DeleteResourceBackdrop"
import AdministrationService from "../../../../../Services/Administration/AdministrationService"
import {useState} from "react"
import {DistanceMatricesIcon, DistanceMatrixIcon, TreeIcon} from "../../../../Shared/Icons";

/**
 * Hook for the DatasetTreeItem component.
 */
export function useDatasetTreeItem(dataset: Dataset) {
    const {projectId} = useParams<{ projectId: string }>()
    const navigate = useNavigate()
    const {deleteBackdropOpen, handleDeleteBackdropOpen, handleDeleteBackdropClose} = useDeleteResourceBackdrop()
    const [error, setError] = useState<string | null>(null)

    const computeTreeOptions = [
        {
            label: "goeBURST",
            url: WebUiUris.computeGoeburst(projectId!, dataset.datasetId)
        },
        {
            label: "goeBURST Full MST",
            url: WebUiUris.computeGoeburstFullMst(projectId!, dataset.datasetId)
        },
        {
            label: "Hierarchical Clustering",
            url: WebUiUris.computeHierarchicalClustering(projectId!, dataset.datasetId)
        },
        {
            label: "Neighbor Joining",
            url: WebUiUris.computeNeighborJoining(projectId!, dataset.datasetId)
        },
        {
            label: "nLV Graph",
            url: WebUiUris.computeNlvGraph(projectId!, dataset.datasetId)
        }
    ]

    const computeDistanceMatrixOptions = [
        {
            label: "Hamming Distance",
            url: WebUiUris.computeHammingDistance(projectId!, dataset.datasetId)
        }
    ]

    return {
        contextMenuItems: [
            {
                label: "Compute Distances",
                icon: DistanceMatricesIcon,
                nestedItems: computeDistanceMatrixOptions.map((option) => {
                    return {
                        label: option.label,
                        icon: DistanceMatrixIcon,
                        onClick: () => navigate(option.url)
                    }
                })
            },
            {
                label: "Compute Tree",
                icon: TreeIcon,
                nestedItems: computeTreeOptions.map((option) => {
                    return {
                        label: option.label,
                        icon: TreeIcon,
                        onClick: () => navigate(option.url)
                    }
                })
            },
            {
                label: "Generate Report",
                icon: Summarize,
                onClick: () => navigate(WebUiUris.report(projectId!, dataset.datasetId))
            },
            {
                label: "Details",
                icon: Info,
                onClick: () => navigate(WebUiUris.dataset(projectId!, dataset.datasetId))
            },
            {
                label: "Edit",
                icon: Edit,
                onClick: () => navigate(WebUiUris.editDataset(projectId!, dataset.datasetId))
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
            AdministrationService.deleteDataset(projectId!, dataset.datasetId)
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