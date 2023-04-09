package org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate;

import org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.ToolTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ToolTemplateRepository {

    Optional<ToolTemplate> findByName(String name);
}
