package uploader.repository.data;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import uploader.http.models.FileType;

import java.io.IOException;

/**
 * Repository for the data to be stored by the uploader module.
 */
@Repository
public interface UploadRepository {

    /**
     * Stores the data.
     *
     * @param location      location where the file will be stored
     * @param multipartFile file to be stored
     * @return true if the data was stored successfully, false otherwise
     */
    boolean store(String location, MultipartFile multipartFile);

    /**
     * Get location of the repository.
     *
     * @return location of the repository
     */
    String getLocation();
}
