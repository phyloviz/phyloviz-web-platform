import {
    GetWorkflowStatusOutputModel,
    workflowStatusToWorkflowStatusString
} from "../../../../Services/Compute/models/getWorkflow/GetWorkflowOutputModel"
import {StyledTreeItem} from "../Utils/StyledTreeItem"
import {Cancel} from "@mui/icons-material"
import * as React from "react"
import {CircularProgress, Popover} from "@mui/material"
import FinishIcon from "@mui/icons-material/Done"
import Typography from "@mui/material/Typography"
import {useWorkflowTreeItem} from "./useWorkflowTreeItem"
import Box from "@mui/material/Box"
import {useProjectContext} from "../../../../Pages/Project/useProject";

/**
 * Props of the WorkflowTreeItem.
 *
 * @property nodeId id of the node
 * @property workflow workflow to display
 */
interface WorkflowTreeItemProps {
    nodeId: string
    workflow: GetWorkflowStatusOutputModel
}

/**
 * Tree item for a workflow.
 */
export function WorkflowTreeItem({nodeId, workflow}: WorkflowTreeItemProps) {
    const {
        anchorEl,
        handlePopoverOpen,
        handlePopoverClose,
        open,
        contextMenuItems
    } = useWorkflowTreeItem(workflow.workflowId)

    const project = useProjectContext().project!

    return (<>
            <StyledTreeItem
                nodeId={nodeId}
                labelText={workflow.name}
                rightContent={
                    workflow.status === "RUNNING"
                        ? <CircularProgress size={12} sx={{ml: 1}}/>
                        : workflow.status === "SUCCESS"
                            ? <FinishIcon sx={{ml: 1, color: "success.main", width: 12, height: 12}}/>
                            : <Cancel sx={{ml: 1, color: "error.main", width: 12, height: 12}}/>
                }
                onMouseEnter={handlePopoverOpen}
                onMouseLeave={handlePopoverClose}
                contextMenuItems={contextMenuItems}
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
                    <Typography variant={"body2"}>
                        <strong>{workflow.name}</strong>
                    </Typography>
                    <Typography variant={"body2"}>
                        <strong>Status: </strong>{workflowStatusToWorkflowStatusString(workflow.status)}
                    </Typography>
                    <Typography variant={"body2"}>
                        <strong>Dataset: </strong>{project?.datasets?.filter(dataset => dataset.datasetId == workflow?.data?.['datasetId'])[0].name ?? "None"}
                    </Typography>
                </Box>
            </Popover>
        </>
    )
}
