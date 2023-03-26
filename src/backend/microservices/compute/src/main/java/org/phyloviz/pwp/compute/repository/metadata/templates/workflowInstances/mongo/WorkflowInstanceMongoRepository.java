package org.phyloviz.pwp.compute.repository.metadata.templates.workflowInstances.mongo;

import java.util.Optional;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflowInstances.documents.WorkflowInstance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowInstanceMongoRepository extends MongoRepository<WorkflowInstance, String> {

}
