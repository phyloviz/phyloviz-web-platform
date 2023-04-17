package org.phyloviz.pwp.shared.service.adapters.tree;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData.TreeAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.adapterSpecificData.TypingDataAdapterId;
import org.phyloviz.pwp.shared.service.adapters.typingData.TypingDataAdapter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TreeAdapterRegistry {

    private final ApplicationContext context;

    private final Map<TreeAdapterId, TreeAdapter> adapters = new HashMap<>();

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
