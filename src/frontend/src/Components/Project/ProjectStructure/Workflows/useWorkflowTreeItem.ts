import * as React from "react"
import {useState} from "react"

/**
 * Hook for the workflow tree item.
 */
export function useWorkflowTreeItem() {
    const [anchorEl, setAnchorEl] = useState<HTMLElement | null>(null)
    const handlePopoverOpen = (event: React.MouseEvent<HTMLElement>) => setAnchorEl(event.currentTarget)
    const handlePopoverClose = () => setAnchorEl(null)
    const open = Boolean(anchorEl)

    return {
        anchorEl,
        handlePopoverOpen,
        handlePopoverClose,
        open
    }
}