package org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.ToolTemplateRepository;
import org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.ToolTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ToolTemplateRepositoryMongo implements ToolTemplateRepository {
    private final ToolTemplateMongoRepository toolTemplateMongoRepository;

    @Override
    public Optional<ToolTemplate> findByName(String name) {
        return toolTemplateMongoRepository.findToolTemplateByGeneralName(name);
    }
}
