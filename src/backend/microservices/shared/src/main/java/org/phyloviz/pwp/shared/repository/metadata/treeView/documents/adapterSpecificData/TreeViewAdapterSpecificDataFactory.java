package org.phyloviz.pwp.shared.repository.metadata.treeView.documents.adapterSpecificData;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData.TreeAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData.TreePhyloDBAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData.TreeS3AdapterSpecificData;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TreeViewAdapterSpecificDataFactory {

    public Class<? extends TreeViewAdapterSpecificData> getClass(String adapterId) {
        return switch (adapterId) {
            case "s3" -> TreeViewS3AdapterSpecificData.class;
            case "phylodb" -> TreeViewPhyloDBAdapterSpecificData.class;
            default -> throw new IllegalArgumentException("Unknown adapter id: " + adapterId);
        };
    }
}
