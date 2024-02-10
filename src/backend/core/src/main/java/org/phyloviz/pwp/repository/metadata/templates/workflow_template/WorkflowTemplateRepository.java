package org.phyloviz.pwp.repository.metadata.templates.workflow_template;

import org.phyloviz.pwp.repository.metadata.templates.workflow_template.documents.WorkflowTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkflowTemplateRepository {

    /**
     * Finds a workflow template by its type.
     *
     * @param type the type of the workflow template
     * @return the workflow template
     */
    Optional<WorkflowTemplate> findByType(String type);

    /**
     * Finds all workflow templates.
     *
     * @return the list of workflow templates
     */
    List<WorkflowTemplate> findAll();
}
