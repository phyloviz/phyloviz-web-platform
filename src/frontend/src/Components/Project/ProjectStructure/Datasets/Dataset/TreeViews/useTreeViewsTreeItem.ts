import {useNavigate, useParams} from "react-router-dom";
import {TreeViewsIcon} from "../../../../../Shared/Icons";
import {computeTreeViewsOptions} from "../useDatasetTreeItem";


/**
 * Hook for the TreeViewsTreeItem component.
 */
export function useTreeViewsTreeItem(datasetId: string) {
    const projectId = useParams<{ projectId: string }>().projectId!
    const navigate = useNavigate()

    return {
        contextMenuItems: [
            {
                label: "Compute Tree View",
                icon: TreeViewsIcon,
                nestedItems: computeTreeViewsOptions(projectId, datasetId).map((option) => {
                    return {
                        label: option.label,
                        icon: TreeViewsIcon,
                        onClick: () => navigate(option.url)
                    }
                })
            }
        ]
    }
}