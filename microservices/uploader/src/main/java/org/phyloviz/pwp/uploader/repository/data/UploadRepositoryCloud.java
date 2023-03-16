package org.phyloviz.pwp.uploader.repository.data;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

/**
 * Implementation of the {@link UploadRepository} interface for the Cloud environment.
 */
@Repository
public class UploadRepositoryCloud implements UploadRepository {

    @Override
    public boolean storeProfile(String location, MultipartFile multipartFile) {
        return false;
    }

    @Override
    public String getLocation() {
        return null;
    }

    @Override
    public String getAdapterId() {
        return null;
    }
}
