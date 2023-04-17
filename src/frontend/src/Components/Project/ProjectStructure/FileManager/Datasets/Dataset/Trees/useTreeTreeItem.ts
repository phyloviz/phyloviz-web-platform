import {Tree} from "../../../../../../../Services/administration/models/getProject/GetProjectOutputModel"
import {useNavigate, useParams} from "react-router-dom"
import {Download, Visibility} from "@mui/icons-material"
import {WebUiUris} from "../../../../../../../Pages/WebUiUris"

/**
 * Hook for the TreeTreeItem component.
 */
export function useTreeTreeItem(datasetId: string, tree: Tree) {
    const navigate = useNavigate()
    const {projectId} = useParams<{ projectId: string }>()

    return {
        contextMenuItems: [
            {
                label: "View",
                icon: Visibility,
                onClick: () => navigate(WebUiUris.tree(projectId!, datasetId, tree.treeId))
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