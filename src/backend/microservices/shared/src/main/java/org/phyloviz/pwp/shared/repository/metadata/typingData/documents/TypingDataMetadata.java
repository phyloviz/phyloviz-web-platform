package org.phyloviz.pwp.shared.repository.metadata.typingData.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private String name;
    private String url;
    private String adapterId;
    private TypingDataAdapterSpecificData adapterSpecificData;

    public TypingDataMetadata(String projectId, String typingDataId, String name, String url, String adapterId,
                              TypingDataAdapterSpecificData adapterSpecificData) {
        this.projectId = projectId;
        this.typingDataId = typingDataId;
        this.name = name;
        this.url = url;
        this.adapterId = adapterId;
        this.adapterSpecificData = adapterSpecificData;
    }
}
