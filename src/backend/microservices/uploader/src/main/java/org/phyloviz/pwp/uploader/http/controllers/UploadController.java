package org.phyloviz.pwp.uploader.http.controllers;

import lombok.AllArgsConstructor;
import org.phyloviz.pwp.shared.domain.User;
import org.phyloviz.pwp.uploader.http.controllers.models.uploadProfile.UploadProfileOutputModel;
import org.phyloviz.pwp.uploader.service.UploadService;
import org.phyloviz.pwp.uploader.service.dtos.uploadeProfile.UploadProfileOutputDTO;
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
@AllArgsConstructor
public class UploadController {

    private final UploadService uploadService;

    /**
     * Uploads a profile dataset.
     *
     * @param projectId the name of the project to which the profile data will be uploaded
     * @param file      the file to be uploaded
     * @return a message indicating that the data was successfully uploaded
     */
    @PostMapping(path = "/profiles", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public UploadProfileOutputModel uploadProfile(
            @RequestParam String projectId,
            @RequestPart MultipartFile file,
            User user
    ) {
        UploadProfileOutputDTO uploadProfileOutputDTO = uploadService.uploadProfile(projectId, file, user.toDTO());

        return new UploadProfileOutputModel(uploadProfileOutputDTO);
    }


}
