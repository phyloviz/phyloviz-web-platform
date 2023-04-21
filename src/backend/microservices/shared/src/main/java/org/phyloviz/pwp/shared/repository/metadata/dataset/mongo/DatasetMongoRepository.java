package org.phyloviz.pwp.shared.repository.metadata.dataset.mongo;

import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatasetMongoRepository extends MongoRepository<Dataset, String> {

    /**
     * Find all datasets by project id.
     *
     * @param projectId the id of the project
     * @return a list of datasets
     */
    List<Dataset> findAllByProjectId(String projectId);
}
