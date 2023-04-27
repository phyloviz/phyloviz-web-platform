package org.phyloviz.pwp.shared.repository.data.registry.tree_view;

import org.phyloviz.pwp.shared.repository.data.registry.AbstractDataRepositoryRegistry;
import org.phyloviz.pwp.shared.repository.data.tree_view.TreeViewDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.TreeViewDataRepository;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.specific_data.TreeViewDataRepositorySpecificData;
import org.springframework.context.ApplicationContext;

import java.util.Map;

public class TreeViewDataRepositoryRegistryImpl
        extends AbstractDataRepositoryRegistry<TreeViewDataRepositoryId, TreeViewDataRepository, TreeViewDataRepositorySpecificData>
        implements TreeViewDataRepositoryRegistry {
    public TreeViewDataRepositoryRegistryImpl(
            ApplicationContext context,
            Map<TreeViewDataRepositoryId, Class<? extends TreeViewDataRepository>> repositoryClasses,
            Map<TreeViewDataRepositoryId, Class<? extends TreeViewDataRepositorySpecificData>> repositorySpecificDataClasses
    ) {
        super(context, TreeViewDataRepositoryId.class, repositoryClasses, repositorySpecificDataClasses);
    }
}