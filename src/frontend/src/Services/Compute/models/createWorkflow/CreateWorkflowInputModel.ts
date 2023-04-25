import {WorkflowType} from "../getWorkflowStatus/GetWorkflowStatusOutputModel"

export interface CreateWorkflowInputModel {
    type: WorkflowType
    properties: any
}
