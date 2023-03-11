package uploader.repository.data;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

/**
 * Implementation of the repository for the uploader module that stores the data in the cloud.
 */
@Repository
public class UploadRepositoryCloud implements UploadRepository {

    @Override
    public boolean store(String location, MultipartFile multipartFile) {
        return false;
    }

    @Override
    public String getLocation() {
        return null;
    }
}
