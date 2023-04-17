package org.phyloviz.pwp.compute.repository.metadata.templates.workflow_template;

import org.phyloviz.pwp.compute.repository.metadata.templates.workflow_template.documents.WorkflowTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkflowTemplateRepository {

    Optional<WorkflowTemplate> findByName(String name);
}
