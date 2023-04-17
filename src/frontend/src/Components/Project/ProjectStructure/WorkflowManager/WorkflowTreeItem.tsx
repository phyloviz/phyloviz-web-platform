import {Workflow} from "../../../../Services/compute/models/getWorkflowStatus/GetWorkflowStatusOutputModel";
import {StyledTreeItem} from "../Utils/StyledTreeItem";
import {Work} from "@mui/icons-material";
import * as React from "react";

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
    return <StyledTreeItem
        nodeId={nodeId}
        labelText={workflow.type}
        labelIcon={Work}
        labelInfo={workflow.status}
    />
}
