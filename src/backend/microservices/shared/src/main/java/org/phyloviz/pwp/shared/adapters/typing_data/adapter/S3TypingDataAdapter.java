package org.phyloviz.pwp.shared.adapters.typing_data.adapter;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.typing_data.adapter.specific_data.TypingDataAdapterSpecificData;
import org.phyloviz.pwp.shared.adapters.typing_data.adapter.specific_data.TypingDataS3AdapterSpecificData;
import org.phyloviz.pwp.shared.repository.data.S3FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class S3TypingDataAdapter implements TypingDataAdapter {

    private final S3FileRepository s3FileRepository;

    @Override
    public TypingDataAdapterSpecificData uploadTypingData(String projectId, String typingDataId, MultipartFile multipartFile) {
        String url = projectId + "/typing-data/" + typingDataId;

        s3FileRepository.store(url, multipartFile);

        return new TypingDataS3AdapterSpecificData(s3FileRepository.getLocation() + "/" + url, multipartFile.getOriginalFilename());
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