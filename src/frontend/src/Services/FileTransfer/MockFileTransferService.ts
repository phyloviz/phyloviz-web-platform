import {UploadTypingDataOutputModel} from "./models/uploadTypingData/UploadTypingDataOutputModel"
import {UploadIsolateDataOutputModel} from "./models/uploadIsolateData/UploadIsolateDataOutputModel"
import {MockAdministrationService} from "../Administration/MockAdministrationService";

export namespace MockFileTransferService {

    import mockProjects = MockAdministrationService.mockProjects;

    /**
     * Uploads a typing data file to a project.
     *
     * @param projectId the name of the project to which the typing data will be uploaded
     * @param file      the file to be uploaded
     * @return a promise that resolves to the uploaded typing data information
     */
    export async function uploadTypingData(
        projectId: string,
        file: File
    ): Promise<UploadTypingDataOutputModel> {
        const typingDataId = Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15)
        mockProjects.get(projectId)!.files.typingData.push({
            typingDataId,
            name: file.name
        })

        return {
            projectId,
            typingDataId
        }
    }

    /**
     * Uploads an isolate data file to a project.
     *
     * @param projectId the name of the project to which the isolate data will be uploaded
     * @param file      the file to be uploaded
     * @return a promise that resolves to the uploaded isolate data information
     */
    export async function uploadIsolateData(
        projectId: string,
        file: File
    ): Promise<UploadIsolateDataOutputModel> {
        const isolateDataId = Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15)
        mockProjects.get(projectId)!.files.isolateData.push({
            isolateDataId,
            name: file.name
        })

        return {
            projectId,
            isolateDataId
        }
    }
}
