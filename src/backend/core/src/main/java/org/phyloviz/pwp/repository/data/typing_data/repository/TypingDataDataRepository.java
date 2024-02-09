package org.phyloviz.pwp.repository.data.typing_data.repository;

import org.phyloviz.pwp.repository.data.typing_data.repository.specific_data.TypingDataDataRepositorySpecificData;
import org.phyloviz.pwp.service.dtos.files.typing_data.GetTypingDataProfilesOutput;
import org.phyloviz.pwp.service.dtos.files.typing_data.GetTypingDataSchemaOutput;
import org.springframework.web.multipart.MultipartFile;

public interface TypingDataDataRepository {

    TypingDataDataRepositorySpecificData uploadTypingData(String projectId, String typingDataId, MultipartFile multipartFile);

    String downloadTypingData(TypingDataDataRepositorySpecificData typingDataDataRepositorySpecificData);

    GetTypingDataSchemaOutput getTypingDataSchema(TypingDataDataRepositorySpecificData typingDataDataRepositorySpecificData);

    GetTypingDataProfilesOutput getTypingDataProfiles(TypingDataDataRepositorySpecificData typingDataDataRepositorySpecificData,
                                                      int limit, int offset);

    void deleteTypingData(TypingDataDataRepositorySpecificData typingDataDataRepositorySpecificData);
}
