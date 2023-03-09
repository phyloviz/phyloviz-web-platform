package phylovizwebplatform.uploader.http;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import phylovizwebplatform.uploader.http.models.FileType;
import phylovizwebplatform.uploader.http.models.UploadModel;
import phylovizwebplatform.uploader.repository.UploadRepository;
import phylovizwebplatform.uploader.service.UploadService;

import java.io.IOException;

/**
 * Controller for the uploader module.
 */
@RestController
@RequestMapping("/storage")
public class UploadController {

    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    /**
     * Uploads the data from the uploader module.
     * The user can load a profile, fasta, newick datasets or auxiliary data.
     *
     * @param file The file to be uploaded.
     */
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String uploadData(
            @RequestParam String project,
            @RequestParam String type,
            @RequestPart MultipartFile file
    ) {
        uploadService.store(project, FileType.valueOf(type), file);

        return "You successfully uploaded ";
    }
}
