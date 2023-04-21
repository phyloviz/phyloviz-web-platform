import {WorkflowType} from "../getWorkflowStatus/GetWorkflowStatusOutputModel"

export interface CreateWorkflowInputModel {
    type: WorkflowType
    properties: any
}

export interface ComputeDistanceMatrixWorkflow extends CreateWorkflowInputModel {
    type: 'compute-distance-matrix'
    properties: {
        datasetId: string
        function: string
    }
}

export interface ComputeTreeWorkflow extends CreateWorkflowInputModel {
    type: 'compute-tree'
    properties: {
        datasetId: string
        distanceMatrixId: string
        algorithm: string
    }
}

export interface ComputeTreeViewWorkflow extends CreateWorkflowInputModel {
    type: 'compute-tree-view'
    properties: {
        datasetId: string
        treeId: string
        layout: string
    }
}