package org.phyloviz.pwp.compute.repository.metadata.templates.workflowInstances;

import java.util.Optional;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflowInstances.documents.WorkflowInstance;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowInstanceRepository {

    Optional<WorkflowInstance> findById(String id);

    WorkflowInstance save(WorkflowInstance workflowInstance);
}
