package org.phyloviz.pwp.shared.repository.data;

import org.springframework.web.multipart.MultipartFile;

public interface S3FileRepository {

    /**
     * Stores the file.
     *
     * @param url           url where the file will be stored
     * @param multipartFile file to be stored
     * @return true if the file was stored successfully, false otherwise
     */
    boolean store(String url, MultipartFile multipartFile);

    /**
     * Deletes the file .
     *
     * @param url location where the file is stored
     * @return true if the file was deleted successfully, false otherwise
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
