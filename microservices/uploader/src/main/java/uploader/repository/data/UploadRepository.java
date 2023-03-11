package uploader.repository.data;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

/**
 * Repository for the Uploader Microservice.
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
