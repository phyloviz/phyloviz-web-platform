package org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.phyloviz.pwp.shared.adapters.isolate_data.IsolateDataAdapterId;
import org.phyloviz.pwp.shared.adapters.isolate_data.adapter.specific_data.IsolateDataAdapterSpecificData;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Metadata for a representation of an isolate data.
 */
@Document(collection = "#{@mongoMetadataCollectionNames.isolateDataMetadataCollection}")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IsolateDataMetadata {
    @Id
    private String id;

    private String projectId;
    private String isolateDataId;
    private List<String> keys;
    private String name;
    private IsolateDataAdapterId adapterId;
    private IsolateDataAdapterSpecificData adapterSpecificData;

    public IsolateDataMetadata(String projectId, String isolateDataId, List<String> keys, String name,
                               IsolateDataAdapterId adapterId, IsolateDataAdapterSpecificData adapterSpecificData) {
        this.projectId = projectId;
        this.isolateDataId = isolateDataId;
        this.keys = keys;
        this.name = name;
        this.adapterId = adapterId;
        this.adapterSpecificData = adapterSpecificData;
    }
}
