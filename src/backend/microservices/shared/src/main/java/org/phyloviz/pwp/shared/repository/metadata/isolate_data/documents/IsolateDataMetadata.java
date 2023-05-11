package org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.isolate_data.IsolateDataDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.specific_data.IsolateDataDataRepositorySpecificData;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Metadata for a representation of an isolate data.
 */
@Document(collection = "isolate-data")
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
    private IsolateDataDataRepositoryId repositoryId;
    private IsolateDataDataRepositorySpecificData repositorySpecificData;

    public IsolateDataMetadata(String projectId, String isolateDataId, List<String> keys, String name,
                               IsolateDataDataRepositoryId repositoryId, IsolateDataDataRepositorySpecificData repositorySpecificData) {
        this.projectId = projectId;
        this.isolateDataId = isolateDataId;
        this.keys = keys;
        this.name = name;
        this.repositoryId = repositoryId;
        this.repositorySpecificData = repositorySpecificData;
    }
}
