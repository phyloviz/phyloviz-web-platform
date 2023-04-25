import {GetWorkflowStatusOutputModel, Workflow} from "./models/getWorkflowStatus/GetWorkflowStatusOutputModel"
import {CreateWorkflowOutputModel} from "./models/createWorkflow/CreateWorkflowOutputModel"
import {CreateWorkflowInputModel} from "./models/createWorkflow/CreateWorkflowInputModel"
import {GetWorkflowsOutputModel} from "./models/getWorkflows/GetWorkflowsOutputModel"

export namespace MockComputeService {

    const projectsWorkflows = new Map<string, Map<string, Workflow>>()
    const WORKFLOW_DURATION = 5000

    const mockWorkflowData = new Map<string, any>([
        ["compute-distance-matrix", {
            typingDataId: "e0d6f7e2-e9b5-4feb-a3c6-aea64c23ffc0",
            datasetId: "6446babf22cde24152e769b9",
            projectId: "6446ba6022cde24152e769b8",
            function: "hamming",
            distanceMatrixId: "6446ba6022cde24da4hh19b8"
        }],
        ["compute-tree", {treeId: "8039f350-e12f-4877-8887-67caa258a143"}],
        ["compute-tree-view", {treeViewId: "8039f350-e12f-4877-8887-67caa258a143"}],
        // Add more mock data here if needed
    ])

    /**
     * Create a workflow.
     *
     * @param projectId The project id.
     * @param createWorkflowInputModel The create workflow input model.
     * @returns The create workflow output model.
     */
    export async function createWorkflow(
        projectId: string,
        createWorkflowInputModel: CreateWorkflowInputModel
    ): Promise<CreateWorkflowOutputModel> {
        if (!projectsWorkflows.has(projectId))
            projectsWorkflows.set(projectId, new Map<string, GetWorkflowStatusOutputModel>())

        const workflows = projectsWorkflows.get(projectId)
        const workflowId = Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15)
        workflows!.set(workflowId, {
            workflowId,
            type: createWorkflowInputModel.type,
            status: "RUNNING",
            data: createWorkflowInputModel.properties as Map<string, string>
        })

        setTimeout(() => {
            workflows!.set(workflowId, {
                workflowId,
                type: createWorkflowInputModel.type,
                status: "SUCCESS",
                data: mockWorkflowData.get(createWorkflowInputModel.type)
            })
        }, WORKFLOW_DURATION)

        return {
            workflowId
        }
    }

    /**
     * Get the status of a workflow.
     *
     * @param projectId The project id.
     * @param workflowId The workflow id.
     * @returns The workflow status.
     */
    export async function getWorkflowStatus(
        projectId: string,
        workflowId: string
    ): Promise<GetWorkflowStatusOutputModel> {
        if (!projectsWorkflows.has(projectId))
            projectsWorkflows.set(projectId, new Map<string, GetWorkflowStatusOutputModel>())

        const workflows = projectsWorkflows.get(projectId)
        if (!workflows!.has(workflowId))
            throw new Error("Workflow not found")

        const workflow = workflows!.get(workflowId)!

        return {
            workflowId,
            type: workflow.type,
            status: workflow.status,
            data: workflow.data
        }
    }

    /**
     * Get the workflows of a project.
     *
     * @param projectId The project id.
     * @returns The workflows.
     */
    export async function getWorkflows(
        projectId: string
    ): Promise<GetWorkflowsOutputModel> {
        if (!projectsWorkflows.has(projectId))
            projectsWorkflows.set(projectId, new Map<string, GetWorkflowStatusOutputModel>())

        return {
            workflows: Array.from(projectsWorkflows.get(projectId)!.values())
        }
    }
}