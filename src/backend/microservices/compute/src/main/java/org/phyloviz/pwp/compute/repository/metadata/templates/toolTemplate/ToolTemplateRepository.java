package org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate;

import java.util.Optional;
import org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.ToolTemplate;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolTemplateRepository {

    Optional<ToolTemplate> findByName(String name);
}
