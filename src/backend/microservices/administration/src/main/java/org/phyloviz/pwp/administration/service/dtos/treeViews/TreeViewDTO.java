package org.phyloviz.pwp.administration.service.dtos.treeViews;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.TreeViewMetadata;

@Data
public class TreeViewDTO {
    private final String treeViewId;
    private final String name;
    private final String layout;
    private final TreeViewSourceDTO source;

    public TreeViewDTO(TreeViewMetadata treeViewMetadata) {
        this.treeViewId = treeViewMetadata.getId();
        this.name = treeViewMetadata.getName();
        this.layout = treeViewMetadata.getLayout();
        this.source = new TreeViewSourceDTO(treeViewMetadata.getSource());
    }
}