package uploader.http;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uploader.http.models.CreateProjectModel;
import uploader.http.models.FileType;
import uploader.service.UploadService;

/**
 * Controller for the uploader module.
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
     * @param file the file to be uploaded
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
     */
    @PostMapping("/project")
    public String createProject(
            @RequestBody CreateProjectModel createProjectModel
    ) {
        uploadService.createProject(createProjectModel.getName(), createProjectModel.getDescription(), "user"); // TODO CHANGE OWNER

        return "You successfully created a project";
    }
}
