package org.phyloviz.pwp.administration.repository.metadata.dataset.mongo;

import org.phyloviz.pwp.administration.repository.metadata.dataset.documents.Dataset;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatasetMongoRepository extends MongoRepository<Dataset, String> {
}
