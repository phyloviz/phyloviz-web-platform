package phylovizwebplatform.uploader;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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
     * The user can load a profile, fasta, snp datasets or auxiliary data.
     *
     * @param file The file to be uploaded.
     */
    @PostMapping
    public String uploadData(
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        uploadService.store(file);

        return "You successfully uploaded " + file.getOriginalFilename() + "!";
    }
}
