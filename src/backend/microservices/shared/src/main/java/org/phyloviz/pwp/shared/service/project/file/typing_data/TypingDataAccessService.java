package org.phyloviz.pwp.shared.service.project.file.typing_data;

import org.phyloviz.pwp.shared.repository.metadata.typing_data.documents.TypingDataMetadata;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.GetTypingDataProfilesOutput;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.GetTypingDataSchemaOutput;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.UploadTypingDataOutput;
import org.springframework.web.multipart.MultipartFile;

public interface TypingDataAccessService {

    UploadTypingDataOutput uploadTypingData(String projectId, MultipartFile multipartFile, String userId);

    TypingDataMetadata getTypingDataMetadata(String projectId, String typingDataId, String userId);

    TypingDataMetadata getTypingDataMetadata(String typingDataId);

    TypingDataMetadata saveTypingDataMetadata(TypingDataMetadata typingData);

    void deleteTypingData(String typingDataId);

    void assertExists(String projectId, String typingDataId, String userId);

    GetTypingDataSchemaOutput getTypingDataSchema(String projectId, String typingDataId, String userId);

    GetTypingDataProfilesOutput getTypingDataProfiles(String projectId, String typingDataId, int limit, int offset,
                                                      String userId);
}
