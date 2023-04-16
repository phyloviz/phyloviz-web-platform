package org.phyloviz.pwp.shared;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
    @Value("${mongo-collections.typing-data-metadata-collection}")
    public String typingDataMetadataCollection;

    @Value("${mongo-collections.isolate-data-metadata-collection}")
    public String isolateDataMetadataCollection;

    @Value("${mongo-collections.distance-matrix-metadata-collection}")
    public String distanceMatrixMetadataCollection;

    @Value("${mongo-collections.tree-metadata-collection}")
    public String treeMetadataCollection;

    @Value("${mongo-collections.tree-view-metadata-collection}")
    public String treeViewMetadataCollection;
}
