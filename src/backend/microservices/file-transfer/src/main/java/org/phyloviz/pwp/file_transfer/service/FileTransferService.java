package org.phyloviz.pwp.file_transfer.service;

import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.UploadIsolateDataOutput;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.UploadTypingDataOutput;
import org.springframework.web.multipart.MultipartFile;

public interface FileTransferService {

    /**
     * Uploads a typing data.
     *
     * @param projectId the name of the project to which the typing data will be uploaded
     * @param file      the file to be uploaded
     * @param type      the type of the typing data
     * @param userId    the if of the user that is uploading the typing data
     * @return information about the uploaded typing data
     */
    UploadTypingDataOutput uploadTypingData(String projectId, MultipartFile file, String type, String userId);

    /**
     * Uploads an isolate data.
     *
     * @param projectId the name of the project to which the isolate data will be uploaded
     * @param file      the file to be uploaded
     * @param userId    the if of the user that is uploading the isolate data
     * @return information about the uploaded isolate data
     */
    UploadIsolateDataOutput uploadIsolateData(String projectId, MultipartFile file, String userId);
}
