import {useNavigate, useParams} from "react-router-dom";
import {TreeViewsIcon} from "../../../../../Shared/Icons";
import {WebUiUris} from "../../../../../../Pages/WebUiUris";


/**
 * Hook for the TreeViewsTreeItem component.
 */
export function useTreeViewsTreeItem(datasetId: string) {
    const {projectId} = useParams<{ projectId: string }>()
    const navigate = useNavigate()

    return {
        contextMenuItems: [
            {
                label: "Compute Tree View",
                icon: TreeViewsIcon,
                onClick: () => navigate(WebUiUris.computeTreeView(projectId!, datasetId))
            }
        ]
    }
}