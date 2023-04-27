package org.phyloviz.pwp.shared.repository.data.typing_data.repository;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.S3FileRepository;
import org.phyloviz.pwp.shared.repository.data.typing_data.repository.specific_data.TypingDataDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.typing_data.repository.specific_data.TypingDataS3DataRepositorySpecificData;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
@RequiredArgsConstructor
public class TypingDataS3DataRepository implements TypingDataDataRepository {

    private final S3FileRepository s3FileRepository;

    @Override
    public TypingDataDataRepositorySpecificData uploadTypingData(String projectId, String typingDataId, MultipartFile multipartFile) {
        String url = projectId + "/typing-data/" + typingDataId;

        s3FileRepository.store(url, multipartFile);

        return new TypingDataS3DataRepositorySpecificData(s3FileRepository.getLocation() + "/" + url, multipartFile.getOriginalFilename());
    }

    @Override
    public String getTypingData(TypingDataDataRepositorySpecificData typingDataDataRepositorySpecificData) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean isFileRepository() {
        return true;
    }

    @Override
    public void deleteTypingData(TypingDataDataRepositorySpecificData typingDataDataRepositorySpecificData) {
        TypingDataS3DataRepositorySpecificData repositorySpecificData =
                (TypingDataS3DataRepositorySpecificData) typingDataDataRepositorySpecificData;

        s3FileRepository.delete(repositorySpecificData.getUrl());
    }
}