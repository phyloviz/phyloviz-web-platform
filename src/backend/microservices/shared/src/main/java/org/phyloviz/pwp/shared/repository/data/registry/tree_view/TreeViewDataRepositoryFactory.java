package org.phyloviz.pwp.shared.repository.data.registry.tree_view;

import org.phyloviz.pwp.shared.repository.data.registry.DataRepositoryFactory;
import org.phyloviz.pwp.shared.repository.data.tree_view.TreeViewDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.TreeViewDataRepository;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.specific_data.TreeViewDataRepositorySpecificData;
import org.springframework.stereotype.Component;

@Component
public class TreeViewDataRepositoryFactory extends
        DataRepositoryFactory<TreeViewDataRepositoryId, TreeViewDataRepository, TreeViewDataRepositorySpecificData> {
    public TreeViewDataRepositoryFactory(
            @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") TreeViewDataRepositoryRegistry dataRepositoryRegistry
    ) {
        super(dataRepositoryRegistry);
    }
}
