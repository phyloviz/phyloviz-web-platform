package org.phyloviz.pwp.shared.repository.data.registry.tree;

import org.phyloviz.pwp.shared.repository.data.registry.DataRepositoryFactory;
import org.phyloviz.pwp.shared.repository.data.tree.TreeDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.tree.repository.TreeDataRepository;
import org.phyloviz.pwp.shared.repository.data.tree.repository.specific_data.TreeDataRepositorySpecificData;
import org.springframework.stereotype.Component;

@Component
public class TreeDataRepositoryFactory extends
        DataRepositoryFactory<TreeDataRepositoryId, TreeDataRepository, TreeDataRepositorySpecificData> {
    public TreeDataRepositoryFactory(
            @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") TreeDataRepositoryRegistry dataRepositoryRegistry
    ) {
        super(dataRepositoryRegistry);
    }
}
