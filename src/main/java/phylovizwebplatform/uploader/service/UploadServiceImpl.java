package phylovizwebplatform.uploader.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import phylovizwebplatform.uploader.repository.UploadRepository;

import java.io.IOException;

@Service
public class UploadServiceImpl implements UploadService {

    private final UploadRepository uploadRepository;

    public UploadServiceImpl(UploadRepository uploadRepository) {
        this.uploadRepository = uploadRepository;
    }

    @Override
    public void store(MultipartFile multipartFile) throws IOException {
        uploadRepository.store(multipartFile);
    }
}
