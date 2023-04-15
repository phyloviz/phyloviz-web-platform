package org.phyloviz.pwp.shared.adapters.typingData;

import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.adapterSpecificData.TypingDataAdapterSpecificData;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class S3TypingDataAdapter implements TypingDataAdapter {

    @Override
    public String uploadTypingData(String projectId, String typingDataId, MultipartFile multipartFile) {
        String location = projectId + "/typing-data/" + typingDataId;

        throw new UnsupportedOperationException("Not implemented yet");
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
        throw new UnsupportedOperationException("Not implemented yet.");
    }

}
