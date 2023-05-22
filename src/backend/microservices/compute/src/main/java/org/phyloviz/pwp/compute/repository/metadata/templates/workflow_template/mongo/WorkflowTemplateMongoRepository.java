package org.phyloviz.pwp.compute.repository.metadata.templates.workflow_template.mongo;

import org.phyloviz.pwp.compute.repository.metadata.templates.workflow_template.documents.WorkflowTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkflowTemplateMongoRepository extends MongoRepository<WorkflowTemplate, String> {

    /**
     * Finds a workflow template by its name.
     *
     * @param name the name of the workflow template
     * @return the workflow template
     */
    Optional<WorkflowTemplate> findByName(String name);
}
