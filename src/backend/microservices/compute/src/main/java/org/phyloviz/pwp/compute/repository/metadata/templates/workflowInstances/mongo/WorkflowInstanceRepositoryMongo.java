package org.phyloviz.pwp.compute.repository.metadata.templates.workflowInstances.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflowInstances.WorkflowInstanceRepository;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflowInstances.documents.WorkflowInstance;
import org.springframework.stereotype.Repository;

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
    public WorkflowInstance save(WorkflowInstance workflowInstance) {
        return workflowInstanceMongoRepository.save(workflowInstance);
    }
}
