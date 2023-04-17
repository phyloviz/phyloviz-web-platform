package org.phyloviz.pwp.shared.service.adapters.treeView;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.adapterSpecificData.TreeViewAdapterSpecificData;
import org.phyloviz.pwp.shared.service.dtos.treeView.GetTreeViewOutput;
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
