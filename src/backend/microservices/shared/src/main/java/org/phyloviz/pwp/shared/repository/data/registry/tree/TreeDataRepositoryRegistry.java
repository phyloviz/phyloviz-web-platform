package org.phyloviz.pwp.shared.repository.data.registry.tree;

import org.phyloviz.pwp.shared.repository.data.registry.DataRepositoryRegistry;
import org.phyloviz.pwp.shared.repository.data.tree.TreeDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.tree.repository.TreeDataRepository;
import org.phyloviz.pwp.shared.repository.data.tree.repository.specific_data.TreeDataRepositorySpecificData;

public interface TreeDataRepositoryRegistry extends
        DataRepositoryRegistry<TreeDataRepositoryId, TreeDataRepository, TreeDataRepositorySpecificData> {
}
