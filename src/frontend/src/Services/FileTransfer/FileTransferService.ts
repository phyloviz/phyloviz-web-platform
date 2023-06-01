import {UploadTypingDataOutputModel} from "./models/uploadTypingData/UploadTypingDataOutputModel"
import {WebApiUris} from "../WebApiUris"
import {UploadIsolateDataOutputModel} from "./models/uploadIsolateData/UploadIsolateDataOutputModel"
import {get, post} from "../utils/apiFetch"
import {MockFileTransferService} from "./MockFileTransferService";
import {TypingDataType} from "../../Pages/Project/CreateDataset/useCreateDataset";

const typingDataTypeKey = (type: TypingDataType) => Object.keys(TypingDataType).find(key => (TypingDataType as any)[key] == type)!

namespace FileTransferService {

    /**
     * Uploads a typing data file to a project.
     *
     * @param projectId the name of the project to which the typing data will be uploaded
     * @param file      the file to be uploaded
     * @param typingDataType the type of typing data to be uploaded
     * @return a promise that resolves to the uploaded typing data information
     */
    export async function uploadTypingData(
        projectId: string,
        file: File,
        typingDataType: TypingDataType
    ): Promise<UploadTypingDataOutputModel> {
        const formData = new FormData()
        formData.append("file", file)
        formData.append("type", typingDataTypeKey(typingDataType))

        return await post<UploadTypingDataOutputModel>(
            WebApiUris.uploadTypingData(projectId),
            formData,
            {
                "Content-Length": file.size.toString()
            }
        )
    }

    /**
     * Downloads the contents of a typing data file.
     *
     * @param projectId    the name of the project the typing data belongs to
     * @param typingDataId the id of the typing data to be downloaded
     * @return a promise that resolves to the contents of the typing data file
     */
    export async function downloadTypingData(
        projectId: string,
        typingDataId: string
    ): Promise<string> {
        return await get<string>(WebApiUris.downloadTypingData(projectId, typingDataId))
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

    /**
     * Downloads the contents of an isolate data file.
     *
     * @param projectId     the name of the project the isolate data belongs to
     * @param isolateDataId the id of the isolate data to be downloaded
     * @return a promise that resolves to the contents of the isolate data file
     */
    export async function downloadIsolateData(
        projectId: string,
        isolateDataId: string
    ): Promise<string> {
        return await get<string>(WebApiUris.downloadIsolateData(projectId, isolateDataId))
    }
}

const env = process.env.MOCK_ENV

export default env === "true" ? MockFileTransferService : FileTransferService