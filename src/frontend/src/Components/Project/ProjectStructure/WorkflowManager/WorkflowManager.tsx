import * as React from "react";
import {StyledTreeItem} from "../Utils/StyledTreeItem";
import {Work} from "@mui/icons-material";
import {useWorkflowManager} from "./useWorkflowManager";
import {WorkflowTreeItem} from "./WorkflowTreeItem";


/**
 * WorkflowManager for the project.
 */
export function WorkflowManager() {
    const {workflows, setUpdated, loading, error} = useWorkflowManager();

    return (
        <StyledTreeItem nodeId="workflowManager" labelText={"Workflows"} labelIcon={Work}>
            {
                workflows.map((workflow, index) => {
                    return <WorkflowTreeItem nodeId={"workflowManager" + index.toString()} workflow={workflow}/>
                })
            }
        </StyledTreeItem>
    )
}

