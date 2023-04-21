package org.phyloviz.pwp.compute.repository.metadata.templates.tool_template;

import org.phyloviz.pwp.compute.repository.metadata.templates.tool_template.documents.ToolTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ToolTemplateRepository {

    Optional<ToolTemplate> findByName(String name);
}
