export interface GetWorkflowStatusOutputModel {
    workflowId: string
    type: WorkflowType
    status: WorkflowStatus
    data?: Map<string, any>
}

export type Workflow = GetWorkflowStatusOutputModel

export type WorkflowType = "compute-distance-matrix" | "compute-tree" | "compute-tree-view" |
    "index-typing-data" | "index-isolate-data" | "index-tree" | "compute-tree-and-index" | "index-typing-and-isolate-data"

export type WorkflowStatus = "RUNNING" | "SUCCESS" | "FAILED"

/**
 * Convert a workflow type to a more readable workflow name.
 *
 * @param type the workflow type.
 * @returns the workflow name
 */
export function workflowTypeToWorkflowName(type: WorkflowType): string {
    switch (type) {
        case "compute-distance-matrix":
            return "Compute Distance Matrix"
        case "compute-tree":
            return "Compute Tree"
        case "compute-tree-view":
            return "Compute Tree View"
        case "index-typing-data":
            return "Index Typing Data"
        case "index-isolate-data":
            return "Index Isolate Data"
        case "index-tree":
            return "Index Tree"
        case "compute-tree-and-index":
            return "Compute Tree and Index"
        case "index-typing-and-isolate-data":
            return "Index Typing and Isolate Data"
    }
}

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
