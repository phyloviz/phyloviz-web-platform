package org.phyloviz.pwp.uploader.http.controllers;

import org.phyloviz.pwp.uploader.http.controllers.models.createProject.CreateProjectInputModel;
import org.phyloviz.pwp.uploader.http.controllers.models.createProject.CreateProjectOutputModel;
import org.phyloviz.pwp.uploader.http.controllers.models.uploadProfile.UploadProfileOutputModel;
import org.phyloviz.pwp.uploader.service.UploadService;
import org.phyloviz.pwp.uploader.service.dtos.createProject.CreateProjectOutputDTO;
import org.phyloviz.pwp.uploader.service.dtos.uploadeProfile.UploadProfileOutputDTO;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller for the Uploader Microservice.
 */
@RestController
public class UploadController {

    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

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
            BearerTokenAuthentication auth // TODO: 3/15/2023 Add interceptor
    ) {
        UploadProfileOutputDTO uploadProfileOutputDTO = uploadService.uploadProfile(projectId, file);

        return new UploadProfileOutputModel(uploadProfileOutputDTO);
    }

    /**
     * Creates a project.
     *
     * @param createProjectInputModel the project to be created following the CreateProjectModel format
     * @return a message indicating that the project was successfully created
     */
    @PostMapping("/project")
    public CreateProjectOutputModel createProject(
            @RequestBody CreateProjectInputModel createProjectInputModel,
            BearerTokenAuthentication auth
    ) {
        CreateProjectOutputDTO createProjectOutputDTO = uploadService.createProject(
                createProjectInputModel.toDTO(auth) // TODO: 11/03/2023 Change owner based on the user logged in
        );

        return new CreateProjectOutputModel(createProjectOutputDTO);
    }
}
