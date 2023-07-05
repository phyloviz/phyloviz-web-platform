import {useNavigate, useParams} from "react-router-dom";
import {TreeIcon, TreesIcon} from "../../../../../Shared/Icons";
import {WebUiUris} from "../../../../../../Pages/WebUiUris";


export const computeTreeOptions = (projectId: string, datasetId: string) => [
    {
        label: "goeBURST",
        url: WebUiUris.computeGoeburst(projectId, datasetId)
    },
    /*{
        label: "goeBURST Full MST",
        url: WebUiUris.computeGoeburstFullMst(projectId, datasetId)
    },
    {
        label: "Hierarchical Clustering",
        url: WebUiUris.computeHierarchicalClustering(projectId, datasetId)
    },
    {
        label: "Neighbor Joining",
        url: WebUiUris.computeNeighborJoining(projectId, datasetId)
    },
    {
        label: "nLV Graph",
        url: WebUiUris.computeNlvGraph(projectId, datasetId)
    }*/
]

/**
 * Hook for the TreesTreeItem component.
 */
export function useTreesTreeItem(datasetId: string) {
    const {projectId} = useParams<{ projectId: string }>()
    const navigate = useNavigate()

    return {
        contextMenuItems: [
            {
                label: "Compute Tree",
                icon: TreesIcon,
                nestedItems: computeTreeOptions(projectId!, datasetId).map((option) => {
                    return {
                        label: option.label,
                        icon: TreeIcon,
                        onClick: () => navigate(option.url)
                    }
                })
            },
        ]
    }
}