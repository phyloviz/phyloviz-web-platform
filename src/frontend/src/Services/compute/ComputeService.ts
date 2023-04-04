import {get, post} from "../utils/apiFetch";
import {WebApiUris} from "../WebApiUris";
import {GetJobStatusOutputModel} from "./models/getJobStatus/GetJobStatusOutputModel";
import {CreateJobOutputModel} from "./models/createJob/CreateJobOutputModel";
import {CreateJobInputModel} from "./models/createJob/CreateJobInputModel";
import {GetJobsOutputModel} from "./models/getJobs/GetJobsOutputModel";

export namespace ComputeService {

    /**
     * Create a job.
     *
     * @param projectId The project id.
     * @param createJobInputModel The create job input model.
     * @returns The create job output model.
     */
    export async function createJob(
        projectId: string,
        createJobInputModel: CreateJobInputModel
    ): Promise<CreateJobOutputModel> {
        return await post<CreateJobOutputModel>(
            WebApiUris.createJob(projectId),
            JSON.stringify(createJobInputModel)
        )
    }

    /**
     * Get the status of a job.
     *
     * @param projectId The project id.
     * @param jobId The job id.
     * @returns The job status.
     */
    export async function getJobStatus(
        projectId: string,
        jobId: string
    ): Promise<GetJobStatusOutputModel> {
        return await get<GetJobStatusOutputModel>(WebApiUris.getJobStatus(projectId, jobId))
    }

    /**
     * Get the jobs of a project.
     *
     * @param projectId The project id.
     * @returns The jobs.
     */
    export async function getJobs(
        projectId: string
    ): Promise<GetJobsOutputModel> {
        return await get<GetJobsOutputModel>(WebApiUris.getJobs(projectId))
    }
}