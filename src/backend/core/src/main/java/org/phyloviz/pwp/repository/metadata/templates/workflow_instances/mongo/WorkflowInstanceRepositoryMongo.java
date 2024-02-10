package org.phyloviz.pwp.repository.metadata.templates.workflow_instances.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.repository.metadata.templates.workflow_instances.WorkflowInstanceRepository;
import org.phyloviz.pwp.repository.metadata.templates.workflow_instances.documents.WorkflowInstance;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WorkflowInstanceRepositoryMongo implements WorkflowInstanceRepository {

    private final WorkflowInstanceMongoRepository workflowInstanceMongoRepository;

    @Override
    public Optional<WorkflowInstance> findById(String id) {
        return workflowInstanceMongoRepository.findById(id);
    }

    @Override
    public List<WorkflowInstance> findAllByProjectId(String projectId) {
        return workflowInstanceMongoRepository.findAllByProjectId(projectId);
    }

    @Override
    public List<WorkflowInstance> findAllByProjectIdAndNotRunning(String projectId) {
        return workflowInstanceMongoRepository.findAllByProjectIdAndNotRunning(projectId);
    }

    @Override
    public List<WorkflowInstance> findAllByProjectIdAndRunning(String projectId) {
        return workflowInstanceMongoRepository.findAllByProjectIdAndRunning(projectId);
    }

    @Override
    public WorkflowInstance save(WorkflowInstance workflowInstance) {
        return workflowInstanceMongoRepository.save(workflowInstance);
    }
}
