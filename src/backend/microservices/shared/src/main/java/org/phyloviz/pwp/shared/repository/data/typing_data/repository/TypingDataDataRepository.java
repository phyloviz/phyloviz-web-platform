package org.phyloviz.pwp.shared.repository.data.typing_data.repository;

import org.phyloviz.pwp.shared.repository.data.typing_data.repository.specific_data.TypingDataDataRepositorySpecificData;
import org.springframework.web.multipart.MultipartFile;

public interface TypingDataDataRepository {

    TypingDataDataRepositorySpecificData uploadTypingData(String projectId, String typingDataId, MultipartFile multipartFile);

    String getTypingData(TypingDataDataRepositorySpecificData typingDataDataRepositorySpecificData);

    boolean isFileRepository();

    void deleteTypingData(TypingDataDataRepositorySpecificData typingDataDataRepositorySpecificData);
}
