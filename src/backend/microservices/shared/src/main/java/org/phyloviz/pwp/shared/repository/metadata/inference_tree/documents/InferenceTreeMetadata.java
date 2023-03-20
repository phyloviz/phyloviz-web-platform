package org.phyloviz.pwp.shared.repository.metadata.inference_tree.documents;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.phyloviz.pwp.shared.repository.metadata.Metadata;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

@Document(collection = "inference-trees")
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class InferenceTreeMetadata extends Metadata {
    public List<String> treeViewIds;

    public InferenceTreeMetadata(String projectId, String resourceId, String url, String adapterId, Object adapterSpecificData) {
        super(projectId, resourceId, url, adapterId, adapterSpecificData);
        treeViewIds = Collections.emptyList();
    }
}
