package org.phyloviz.pwp.repository.metadata.templates.workflow_template.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.repository.metadata.templates.workflow_template.WorkflowTemplateRepository;
import org.phyloviz.pwp.repository.metadata.templates.workflow_template.documents.WorkflowTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WorkflowTemplateRepositoryMongo implements WorkflowTemplateRepository {
    private final WorkflowTemplateMongoRepository repository;

    @Override
    public Optional<WorkflowTemplate> findByType(String type) {
        return repository.findByType(type);
    }

    @Override
    public List<WorkflowTemplate> findAll() {
        return repository.findAll();
    }
}
