package phylovizwebplatform.uploader.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Service for the uploader module.
 */
@Service
public interface UploadService {

    /**
     * Stores the multipartFile.
     *
     * @param multipartFile The multipartFile to be stored.
     */
    void store(MultipartFile multipartFile) throws IOException;
}
