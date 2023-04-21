package org.phyloviz.pwp.shared.service.project.file.typing_data;

import org.phyloviz.pwp.shared.adapters.typing_data.TypingDataAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.documents.TypingDataMetadata;

import java.util.List;

public interface TypingDataMetadataService {

    TypingDataMetadata getTypingDataMetadata(String projectId, String typingDataId, String userId);

    TypingDataMetadata getTypingDataMetadata(String typingDataId);

    TypingDataMetadata getTypingDataMetadataByAdapterIdOrNull(String typingDataId, TypingDataAdapterId adapterId);

    TypingDataMetadata saveTypingDataMetadata(TypingDataMetadata typingData);

    List<TypingDataMetadata> getAllTypingDataMetadata(String typingDataId);

    /**
     * Find all typing data metadata from a project id. Only one typing data metadata per typing data resource.
     *
     * @param projectId the id of the project
     * @return a list of typing data metadata
     */
    List<TypingDataMetadata> getAllTypingDataMetadataByProjectId(String projectId);

    void deleteTypingData(TypingDataMetadata typingDataMetadata);

    void assertExists(String projectId, String typingDataId, String userId);
}
