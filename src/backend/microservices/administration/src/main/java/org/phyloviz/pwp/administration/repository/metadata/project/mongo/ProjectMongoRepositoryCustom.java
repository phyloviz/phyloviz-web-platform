package org.phyloviz.pwp.administration.repository.metadata.project.mongo;

import org.phyloviz.pwp.shared.repository.metadata.Metadata;
import org.phyloviz.pwp.administration.repository.metadata.project.documents.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMongoRepositoryCustom {
    /**
     * Find all the metadata representations of a resource.
     *
     * @param resource the resource to find the representations of
     * @return a list of metadata representations
     */
    List<Metadata> findAllResourceRepresentations(Resource resource);
}
