package org.phyloviz.pwp.compute.repository.metadata.templates.workflowTemplate;

import org.phyloviz.pwp.compute.repository.metadata.templates.workflowTemplate.documents.WorkflowTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkflowTemplateRepository {

    Optional<WorkflowTemplate> findByName(String name);
}
