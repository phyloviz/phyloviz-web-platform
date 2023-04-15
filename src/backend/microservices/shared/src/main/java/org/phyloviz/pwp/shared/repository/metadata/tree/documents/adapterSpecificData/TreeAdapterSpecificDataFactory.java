package org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TreeAdapterSpecificDataFactory {

    public Class<? extends TreeAdapterSpecificData> getClass(String adapterId) {
        return switch (adapterId) {
            case "s3" -> TreeS3AdapterSpecificData.class;
            case "phyloDB" -> TreePhyloDBAdapterSpecificData.class;
            default -> throw new IllegalArgumentException("Unknown adapter id: " + adapterId);
        };
    }
}
