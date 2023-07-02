import * as React from "react"
import {useState} from "react"
import {Visibility} from "@mui/icons-material";
import {WebUiUris} from "../../../../Pages/WebUiUris";
import {useNavigate, useParams} from "react-router-dom";

/**
 * Hook for the workflow tree item.
 */
export function useWorkflowTreeItem(workflowId: string) {
    const [anchorEl, setAnchorEl] = useState<HTMLElement | null>(null)
    const handlePopoverOpen = (event: React.MouseEvent<HTMLElement>) => setAnchorEl(event.currentTarget)
    const handlePopoverClose = () => setAnchorEl(null)
    const open = Boolean(anchorEl)
    const navigate = useNavigate()
    const {projectId} = useParams<{ projectId: string }>()

    return {
        anchorEl,
        handlePopoverOpen,
        handlePopoverClose,
        open,
        contextMenuItems: [
            {
                label: "View",
                icon: Visibility,
                onClick: () => navigate(WebUiUris.workflow(projectId!, workflowId))
            }
        ]
    }
}