package uploader.repository.data;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Implementation of the repository for the uploader module that stores the data in the disk.
 */
@Repository
@Primary
public class UploadRepositoryDisk implements UploadRepository {

    private final String path = new File("").getAbsolutePath() + "\\diskUploadedFiles";

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
