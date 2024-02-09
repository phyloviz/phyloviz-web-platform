package org.phyloviz.pwp.repository.metadata.templates.tool_template.mongo;

import org.phyloviz.pwp.repository.metadata.templates.tool_template.documents.ToolTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ToolTemplateMongoRepository extends MongoRepository<ToolTemplate, String> {

    Optional<ToolTemplate> findToolTemplateByGeneralName(String name);
}
