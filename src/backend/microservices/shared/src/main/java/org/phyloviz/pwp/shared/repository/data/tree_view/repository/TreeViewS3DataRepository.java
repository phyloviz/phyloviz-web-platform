package org.phyloviz.pwp.shared.repository.data.tree_view.repository;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.specific_data.TreeViewDataRepositorySpecificData;
import org.phyloviz.pwp.shared.service.dtos.tree_view.GetTreeViewOutput;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TreeViewS3DataRepository implements TreeViewDataRepository {
    @Override
    public GetTreeViewOutput getTreeView(TreeViewDataRepositorySpecificData treeViewDataRepositorySpecificData) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void deleteTreeView(TreeViewDataRepositorySpecificData treeViewDataRepositorySpecificData) {
        throw new UnsupportedOperationException("Not supported yet");
    }
}
