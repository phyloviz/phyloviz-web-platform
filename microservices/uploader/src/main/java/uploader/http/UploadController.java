package uploader.http;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import uploader.http.models.CreateProjectModel;
import uploader.http.models.FileType;
import uploader.service.UploadService;

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
     * Uploads the data from the uploader module.
     * The user can load a profile, fasta, newick datasets or auxiliary data.
     *
     * @param projectName the name of the project to which the data will be uploaded
     * @param type        the type of the data to be uploaded (profile, fasta, newick, auxiliary)
     * @param file        the file to be uploaded
     * @return a message indicating that the data was successfully uploaded
     */
    @PostMapping(path = "/storage", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String uploadData(
            @RequestParam String projectName,
            @RequestParam String type,
            @RequestPart MultipartFile file
    ) {
        uploadService.store(projectName, FileType.valueOf(type), file);

        return "You successfully uploaded";
    }

    /**
     * Creates a project.
     *
     * @param createProjectModel the project to be created following the CreateProjectModel format
     * @return a message indicating that the project was successfully created
     */
    @PostMapping("/project")
    public String createProject(
            @RequestBody CreateProjectModel createProjectModel
    ) {
        uploadService.createProject(
                createProjectModel.getName(),
                createProjectModel.getDescription(),
                "user"
        ); // TODO: 11/03/2023 Change owner based on the user logged in

        return "You successfully created a project";
    }
}
