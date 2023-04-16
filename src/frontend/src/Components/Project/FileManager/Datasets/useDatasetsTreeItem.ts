import {useNavigate, useParams} from "react-router-dom";
import {Add} from "@mui/icons-material";
import {WebUiUris} from "../../../../Pages/WebUiUris";

/**
 * Hook for the DatasetsTreeItem component.
 */
export function useDatasetsTreeItem() {
    const {projectId} = useParams<{ projectId: string }>()
    const navigate = useNavigate()

    return {
        contextMenuItems: [
            {
                label: "Create Dataset",
                icon: Add,
                onClick: () => navigate(WebUiUris.createDataset(projectId!))
            }
        ]
    }
}
