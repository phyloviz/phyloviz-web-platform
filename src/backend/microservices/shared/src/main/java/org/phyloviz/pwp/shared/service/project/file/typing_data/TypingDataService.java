package org.phyloviz.pwp.shared.service.project.file.typing_data;

import org.phyloviz.pwp.shared.repository.metadata.typing_data.documents.TypingDataMetadata;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.GetTypingDataProfilesOutput;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.GetTypingDataSchemaOutput;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.TypingDataInfo;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.UploadTypingDataOutput;
import org.springframework.web.multipart.MultipartFile;

public interface TypingDataService {

    UploadTypingDataOutput uploadTypingData(String projectId, MultipartFile multipartFile, String userId);

    TypingDataMetadata getTypingDataMetadata(String typingDataId);

    TypingDataInfo getTypingDataInfo(String typingDataId);

    void deleteTypingData(String projectId, String typingDataId, String userId);

    void deleteTypingData(String projectId);

    GetTypingDataSchemaOutput getTypingDataSchema(String projectId, String typingDataId, String userId);

    GetTypingDataProfilesOutput getTypingDataProfiles(String projectId, String typingDataId, int limit, int offset,
                                                      String userId);
}
