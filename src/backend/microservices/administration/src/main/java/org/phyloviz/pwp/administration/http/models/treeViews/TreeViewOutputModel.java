package org.phyloviz.pwp.administration.http.models.treeViews;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.treeView.TreeViewMetadataDTO;

@Data
public class TreeViewOutputModel {
    private String treeViewId;
    private String name;
    private String layout;
    private TreeViewSourceOutputModel source;

    public TreeViewOutputModel(TreeViewMetadataDTO treeViewMetadataDTO) {
        this.treeViewId = treeViewMetadataDTO.getTreeViewId();
        this.name = treeViewMetadataDTO.getName();
        this.layout = treeViewMetadataDTO.getLayout();
        this.source = new TreeViewSourceOutputModel(treeViewMetadataDTO.getSource());
    }
}
