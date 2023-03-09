package phylovizwebplatform.uploader.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Implementation of the repository for the uploader module that stores the data in the disk.
 */
@Repository
public class UploadRepositoryDisk implements UploadRepository {

    // TODO: To be changed
    private final String path = new File("").getAbsolutePath() + "\\diskUploadedFiles";

    /**
     * Stores the data in the disk.
     *
     * @param location      location where the file will be stored
     * @param multipartFile file to be stored
     * @return true if the data was stored successfully, false otherwise
     */
    @Override
    public boolean store(String location, MultipartFile multipartFile) {
        File file = new File(path + location);

        try {
            multipartFile.transferTo(file);
            return true;
        } catch (IOException | IllegalStateException e) {
            // TODO LOG
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getLocation() {
        return path;
    }
}
