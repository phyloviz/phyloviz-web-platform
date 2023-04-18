package org.phyloviz.pwp.shared.repository.metadata.typing_data.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.phyloviz.pwp.shared.adapters.typing_data.TypingDataAdapterId;
import org.phyloviz.pwp.shared.adapters.typing_data.adapter.specific_data.TypingDataAdapterSpecificData;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Metadata for a representation of a typing data.
 */
@Document(collection = "#{@mongoMetadataCollectionNames.typingDataMetadataCollection}")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypingDataMetadata {
    @Id
    private String id;

    private String projectId;
    private String typingDataId;
    private String name;
    private TypingDataAdapterId adapterId;
    private TypingDataAdapterSpecificData adapterSpecificData;

    public TypingDataMetadata(String projectId, String typingDataId, String name, TypingDataAdapterId adapterId,
                              TypingDataAdapterSpecificData adapterSpecificData) {
        this.projectId = projectId;
        this.typingDataId = typingDataId;
        this.name = name;
        this.adapterId = adapterId;
        this.adapterSpecificData = adapterSpecificData;
    }
}
