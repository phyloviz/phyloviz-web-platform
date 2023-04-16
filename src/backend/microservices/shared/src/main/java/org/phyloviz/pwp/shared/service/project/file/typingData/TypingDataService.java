package org.phyloviz.pwp.shared.service.project.file.typingData;

import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.TypingDataMetadata;
import org.phyloviz.pwp.shared.service.dtos.files.GetTypingDataProfilesOutput;
import org.phyloviz.pwp.shared.service.dtos.files.GetTypingDataSchemaOutput;
import org.phyloviz.pwp.shared.service.dtos.files.TypingDataMetadataDTO;
import org.phyloviz.pwp.shared.service.dtos.files.UploadTypingDataOutput;
import org.springframework.web.multipart.MultipartFile;

public interface TypingDataService {

    UploadTypingDataOutput uploadTypingData(String projectId, MultipartFile multipartFile, String userId);

    TypingDataMetadata getTypingDataMetadata(String projectId, String typingDataId, String userId);

    TypingDataMetadata getTypingDataMetadata(String typingDataId);

    TypingDataMetadataDTO getTypingDataMetadataDTO(String typingDataId);

    void assertExists(String projectId, String typingDataId, String userId);

    TypingDataMetadata saveTypingDataMetadata(TypingDataMetadata typingData);

    void deleteTypingData(String projectId, String typingDataId, String userId);

    void deleteTypingData(String projectId);

    GetTypingDataSchemaOutput getTypingDataSchema(String projectId, String typingDataId, String userId);

    GetTypingDataProfilesOutput getTypingDataProfiles(String projectId, String typingDataId, int limit, int offset,
                                                      String userId);
}
