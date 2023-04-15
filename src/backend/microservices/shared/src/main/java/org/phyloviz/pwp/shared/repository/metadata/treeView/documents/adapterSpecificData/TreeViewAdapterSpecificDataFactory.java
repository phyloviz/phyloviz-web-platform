package org.phyloviz.pwp.shared.repository.metadata.treeView.documents.adapterSpecificData;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TreeViewAdapterSpecificDataFactory {

    public Class<? extends TreeViewAdapterSpecificData> getClass(String adapterId) {
        return switch (adapterId) {
            case "s3" -> TreeViewS3AdapterSpecificData.class;
            case "phyloDB" -> TreeViewPhyloDBAdapterSpecificData.class;
            default -> throw new IllegalArgumentException("Unknown adapter id: " + adapterId);
        };
    }
}
