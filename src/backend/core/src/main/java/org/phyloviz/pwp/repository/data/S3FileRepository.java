package org.phyloviz.pwp.repository.data;

import org.springframework.web.multipart.MultipartFile;

public interface S3FileRepository {

    /**
     * Uploads the file.
     *
     * @param url           url where the file will be stored
     * @param multipartFile file to be stored
     * @return true if the file was stored successfully, false otherwise
     */
    boolean upload(String url, MultipartFile multipartFile);

    /**
     * Downloads the contents of a file.
     *
     * @param url url where the file is stored
     * @return the contents of the file
     */
    String download(String url);

    /**
     * Deletes the file .
     *
     * @param url location where the file is stored
     * @return true if the file was deleted successfully, false otherwise
     */
    boolean delete(String url);
}
