import {UploadTypingDataOutputModel} from "./models/uploadTypingData/UploadTypingDataOutputModel"
import {UploadIsolateDataOutputModel} from "./models/uploadIsolateData/UploadIsolateDataOutputModel"
import {MockAdministrationService} from "../Administration/MockAdministrationService";
import {TypingDataType} from "../../Pages/Project/CreateDataset/useCreateDataset";

export namespace MockFileTransferService {

    import mockProjects = MockAdministrationService.mockProjects;

    const DELAY = 1000
    const MOCK_DIR = "/mock"

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
        return await fetch(`${MOCK_DIR}/typing-data-file.txt`).then(response => response.text())
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
        const keys = await file.text().then(text => {
            const lines = text.split("\n")
            return lines[0].split(/\s+/)
        })

        mockProjects.get(projectId)!.files.isolateData.push({
            isolateDataId,
            name: file.name,
            keys: keys
        })

        return {
            projectId,
            isolateDataId
        }
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
        return await fetch(`${MOCK_DIR}/isolate-data-file.txt`).then(response => response.text())
    }
}
