package org.phyloviz.pwp.shared.repository.metadata.isolateData.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.adapterSpecificData.IsolateDataAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.adapterSpecificData.IsolateDataAdapterSpecificData;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Metadata for a representation of an isolate data.
 */
@Document(collection = "#{constants.isolateDataMetadataCollection}")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IsolateDataMetadata {
    @Id
    private String id;

    private String projectId;
    private String isolateDataId;
    private String name;
    private IsolateDataAdapterId adapterId;
    private IsolateDataAdapterSpecificData adapterSpecificData;

    public IsolateDataMetadata(String projectId, String isolateDataId, String name, IsolateDataAdapterId adapterId,
                               IsolateDataAdapterSpecificData adapterSpecificData) {
        this.projectId = projectId;
        this.isolateDataId = isolateDataId;
        this.name = name;
        this.adapterId = adapterId;
        this.adapterSpecificData = adapterSpecificData;
    }
}
