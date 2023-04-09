package org.phyloviz.pwp.compute.repository.metadata.templates.workflowInstances;

import org.phyloviz.pwp.compute.repository.metadata.templates.workflowInstances.documents.WorkflowInstance;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkflowInstanceRepository {

    Optional<WorkflowInstance> findById(String id);

    WorkflowInstance save(WorkflowInstance workflowInstance);
}
