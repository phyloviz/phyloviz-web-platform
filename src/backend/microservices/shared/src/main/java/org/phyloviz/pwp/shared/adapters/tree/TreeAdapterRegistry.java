package org.phyloviz.pwp.shared.adapters.tree;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.tree.adapter.TreeAdapter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TreeAdapterRegistry {

    private final ApplicationContext context;

    private final Map<TreeAdapterId, TreeAdapter> adapters = new EnumMap<>(TreeAdapterId.class);

    @PostConstruct
    public void setAdapters() {
        for (TreeAdapterId adapterId : TreeAdapterId.values()) {
            TreeAdapter adapter = context.getBean(adapterId.getAdapterClass());
            this.adapters.put(adapterId, adapter);
        }
    }

    public TreeAdapter getTreeAdapter(TreeAdapterId adapterId) {
        TreeAdapter adapter = adapters.get(adapterId);
        if (adapter == null) {
            throw new IllegalArgumentException("No TreeAdapter found for id: " + adapterId);
        }
        return adapter;
    }
}
