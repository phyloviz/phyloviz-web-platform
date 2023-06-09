import {Dataset} from "../../../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {useNavigate, useParams} from "react-router-dom"
import {WebUiUris} from "../../../../../Pages/WebUiUris"
import {Delete, Edit, Info} from "@mui/icons-material"
import {useDeleteResourceBackdrop} from "../../../../Shared/DeleteResourceBackdrop"
import AdministrationService from "../../../../../Services/Administration/AdministrationService"
import {useState} from "react"
import {DistanceMatricesIcon, DistanceMatrixIcon, TreeIcon, TreesIcon, TreeViewsIcon} from "../../../../Shared/Icons";
import {useProjectContext} from "../../../../../Pages/Project/useProject";
import {computeDistanceMatrixOptions} from "./Distances/useDistancesTreeItem";
import {computeTreeOptions} from "./Trees/useTreesTreeItem";

/**
 * Hook for the DatasetTreeItem component.
 */
export function useDatasetTreeItem(dataset: Dataset) {
    const {projectId} = useParams<{ projectId: string }>()
    const navigate = useNavigate()
    const {deleteBackdropOpen, handleDeleteBackdropOpen, handleDeleteBackdropClose} = useDeleteResourceBackdrop()
    const [error, setError] = useState<string | null>(null)
    const {onFileStructureUpdate} = useProjectContext()

    const computeTreeViewsOptions = [
        {
            label: "Force Directed Layout",
            url: WebUiUris.computeForceDirectedLayout(projectId!, dataset.datasetId)
        },
        {
            label: "Radial Layout",
            url: WebUiUris.computeRadialLayout(projectId!, dataset.datasetId)
        },
        {
            label: "Rectangular Layout",
            url: WebUiUris.computeRectangularLayout(projectId!, dataset.datasetId)
        }
    ]

    return {
        contextMenuItems: [
            {
                label: "Compute Distances",
                icon: DistanceMatricesIcon,
                nestedItems: computeDistanceMatrixOptions(projectId!, dataset.datasetId).map((option) => {
                    return {
                        label: option.label,
                        icon: DistanceMatrixIcon,
                        onClick: () => navigate(option.url)
                    }
                })
            },
            {
                label: "Compute Tree",
                icon: TreesIcon,
                nestedItems: computeTreeOptions(projectId!, dataset.datasetId).map((option) => {
                    return {
                        label: option.label,
                        icon: TreeIcon,
                        onClick: () => navigate(option.url)
                    }
                })
            },
            {
                label: "Compute Tree View",
                icon: TreeViewsIcon,
                nestedItems: computeTreeViewsOptions.map((option) => {
                    return {
                        label: option.label,
                        icon: TreeViewsIcon,
                        onClick: () => navigate(option.url)
                    }
                })
            },
            /*
            TODO: Not implemented yet.
            {
                label: "Generate Report",
                icon: Summarize,
                onClick: () => navigate(WebUiUris.report(projectId!, dataset.datasetId))
            },
             */
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
                    onFileStructureUpdate()
                })
                .catch(error => setError(error.message))
        },
        error,
        clearError: () => setError(null)
    }
}