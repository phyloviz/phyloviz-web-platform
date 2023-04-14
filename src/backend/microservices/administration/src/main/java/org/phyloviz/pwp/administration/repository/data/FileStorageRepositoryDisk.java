package org.phyloviz.pwp.administration.repository.data;

import org.phyloviz.pwp.shared.utils.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Implementation of the {@link FileStorageRepository} interface that stores the uploaded files in the disk.
 */
@Repository
public class FileStorageRepositoryDisk implements FileStorageRepository {

    private static final String ADAPTER_ID = "disk";
    private final String path = new File("").getAbsolutePath() + "\\diskUploadedFiles";

    @Override
    public boolean store(String url, MultipartFile multipartFile) {
        File file = new File(path + url);

        try {
            multipartFile.transferTo(file);
            return true;
        } catch (IOException | IllegalStateException e) {
            Logger.warn("Error while storing file in disk: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(String url) {
        File file = new File(url);

        if (file.exists())
            return file.delete();

        return false;
    }

    @Override
    public String getLocation() {
        return path;
    }

    @Override
    public String getAdapterId() {
        return ADAPTER_ID;
    }
}
