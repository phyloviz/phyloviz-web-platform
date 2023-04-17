package org.phyloviz.pwp.shared.service.adapters.treeView;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.adapterSpecificData.DistanceMatrixAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.adapterSpecificData.TreeViewAdapterId;
import org.phyloviz.pwp.shared.service.adapters.distanceMatrix.DistanceMatrixAdapter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TreeViewAdapterRegistry {

    private final ApplicationContext context;

    private final Map<TreeViewAdapterId, TreeViewAdapter> adapters = new HashMap<>();

    @PostConstruct
    public void setAdapters() {
        for (TreeViewAdapterId adapterId : TreeViewAdapterId.values()) {
            TreeViewAdapter adapter = context.getBean(adapterId.getAdapterClass());
            this.adapters.put(adapterId, adapter);
        }
    }

    public TreeViewAdapter getTreeViewAdapter(TreeViewAdapterId adapterId) {
        TreeViewAdapter adapter = adapters.get(adapterId);
        if (adapter == null) {
            throw new IllegalArgumentException("No TreeViewAdapter found for id: " + adapterId);
        }
        return adapter;
    }
}
