package org.phyloviz.pwp.shared.adapters.tree;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TreeAdapterFactory {

    private final PhyloDBTreeAdapter phyloDBTreeAdapter;
    private final S3TreeAdapter s3TreeAdapter;

    public TreeAdapter getTreeAdapter(String adapterId) {
        return switch (adapterId) {
            case "phylodb" -> phyloDBTreeAdapter;
            case "s3" -> s3TreeAdapter;
            default -> throw new IllegalArgumentException("Unknown adapter id: " + adapterId);
        };
    }
}
