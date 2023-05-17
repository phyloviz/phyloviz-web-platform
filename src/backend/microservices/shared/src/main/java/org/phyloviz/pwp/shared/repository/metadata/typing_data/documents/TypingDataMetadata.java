package org.phyloviz.pwp.shared.repository.metadata.typing_data.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.typing_data.TypingDataDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.typing_data.repository.specific_data.TypingDataDataRepositorySpecificData;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * Metadata for a representation of a typing data.
 */
@Document(collection = "typing-data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypingDataMetadata {
    @Id
    private String id;

    private String projectId;
    private String typingDataId;
    private String type;
    private String name;
    private Map<TypingDataDataRepositoryId, TypingDataDataRepositorySpecificData> repositorySpecificData;

    public TypingDataMetadata(String projectId, String typingDataId, String type, String name,
                              Map<TypingDataDataRepositoryId, TypingDataDataRepositorySpecificData> repositorySpecificData) {
        this.projectId = projectId;
        this.typingDataId = typingDataId;
        this.type = type;
        this.name = name;
        this.repositorySpecificData = repositorySpecificData;
    }
}
