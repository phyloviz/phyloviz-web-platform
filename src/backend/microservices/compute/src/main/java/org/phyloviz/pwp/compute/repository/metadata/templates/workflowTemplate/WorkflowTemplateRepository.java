package org.phyloviz.pwp.compute.repository.metadata.templates.workflowTemplate;

import java.util.Optional;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflowTemplate.documents.WorkflowTemplate;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowTemplateRepository {

    Optional<WorkflowTemplate> findByName(String name);
}
