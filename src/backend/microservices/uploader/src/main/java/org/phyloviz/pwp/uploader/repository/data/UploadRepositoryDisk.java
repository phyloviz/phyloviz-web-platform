package org.phyloviz.pwp.uploader.repository.data;

import org.phyloviz.pwp.uploader.utils.UploaderLogger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Implementation of the {@link UploadRepository} interface that stores the uploaded files in the disk.
 */
@Repository
@Primary
public class UploadRepositoryDisk implements UploadRepository {

    private final String path = new File("").getAbsolutePath() + "\\diskUploadedFiles";
    private final String adapterId = "disk";

    @Override
    public boolean storeProfile(String location, MultipartFile multipartFile) {
        File file = new File(path + location);

        try {
            multipartFile.transferTo(file);
            return true;
        } catch (IOException | IllegalStateException e) {
            UploaderLogger.warn("Error while storing file in disk: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String getLocation() {
        return path;
    }

    @Override
    public String getAdapterId() {
        return adapterId;
    }
}
