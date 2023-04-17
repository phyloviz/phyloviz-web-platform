import {useNavigate, useParams} from "react-router-dom"
import {Download, Visibility} from "@mui/icons-material"
import {WebUiUris} from "../../../../../../../Pages/WebUiUris"

/**
 * Hook for the TypingDataTreeItem component.
 */
export function useTypingDataTreeItem(typingDataId: string) {
    const navigate = useNavigate()
    const {projectId} = useParams<{ projectId: string }>()

    return {
        contextMenuItems: [
            {
                label: "View",
                icon: Visibility,
                onClick: () => navigate(WebUiUris.typingData(projectId!, typingDataId))
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