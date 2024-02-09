package org.phyloviz.pwp.repository.metadata.dataset.mongo;

import org.phyloviz.pwp.repository.metadata.dataset.documents.Dataset;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DatasetMongoRepository extends MongoRepository<Dataset, String> {

    /**
     * Find all datasets by project id.
     *
     * @param projectId the id of the project
     * @return a list of datasets
     */
    List<Dataset> findAllByProjectId(String projectId);

    Optional<Dataset> findByProjectIdAndId(String projectId, String datasetId);

    boolean existsByProjectIdAndId(String projectId, String datasetId);

    boolean existsByProjectIdAndTypingDataId(String projectId, String typingDataId);

    boolean existsByProjectIdAndIsolateDataId(String projectId, String isolateDataId);
}
