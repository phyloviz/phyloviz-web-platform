package org.phyloviz.pwp.shared.service.dtos.treeView;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.TreeViewMetadata;

@Data
public class TreeViewMetadataDTO {
    private final String treeViewId;
    private final String name;
    private final String layout;
    private final TreeViewSourceDTO source;

    public TreeViewMetadataDTO(TreeViewMetadata treeViewMetadata) {
        this.treeViewId = treeViewMetadata.getId();
        this.name = treeViewMetadata.getName();
        this.layout = treeViewMetadata.getLayout();
        this.source = new TreeViewSourceDTO(treeViewMetadata.getSource());
    }
}