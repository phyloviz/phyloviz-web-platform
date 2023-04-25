import {UploadTypingDataOutputModel} from "./models/uploadTypingData/UploadTypingDataOutputModel"
import {WebApiUris} from "../WebApiUris"
import {UploadIsolateDataOutputModel} from "./models/uploadIsolateData/UploadIsolateDataOutputModel"
import {post} from "../utils/apiFetch"
import {MockFileTransferService} from "./MockFileTransferService";

namespace FileTransferService {

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
        const formData = new FormData()
        formData.append("file", file)

        return await post<UploadTypingDataOutputModel>(
            WebApiUris.uploadTypingData(projectId),
            formData,
            {
                "Content-Length": file.size.toString()
            }
        )
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
        const formData = new FormData()
        formData.append("file", file)

        return await post<UploadIsolateDataOutputModel>(
            WebApiUris.uploadIsolateData(projectId),
            formData,
            {
                "Content-Length": file.size.toString()
            }
        )
    }
}

const env = process.env.MOCK_ENV

export default env === "true" ? MockFileTransferService : FileTransferService