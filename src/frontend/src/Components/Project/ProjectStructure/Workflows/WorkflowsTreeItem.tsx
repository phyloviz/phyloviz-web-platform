import * as React from "react"
import {StyledTreeItem} from "../Utils/StyledTreeItem"
import {WorkflowTreeItem} from "./WorkflowTreeItem"
import {
    GetWorkflowStatusOutputModel,
    Workflow
} from "../../../../Services/Compute/models/getWorkflow/GetWorkflowOutputModel"
import {WorkflowsIcon} from "../../../Shared/Icons";
import {CircularProgress} from "@mui/material";

/**
 * Props of the WorkflowsTreeItem.
 *
 * @property workflows workflows to display
 */
interface WorkflowsTreeItemProps {
    workflows: GetWorkflowStatusOutputModel[]
}


/**
 * WorkflowManager for the project.
 */
export function WorkflowsTreeItem({workflows}: WorkflowsTreeItemProps) {
    return (
        <StyledTreeItem
            nodeId="workflows"
            labelText={"Workflows"}
            labelIcon={WorkflowsIcon}
            rightContent={
                workflows.find(workflow => workflow.status === "RUNNING") !== undefined
                    ? <CircularProgress size={12} sx={{ml: 1}}/> : null
            }
        >
            {
                workflows.map((workflow, index) => {
                    return <WorkflowTreeItem
                        key={"workflows" + index.toString()}
                        nodeId={"workflows" + index.toString()}
                        workflow={workflow}
                    />
                })
            }
        </StyledTreeItem>
    )
}

