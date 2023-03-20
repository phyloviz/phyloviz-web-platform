package org.phyloviz.pwp.shared.repository.data;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

/**
 * Repository for the Uploader Microservice.
 */
@Repository
public interface ObjectStorageRepository {

    /**
     * Stores the data.
     *
     * @param location      location where the file will be stored
     * @param multipartFile file to be stored
     * @return true if the data was stored successfully, false otherwise
     */
    boolean store(String location, MultipartFile multipartFile);

    /**
     * Deletes the data.
     *
     * @param url      location where the file is stored
     * @return true if the data was deleted successfully, false otherwise
     */
    boolean delete(String url);

    /**
     * Get location of the repository.
     *
     * @return location of the repository
     */
    String getLocation();

    /**
     * Get the adapter id.
     *
     * @return adapter id
     */
    String getAdapterId();
}
