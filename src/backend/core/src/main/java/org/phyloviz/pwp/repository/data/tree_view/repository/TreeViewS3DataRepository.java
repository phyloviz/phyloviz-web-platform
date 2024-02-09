package org.phyloviz.pwp.repository.data.tree_view.repository;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.repository.data.tree_view.repository.specific_data.TreeViewDataRepositorySpecificData;
import org.phyloviz.pwp.service.dtos.tree_view.GetTreeViewOutput;
import org.phyloviz.pwp.service.dtos.tree_view.Node;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TreeViewS3DataRepository implements TreeViewDataRepository {
    @Override
    public GetTreeViewOutput getTreeView(TreeViewDataRepositorySpecificData treeViewDataRepositorySpecificData) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void saveTreeView(TreeViewDataRepositorySpecificData treeViewDataRepositorySpecificData, List<Node> nodes) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void deleteTreeView(TreeViewDataRepositorySpecificData treeViewDataRepositorySpecificData) {
        throw new UnsupportedOperationException("Not supported yet");
    }
}
