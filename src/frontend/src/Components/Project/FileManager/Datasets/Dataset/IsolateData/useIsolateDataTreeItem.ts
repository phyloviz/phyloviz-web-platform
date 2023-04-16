import {useNavigate, useParams} from "react-router-dom";
import {Download, Visibility} from "@mui/icons-material";
import {WebUiUris} from "../../../../../../Pages/WebUiUris";

/**
 * Hook for the IsolateDataTreeItem component.
 */
export function useIsolateDataTreeItem(isolateDataId: string) {
    const navigate = useNavigate()
    const {projectId} = useParams<{ projectId: string }>()

    return {
        contextMenuItems: [
            {
                label: "View",
                icon: Visibility,
                onClick: () => navigate(WebUiUris.isolateData(projectId!, isolateDataId))
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