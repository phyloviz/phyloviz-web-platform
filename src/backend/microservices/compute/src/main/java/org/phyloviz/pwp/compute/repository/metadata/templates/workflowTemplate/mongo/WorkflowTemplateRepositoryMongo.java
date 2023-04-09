package org.phyloviz.pwp.compute.repository.metadata.templates.workflowTemplate.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflowTemplate.WorkflowTemplateRepository;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflowTemplate.documents.WorkflowTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WorkflowTemplateRepositoryMongo implements WorkflowTemplateRepository {
    private final WorkflowTemplateMongoRepository repository;

    @Override
    public Optional<WorkflowTemplate> findByName(String name) {
        return repository.findByName(name);
    }
}
