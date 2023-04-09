package org.phyloviz.pwp.compute.repository.metadata.templates.workflowTemplate.mongo;

import org.phyloviz.pwp.compute.repository.metadata.templates.workflowTemplate.documents.WorkflowTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkflowTemplateMongoRepository extends MongoRepository<WorkflowTemplate, String> {
    Optional<WorkflowTemplate> findByName(String name);
}
