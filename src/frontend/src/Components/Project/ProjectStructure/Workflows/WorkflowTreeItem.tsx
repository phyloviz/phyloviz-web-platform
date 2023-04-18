import {Workflow} from "../../../../Services/compute/models/getWorkflowStatus/GetWorkflowStatusOutputModel"
import {StyledTreeItem} from "../Utils/StyledTreeItem"
import {Cancel} from "@mui/icons-material"
import * as React from "react"
import {CircularProgress, Popover} from "@mui/material"
import FinishIcon from "@mui/icons-material/Done"
import Typography from "@mui/material/Typography"
import {useWorkflowTreeItem} from "./useWorkflowTreeItem"
import Box from "@mui/material/Box"

/**
 * Props of the WorkflowTreeItem.
 */
interface WorkflowTreeItemProps {
    nodeId: string
    workflow: Workflow
}

/**
 * Tree item for a workflow.
 */
export function WorkflowTreeItem({nodeId, workflow}: WorkflowTreeItemProps) {
    const {anchorEl, handlePopoverOpen, handlePopoverClose, open} = useWorkflowTreeItem()

    return (<>
        <StyledTreeItem
            nodeId={nodeId}
            labelText={workflow.type}
            rightContent={
                workflow.status === "RUNNING"
                    ? <CircularProgress size={12} sx={{ml: 1}}/>
                    : workflow.status === "COMPLETED"
                        ? <FinishIcon sx={{ml: 1, color: "success.main", width: 12, height: 12}}/>
                        : <Cancel sx={{ml: 1, color: "error.main", width: 12, height: 12}}/>
            }
            onMouseEnter={handlePopoverOpen}
            onMouseLeave={handlePopoverClose}
        />
        <Popover
            sx={{
                pointerEvents: 'none',
            }}
            open={open}
            anchorEl={anchorEl}
            anchorOrigin={{
                vertical: 'center',
                horizontal: 'right',
            }}
            transformOrigin={{
                vertical: 'top',
                horizontal: 'left',
            }}
            onClose={handlePopoverClose}
            disableRestoreFocus
        >
            <Box sx={{p: 2}}>
                <Typography variant={"body1"}>
                    <strong>{workflow.type}</strong>
                </Typography>
                <Typography variant={"body2"}>
                    <strong>Status: </strong>
                    {workflow.status === "RUNNING" ? "Running" : workflow.status === "COMPLETED" ? "Completed" : "Failed"}
                </Typography>
            </Box>
        </Popover>
    </>)
}
