package org.phyloviz.pwp.shared.adapters.tree_view.adapter;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.tree_view.adapter.specific_data.TreeViewAdapterSpecificData;
import org.phyloviz.pwp.shared.service.dtos.tree_view.GetTreeViewOutput;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class S3TreeViewAdapter implements TreeViewAdapter {
    @Override
    public GetTreeViewOutput getTreeView(TreeViewAdapterSpecificData treeViewAdapterSpecificData) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void deleteTreeView(TreeViewAdapterSpecificData treeViewAdapterSpecificData) {
        throw new UnsupportedOperationException("Not supported yet");
    }
}
