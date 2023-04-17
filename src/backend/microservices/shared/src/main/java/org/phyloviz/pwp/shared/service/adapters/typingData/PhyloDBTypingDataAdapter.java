package org.phyloviz.pwp.shared.service.adapters.typingData;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.adapterSpecificData.TypingDataAdapterSpecificData;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class PhyloDBTypingDataAdapter implements TypingDataAdapter {
    @Override
    public TypingDataAdapterSpecificData uploadTypingData(String projectId, String typingDataId, MultipartFile multipartFile) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getTypingData(TypingDataAdapterSpecificData typingDataAdapterSpecificData) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isFileAdapter() {
        return false;
    }

    @Override
    public void deleteTypingData(TypingDataAdapterSpecificData typingDataAdapterSpecificData) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
