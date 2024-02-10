package org.phyloviz.pwp.http.controllers;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.http.models.isolate_data.UploadIsolateDataOutputModel;
import org.phyloviz.pwp.http.models.typing_data.UploadTypingDataOutputModel;
import org.phyloviz.pwp.service.FileTransferService;
import org.phyloviz.pwp.domain.User;
import org.phyloviz.pwp.service.dtos.files.isolate_data.UploadIsolateDataOutput;
import org.phyloviz.pwp.service.dtos.files.typing_data.UploadTypingDataOutput;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Controller for the File Transfer Microservice.
 */
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
    public ResponseEntity<UploadTypingDataOutputModel> uploadTypingData(
            @PathVariable String projectId,
            @RequestPart MultipartFile file,
            @RequestPart String type,
            User user
    ) {
        UploadTypingDataOutput uploadTypingDataOutput = fileTransferService.uploadTypingData(
                projectId, file, type, user.getId()
        );

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{typingDataId}")
                .buildAndExpand(uploadTypingDataOutput.getTypingDataId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(new UploadTypingDataOutputModel(uploadTypingDataOutput));
    }

    /**
     * Downloads the contents of a typing data file.
     *
     * @param projectId    the name of the project the typing data belongs to
     * @param typingDataId the id of the typing data to be downloaded
     * @param user         the user that is downloading the typing data
     * @return the contents of the typing data file
     */
    @GetMapping(path = "/projects/{projectId}/files/typing-data/{typingDataId}/file")
    public String downloadTypingData(
            @PathVariable String projectId,
            @PathVariable String typingDataId,
            User user
    ) {
        return fileTransferService.downloadTypingData(projectId, typingDataId, user.getId());
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
    public ResponseEntity<UploadIsolateDataOutputModel> uploadIsolateData(
            @PathVariable String projectId,
            @RequestPart MultipartFile file,
            User user
    ) {
        UploadIsolateDataOutput uploadIsolateDataOutput = fileTransferService.uploadIsolateData(projectId, file, user.getId());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{isolateDataId}")
                .buildAndExpand(uploadIsolateDataOutput.getIsolateDataId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(new UploadIsolateDataOutputModel(uploadIsolateDataOutput));
    }

    /**
     * Downloads the contents of an isolate data file.
     *
     * @param projectId     the name of the project the isolate data belongs to
     * @param isolateDataId the id of the isolate data to be downloaded
     * @param user          the user that is downloading the isolate data
     * @return the contents of the isolate data file
     */
    @GetMapping(path = "/projects/{projectId}/files/isolate-data/{isolateDataId}/file")
    public String downloadIsolateData(
            @PathVariable String projectId,
            @PathVariable String isolateDataId,
            User user
    ) {
        return fileTransferService.downloadIsolateData(projectId, isolateDataId, user.getId());
    }
}
