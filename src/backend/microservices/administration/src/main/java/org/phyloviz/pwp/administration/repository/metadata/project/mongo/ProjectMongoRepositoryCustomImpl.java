package org.phyloviz.pwp.administration.repository.metadata.project.mongo;

import org.phyloviz.pwp.shared.repository.metadata.Metadata;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectMongoRepositoryCustomImpl {
    private final MongoTemplate mongoTemplate;

    public ProjectMongoRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public <T extends Metadata> List<T> findAllResourceRepresentations(String resourceId, Class<T> resourceClass) {
        Query query = new Query(Criteria.where("resourceId").is(resourceId));

        return mongoTemplate.find(query, resourceClass, resourceClass.getAnnotation(Document.class).collection());
    }
}
