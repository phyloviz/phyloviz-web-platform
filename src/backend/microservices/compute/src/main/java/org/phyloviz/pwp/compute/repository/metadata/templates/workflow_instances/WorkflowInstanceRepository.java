package org.phyloviz.pwp.compute.repository.metadata.templates.workflow_instances;

import org.phyloviz.pwp.compute.repository.metadata.templates.workflow_instances.documents.WorkflowInstance;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkflowInstanceRepository {

    Optional<WorkflowInstance> findById(String id);

    WorkflowInstance save(WorkflowInstance workflowInstance);
}
