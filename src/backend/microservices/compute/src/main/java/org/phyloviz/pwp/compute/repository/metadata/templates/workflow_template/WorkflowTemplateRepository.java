package org.phyloviz.pwp.compute.repository.metadata.templates.workflow_template;

import org.phyloviz.pwp.compute.repository.metadata.templates.workflow_template.documents.WorkflowTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkflowTemplateRepository {

    /**
     * Finds a workflow template by its name.
     *
     * @param name the name of the workflow template
     * @return the workflow template
     */
    Optional<WorkflowTemplate> findByName(String name);

    /**
     * Finds all workflow templates.
     *
     * @return the list of workflow templates
     */
    List<WorkflowTemplate> findAll();
}
