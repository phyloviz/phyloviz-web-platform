import {useNavigate, useParams} from "react-router-dom"
import UploadIcon from "@mui/icons-material/Upload"
import {WebUiUris} from "../../../../../Pages/WebUiUris"

/**
 * Hook for the FilesTreeItem component.
 */
export function useFilesTreeItem() {
    const navigate = useNavigate()
    const {projectId} = useParams<{ projectId: string }>()

    return {
        contextMenuItems: [
            {
                label: "Upload Files",
                icon: UploadIcon,
                onClick: () => navigate(WebUiUris.uploadFiles(projectId!))
            }
        ]
    }
}