package phylovizwebplatform.uploader.repository;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Repository for the uploader module.
 */
@Component
public interface UploadRepository {

    void store(MultipartFile multipartFile) throws IOException;
}
