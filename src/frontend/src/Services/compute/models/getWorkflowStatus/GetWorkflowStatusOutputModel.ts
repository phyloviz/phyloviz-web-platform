export interface GetWorkflowStatusOutputModel {
    workflowId: string
    type: string
    status: "RUNNING" | "COMPLETED"
    data?: any
}

export type Workflow = GetWorkflowStatusOutputModel