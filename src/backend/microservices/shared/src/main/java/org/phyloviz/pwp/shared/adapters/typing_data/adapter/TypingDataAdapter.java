package org.phyloviz.pwp.shared.adapters.typing_data.adapter;

import org.phyloviz.pwp.shared.adapters.typing_data.adapter.specific_data.TypingDataAdapterSpecificData;
import org.springframework.web.multipart.MultipartFile;

public interface TypingDataAdapter {

    TypingDataAdapterSpecificData uploadTypingData(String projectId, String typingDataId, MultipartFile multipartFile);

    String getTypingData(TypingDataAdapterSpecificData typingDataAdapterSpecificData);

    boolean isFileAdapter();

    void deleteTypingData(TypingDataAdapterSpecificData typingDataAdapterSpecificData);
}
