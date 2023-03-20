package org.phyloviz.pwp.uploader.http.controllers;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.domain.User;
import org.phyloviz.pwp.uploader.http.controllers.models.uploadTypingDataset.UploadTypingDatasetOutputModel;
import org.phyloviz.pwp.uploader.service.UploadService;
import org.phyloviz.pwp.uploader.service.dtos.uploadTypingDataset.UploadTypingDatasetOutputDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller for the Uploader Microservice.
 */
@RestController
@RequiredArgsConstructor
public class UploadController {

    private final UploadService uploadService;

    /**
     * Uploads a typing dataset.
     *
     * @param projectId the name of the project to which the typing dataset will be uploaded
     * @param file      the file to be uploaded
     * @return a message indicating that the data was successfully uploaded
     */
    @PostMapping(path = "/typing-datasets", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public UploadTypingDatasetOutputModel uploadTypingDataset(
            @RequestParam String projectId,
            @RequestPart MultipartFile file,
            User user
    ) {
        UploadTypingDatasetOutputDTO uploadTypingDatasetOutputDTO = uploadService.uploadTypingDataset(projectId, file, user.toDTO());

        return new UploadTypingDatasetOutputModel(uploadTypingDatasetOutputDTO);
    }
}
