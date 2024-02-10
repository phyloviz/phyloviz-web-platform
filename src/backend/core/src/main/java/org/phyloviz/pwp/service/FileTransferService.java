package org.phyloviz.pwp.service;

import org.phyloviz.pwp.service.dtos.files.isolate_data.UploadIsolateDataOutput;
import org.phyloviz.pwp.service.dtos.files.typing_data.UploadTypingDataOutput;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service for the File Transfer Microservice.
 */
public interface FileTransferService {

    /**
     * Uploads a typing data.
     *
     * @param projectId the name of the project to which the typing data will be uploaded
     * @param file      the file to be uploaded
     * @param type      the type of the typing data
     * @param userId    the id of the user that is uploading the typing data
     * @return information about the uploaded typing data
     */
    UploadTypingDataOutput uploadTypingData(String projectId, MultipartFile file, String type, String userId);

    /**
     * Downloads the contents of a typing data file.
     *
     * @param projectId    the name of the project the typing data belongs to
     * @param typingDataId the id of the typing data to be downloaded
     * @param userId       the id of the user that is downloading the typing data
     * @return the contents of the typing data file
     */
    String downloadTypingData(String projectId, String typingDataId, String userId);

    /**
     * Uploads an isolate data.
     *
     * @param projectId the name of the project to which the isolate data will be uploaded
     * @param file      the file to be uploaded
     * @param userId    the id of the user that is uploading the isolate data
     * @return information about the uploaded isolate data
     */
    UploadIsolateDataOutput uploadIsolateData(String projectId, MultipartFile file, String userId);

    /**
     * Downloads the contents of an isolate data file.
     *
     * @param projectId     the name of the project the isolate data belongs to
     * @param isolateDataId the id of the isolate data to be downloaded
     * @param userId        the id of the user that is downloading the isolate data
     * @return the contents of the isolate data file
     */
    String downloadIsolateData(String projectId, String isolateDataId, String userId);
}
