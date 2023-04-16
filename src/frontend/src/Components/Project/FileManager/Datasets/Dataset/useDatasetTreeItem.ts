import {Dataset} from "../../../../../Services/administration/models/getProject/GetProjectOutputModel";
import {useNavigate, useParams} from "react-router-dom";
import {WebUiUris} from "../../../../../Pages/WebUiUris";
import {Info, ScatterPlot, Summarize, TableView} from "@mui/icons-material";

/**
 * Hook for the DatasetTreeItem component.
 */
export function useDatasetTreeItem(dataset: Dataset) {
    const {projectId} = useParams<{ projectId: string }>()
    const navigate = useNavigate()
    const computeTreeOptions = [
        {
            label: "goeBURST",
            url: WebUiUris.computeConfigGoeburst(projectId!, dataset.datasetId)
        },
        {
            label: "goeBURST Full MST",
            url: WebUiUris.computeConfigGoeburstFullMst(projectId!, dataset.datasetId)
        },
        {
            label: "Hierarchical Clustering",
            url: WebUiUris.computeConfigHierarchicalClustering(projectId!, dataset.datasetId)
        },
        {
            label: "Neighbor Joining",
            url: WebUiUris.computeConfigNeighborJoining(projectId!, dataset.datasetId)
        },
        {
            label: "nLV Graph",
            url: WebUiUris.computeConfigNlvGraph(projectId!, dataset.datasetId)
        }
    ]

    const computeDistanceMatrixOptions = [
        {
            label: "Hamming Distance",
            url: WebUiUris.computeConfigHammingDistance(projectId!, dataset.datasetId)
        }
    ]

    return {
        contextMenuItems: [
            {
                label: "Compute Distances",
                icon: TableView,
                nestedItems: computeDistanceMatrixOptions.map((option, index) => {
                    return {
                        label: option.label,
                        icon: TableView,
                        onClick: () => navigate(option.url)
                    }
                })
            },
            {
                label: "Compute Tree",
                icon: ScatterPlot,
                nestedItems: computeTreeOptions.map((option, index) => {
                    return {
                        label: option.label,
                        icon: ScatterPlot,
                        onClick: () => navigate(option.url)
                    }
                })
            },
            {
                label: "Dataset Details",
                icon: Info,
                onClick: () => {/*TODO: To be implemented*/
                }
            },
            {
                label: "Generate Report",
                icon: Summarize,
                onClick: () => {/*TODO: To be implemented*/
                }
            }
        ]
    }
}