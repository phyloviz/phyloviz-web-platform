package org.phyloviz.pwp.file_transfer.http.controllers;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.file_transfer.http.models.isolate_data.UploadIsolateDataOutputModel;
import org.phyloviz.pwp.file_transfer.http.models.typing_data.UploadTypingDataOutputModel;
import org.phyloviz.pwp.file_transfer.service.FileTransferService;
import org.phyloviz.pwp.shared.domain.User;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.UploadIsolateDataOutput;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.UploadTypingDataOutput;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class FileTransferController {

    private final FileTransferService fileTransferService;

    /**
     * Uploads a typing data.
     *
     * @param projectId the name of the project to which the typing data will be uploaded
     * @param file      the file to be uploaded
     * @param user      the user that is uploading the typing data
     * @return information about the uploaded typing data
     */
    @PostMapping(path = "/projects/{projectId}/files/typing-data", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public UploadTypingDataOutputModel uploadTypingData(
            @PathVariable String projectId,
            @RequestPart MultipartFile file,
            User user
    ) {
        UploadTypingDataOutput uploadTypingDataOutput = fileTransferService.uploadTypingData(projectId, file, user.getId());

        return new UploadTypingDataOutputModel(uploadTypingDataOutput);
    }

    /**
     * Uploads an isolate data.
     *
     * @param projectId the name of the project to which the isolate data will be uploaded
     * @param file      the file to be uploaded
     * @param user      the user that is uploading the isolate data
     * @return information about the uploaded isolate data
     */
    @PostMapping(path = "/projects/{projectId}/files/isolate-data", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public UploadIsolateDataOutputModel uploadIsolateData(
            @PathVariable String projectId,
            @RequestPart MultipartFile file,
            User user
    ) {
        UploadIsolateDataOutput uploadIsolateDataOutput = fileTransferService.uploadIsolateData(projectId, file, user.getId());

        return new UploadIsolateDataOutputModel(uploadIsolateDataOutput);
    }
}
