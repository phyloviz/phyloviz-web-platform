package phylovizwebplatform.uploader.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import phylovizwebplatform.uploader.http.models.FileType;

import java.io.IOException;

/**
 * Service for the uploader module.
 */
@Service
public interface UploadService {

    /**
     * Stores the uploaded file.
     *
     * @param projectName name of the project
     * @param fileType type of the file
     * @param multipartFile file to be stored
     */
    void store(String projectName, FileType fileType, MultipartFile multipartFile);
}
