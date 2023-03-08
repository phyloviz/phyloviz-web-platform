package phylovizwebplatform.uploader.repository;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Implementation of the repository for the uploader module that stores the data in the disk.
 */
public class UploadRepositoryDisk implements UploadRepository {

    // TODO: To be changed
    final String path = "C:\\ISEL\\Courses\\6th Semester\\PFC\\phyloviz-online\\files\\";

    @Override
    public void store(MultipartFile multipartFile) throws IOException {
        storeData(multipartFile);
        storeMetadata();
    }

    private void storeMetadata() {
        // TODO
    }

    private void storeData(MultipartFile multipartFile) throws IOException {
        String destination = path + multipartFile.getOriginalFilename();
        File file = new File(destination);
        multipartFile.transferTo(file);
    }
}
