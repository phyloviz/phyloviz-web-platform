package phylovizwebplatform.uploader.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import phylovizwebplatform.uploader.http.models.FileType;

import java.io.IOException;

/**
 * Repository for the data to be stored by the uploader module.
 */
@Repository
public interface UploadRepository {

    /**
     * Stores the uploaded file.
     *
     * @param multipartFile file to be stored
     */
    boolean store(String location, MultipartFile multipartFile);

    /**
     * Get location of the repository.
     */
    String getLocation();
}
