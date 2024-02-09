package org.phyloviz.pwp.repository.data.registry.tree;

import org.phyloviz.pwp.repository.data.registry.AbstractDataRepositoryRegistry;
import org.phyloviz.pwp.repository.data.tree.TreeDataRepositoryId;
import org.phyloviz.pwp.repository.data.tree.repository.TreeDataRepository;
import org.phyloviz.pwp.repository.data.tree.repository.specific_data.TreeDataRepositorySpecificData;
import org.springframework.context.ApplicationContext;

import java.util.Map;

public class TreeDataRepositoryRegistryImpl
        extends AbstractDataRepositoryRegistry<TreeDataRepositoryId, TreeDataRepository, TreeDataRepositorySpecificData>
        implements TreeDataRepositoryRegistry {
    public TreeDataRepositoryRegistryImpl(
            ApplicationContext context,
            Map<TreeDataRepositoryId, Class<? extends TreeDataRepository>> repositoryClasses,
            Map<TreeDataRepositoryId, Class<? extends TreeDataRepositorySpecificData>> repositorySpecificDataClasses
    ) {
        super(context, TreeDataRepositoryId.class, repositoryClasses, repositorySpecificDataClasses);
    }
}