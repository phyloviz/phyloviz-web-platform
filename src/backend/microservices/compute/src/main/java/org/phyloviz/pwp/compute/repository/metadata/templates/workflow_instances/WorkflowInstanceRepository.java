package org.phyloviz.pwp.compute.repository.metadata.templates.workflow_instances;

import org.phyloviz.pwp.compute.repository.metadata.templates.workflow_instances.documents.WorkflowInstance;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkflowInstanceRepository {

    Optional<WorkflowInstance> findById(String id);

    List<WorkflowInstance> findAllByProjectId(String projectId);

    List<WorkflowInstance> findAllByProjectIdAndNotRunning(String projectId);

    List<WorkflowInstance> findAllByProjectIdAndRunning(String projectId);

    WorkflowInstance save(WorkflowInstance workflowInstance);
}
