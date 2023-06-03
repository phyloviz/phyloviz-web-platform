export interface GetWorkflowStatusOutputModel {
    workflowId: string
    type: string
    name: string
    status: WorkflowStatus
    failureReason?: string
    failureLog?: string
    data?: Map<string, any>
}

export type Workflow = GetWorkflowStatusOutputModel

export type WorkflowStatus = "RUNNING" | "SUCCESS" | "FAILED"

/**
 * Convert a workflow status to a more readable status name.
 *
 * @param status the workflow status.
 * @returns the workflow status string
 */
export function workflowStatusToWorkflowStatusString(status: WorkflowStatus): string {
    switch (status) {
        case "RUNNING":
            return "Running"
        case "SUCCESS":
            return "Success"
        case "FAILED":
            return "Failed"
    }
}
