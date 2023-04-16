package org.phyloviz.pwp.shared.service.adapters.typingData;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.S3FileRepository;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.adapterSpecificData.TypingDataAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.adapterSpecificData.TypingDataS3AdapterSpecificData;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class S3TypingDataAdapter implements TypingDataAdapter {

    private final S3FileRepository s3FileRepository;

    @Override
    public String uploadTypingData(String projectId, String typingDataId, MultipartFile multipartFile) {
        String url = projectId + "/typing-data/" + typingDataId;

        s3FileRepository.store(url, multipartFile);

        return s3FileRepository.getLocation() + "/" + url;
    }

    @Override
    public String getTypingData(TypingDataAdapterSpecificData typingDataAdapterSpecificData) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean isFileAdapter() {
        return true;
    }

    @Override
    public void deleteTypingData(TypingDataAdapterSpecificData typingDataAdapterSpecificData) {
        TypingDataS3AdapterSpecificData typingDataS3AdapterSpecificData =
                (TypingDataS3AdapterSpecificData) typingDataAdapterSpecificData;

        s3FileRepository.delete(typingDataS3AdapterSpecificData.getUrl());
    }

}