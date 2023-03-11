package uploader.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uploader.http.models.FileType;

/**
 * Service for the Uploader Microservice.
 */
@Service
public interface UploadService {

    /**
     * Stores the uploaded file.
     *
     * @param projectName   name of the project
     * @param fileType      type of the file
     * @param multipartFile file to be stored
     */
    void store(String projectName, FileType fileType, MultipartFile multipartFile);

    /**
     * Creates a project.
     *
     * @param name        name of the project
     * @param description description of the project
     * @param owner       owner of the project
     */
    void createProject(String name, String description, String owner);
}
