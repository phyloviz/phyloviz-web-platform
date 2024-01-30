package org.phyloviz.pwp.repository.metadata.templates.tool_template.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.repository.metadata.templates.tool_template.ToolTemplateRepository;
import org.phyloviz.pwp.repository.metadata.templates.tool_template.documents.ToolTemplate;
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
