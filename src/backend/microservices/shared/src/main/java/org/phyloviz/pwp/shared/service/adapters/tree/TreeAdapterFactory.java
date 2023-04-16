package org.phyloviz.pwp.shared.service.adapters.tree;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData.TreeAdapterId;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TreeAdapterFactory {

    private final PhyloDBTreeAdapter phyloDBTreeAdapter;
    private final S3TreeAdapter s3TreeAdapter;

    public TreeAdapter getTreeAdapter(TreeAdapterId adapterId) {
        return switch (adapterId) {
            case PHYLODB -> phyloDBTreeAdapter;
            case S3 -> s3TreeAdapter;
        };
    }
}
