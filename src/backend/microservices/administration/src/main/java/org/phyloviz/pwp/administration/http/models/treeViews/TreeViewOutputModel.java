package org.phyloviz.pwp.administration.http.models.treeViews;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.treeViews.TreeViewDTO;

@Data
public class TreeViewOutputModel {
    private String treeViewId;
    private String name;
    private String layout;
    private TreeViewSourceOutputModel source;

    public TreeViewOutputModel(TreeViewDTO treeViewDTO) {
        this.treeViewId = treeViewDTO.getTreeViewId();
        this.name = treeViewDTO.getName();
        this.layout = treeViewDTO.getLayout();
        this.source = new TreeViewSourceOutputModel(treeViewDTO.getSource());
    }
}
