package org.phyloviz.pwp.shared.repository.metadata.tree_view.documents;

import lombok.*;
import org.phyloviz.pwp.shared.repository.metadata.Metadata;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tree-views")
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class TreeViewMetadata extends Metadata {
    public TreeViewMetadata(String projectId, String resourceId, String url, String adapterId, Object adapterSpecificData) {
        super(projectId, resourceId, url, adapterId, adapterSpecificData);
    }
}
