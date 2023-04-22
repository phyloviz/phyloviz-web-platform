import * as React from "react"
import {StyledTreeItem} from "../Utils/StyledTreeItem"
import {Work} from "@mui/icons-material"
import {WorkflowTreeItem} from "./WorkflowTreeItem"
import {Workflow} from "../../../../Services/compute/models/getWorkflowStatus/GetWorkflowStatusOutputModel"

/**
 * Props of the WorkflowsTreeItem.
 *
 * @property workflows workflows to display
 */
interface WorkflowsTreeItemProps {
    workflows: Workflow[]
}


/**
 * WorkflowManager for the project.
 */
export function WorkflowsTreeItem({workflows}: WorkflowsTreeItemProps) {
    return (
        <StyledTreeItem nodeId="workflows" labelText={"Workflows"} labelIcon={Work}>
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

