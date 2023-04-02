package org.phyloviz.pwp.shared.repository.metadata.isolateData.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Metadata for a representation of an isolate data.
 */
@Document(collection = "isolate-data")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeAlias("isolateDataMetadata")
public class IsolateDataMetadata {
    @Id
    private String id;

    private String projectId;
    private String isolateDataId;
    private String name;
    private String url;
    private String adapterId;
    private IsolateDataAdapterSpecificData adapterSpecificData;

    public IsolateDataMetadata(String projectId, String isolateDataId, String name, String url, String adapterId,
                               IsolateDataAdapterSpecificData adapterSpecificData) {
        this.projectId = projectId;
        this.isolateDataId = isolateDataId;
        this.name = name;
        this.url = url;
        this.adapterId = adapterId;
        this.adapterSpecificData = adapterSpecificData;
    }
}
