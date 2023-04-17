package org.phyloviz.pwp.shared.adapters.tree_view;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.tree_view.adapter.TreeViewAdapter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TreeViewAdapterRegistry {

    private final ApplicationContext context;

    private final Map<TreeViewAdapterId, TreeViewAdapter> adapters = new EnumMap<>(TreeViewAdapterId.class);

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
