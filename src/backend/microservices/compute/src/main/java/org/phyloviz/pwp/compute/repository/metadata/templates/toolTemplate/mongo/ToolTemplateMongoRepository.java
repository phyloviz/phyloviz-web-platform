package org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.mongo;

import java.util.Optional;
import org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.ToolTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolTemplateMongoRepository extends MongoRepository<ToolTemplate, String> {

    Optional<ToolTemplate> findToolTemplateByGeneralName(String name);
}
