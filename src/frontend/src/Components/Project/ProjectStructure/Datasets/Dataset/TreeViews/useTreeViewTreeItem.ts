import {TreeView} from "../../../../../../Services/administration/models/getProject/GetProjectOutputModel"
import {useNavigate, useParams} from "react-router-dom"
import {Download, Visibility} from "@mui/icons-material"
import {WebUiUris} from "../../../../../../Pages/WebUiUris"

/**
 * Hook for the TreeViewTreeItem component.
 */
export function useTreeViewTreeItem(datasetId: string, treeView: TreeView) {
    const navigate = useNavigate()
    const {projectId} = useParams<{ projectId: string }>()

    return {
        contextMenuItems: [
            {
                label: "View",
                icon: Visibility,
                onClick: () => navigate(WebUiUris.treeView(projectId!, datasetId, treeView.treeViewId))
            },
            {
                label: "Export",
                icon: Download,
                onClick: () => {/*TODO: To be implemented*/
                }
            }
        ]
    }
}