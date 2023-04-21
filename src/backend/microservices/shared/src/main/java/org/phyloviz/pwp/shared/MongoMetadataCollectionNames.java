package org.phyloviz.pwp.shared;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class MongoMetadataCollectionNames {

    @Value("${mongo-collections.projects-collection}")
    private String projectsCollection;

    @Value("${mongo-collections.datasets-collection}")
    private String datasetsCollection;

    @Value("${mongo-collections.typing-data-metadata-collection}")
    private String typingDataMetadataCollection;

    @Value("${mongo-collections.isolate-data-metadata-collection}")
    private String isolateDataMetadataCollection;

    @Value("${mongo-collections.distance-matrix-metadata-collection}")
    private String distanceMatrixMetadataCollection;

    @Value("${mongo-collections.tree-metadata-collection}")
    private String treeMetadataCollection;

    @Value("${mongo-collections.tree-view-metadata-collection}")
    private String treeViewMetadataCollection;
}
