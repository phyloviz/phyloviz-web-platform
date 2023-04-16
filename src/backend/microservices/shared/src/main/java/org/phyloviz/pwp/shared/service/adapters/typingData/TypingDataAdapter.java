package org.phyloviz.pwp.shared.service.adapters.typingData;

import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.adapterSpecificData.TypingDataAdapterSpecificData;
import org.springframework.web.multipart.MultipartFile;

public interface TypingDataAdapter {

    String uploadTypingData(String projectId, String typingDataId, MultipartFile multipartFile);

    String getTypingData(TypingDataAdapterSpecificData typingDataAdapterSpecificData);

    boolean isFileAdapter();

    void deleteTypingData(TypingDataAdapterSpecificData typingDataAdapterSpecificData);
}
