package org.phyloviz.pwp.shared.repository.data.registry.tree_view;

import org.phyloviz.pwp.shared.repository.data.registry.DataRepositoryRegistry;
import org.phyloviz.pwp.shared.repository.data.tree_view.TreeViewDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.TreeViewDataRepository;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.specific_data.TreeViewDataRepositorySpecificData;

public interface TreeViewDataRepositoryRegistry extends
        DataRepositoryRegistry<TreeViewDataRepositoryId, TreeViewDataRepository, TreeViewDataRepositorySpecificData> {
}
