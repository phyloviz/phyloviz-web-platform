package org.phyloviz.pwp.shared.repository.data.typing_data.repository;

import org.phyloviz.pwp.shared.repository.data.typing_data.repository.specific_data.TypingDataDataRepositorySpecificData;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.GetTypingDataProfilesOutput;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.GetTypingDataSchemaOutput;
import org.springframework.web.multipart.MultipartFile;

public interface TypingDataDataRepository {

    TypingDataDataRepositorySpecificData uploadTypingData(String projectId, String typingDataId, MultipartFile multipartFile);

    GetTypingDataSchemaOutput getTypingDataSchema(TypingDataDataRepositorySpecificData typingDataDataRepositorySpecificData);

    GetTypingDataProfilesOutput getTypingDataProfiles(TypingDataDataRepositorySpecificData typingDataDataRepositorySpecificData,
                                                      int limit, int offset);

    void deleteTypingData(TypingDataDataRepositorySpecificData typingDataDataRepositorySpecificData);
}
