import {get, post} from "../utils/apiFetch"
import {WebApiUris} from "../WebApiUris"
import {GetWorkflowOutputModel, GetWorkflowStatusOutputModel} from "./models/getWorkflow/GetWorkflowOutputModel"
import {CreateWorkflowOutputModel} from "./models/createWorkflow/CreateWorkflowOutputModel"
import {CreateWorkflowInputModel} from "./models/createWorkflow/CreateWorkflowInputModel"
import {GetWorkflowsOutputModel} from "./models/getWorkflows/GetWorkflowsOutputModel"
import {MockComputeService} from "./MockComputeService"

namespace ComputeService {

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
        return await post<CreateWorkflowOutputModel>(
            WebApiUris.createWorkflow(projectId),
            JSON.stringify(createWorkflowInputModel)
        )
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
        return await get<GetWorkflowStatusOutputModel>(WebApiUris.getWorkflowStatus(projectId, workflowId))
    }

    /**
     * Get a workflow.
     *
     * @param projectId The project id.
     * @param workflowId The workflow id.
     * @returns The workflow.
     */
    export async function getWorkflow(
        projectId: string,
        workflowId: string
    ): Promise<GetWorkflowOutputModel> {
        return await get<GetWorkflowOutputModel>(WebApiUris.getWorkflow(projectId, workflowId))
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
        return await get<GetWorkflowsOutputModel>(WebApiUris.getWorkflows(projectId))
    }
}


const env = process.env.MOCK_ENV

export default env === "true" ? MockComputeService : ComputeService