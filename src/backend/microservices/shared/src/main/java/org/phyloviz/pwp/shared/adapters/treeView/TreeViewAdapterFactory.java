package org.phyloviz.pwp.shared.adapters.treeView;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TreeViewAdapterFactory {

    private final PhyloDBTreeViewAdapter phyloDBTreeViewAdapter;

    public TreeViewAdapter getTreeViewAdapter(String adapterId) {
        return switch (adapterId) {
            case "phylodb" -> phyloDBTreeViewAdapter;
            default -> throw new IllegalArgumentException("Unknown adapter id: " + adapterId);
        };
    }
}
