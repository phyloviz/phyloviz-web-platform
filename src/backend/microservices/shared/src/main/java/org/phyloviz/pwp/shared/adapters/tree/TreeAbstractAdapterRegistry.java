package org.phyloviz.pwp.shared.adapters.tree;

import jakarta.annotation.PostConstruct;
import org.phyloviz.pwp.shared.adapters.tree.adapter.TreeAdapter;
import org.phyloviz.pwp.shared.adapters.tree.adapter.specific_data.TreeAdapterSpecificData;
import org.springframework.context.ApplicationContext;

import java.util.EnumMap;
import java.util.Map;

public abstract class TreeAbstractAdapterRegistry implements TreeAdapterRegistry {

    private final ApplicationContext context;

    private final Map<TreeAdapterId, TreeAdapter> adapters = new EnumMap<>(TreeAdapterId.class);

    private final Map<TreeAdapterId, Class<? extends TreeAdapter>> adapterClasses;
    private final Map<TreeAdapterId, Class<? extends TreeAdapterSpecificData>> adapterSpecificDataClasses;

    protected TreeAbstractAdapterRegistry(
            ApplicationContext context,
            Map<TreeAdapterId, Class<? extends TreeAdapter>> adapterClasses,
            Map<TreeAdapterId, Class<? extends TreeAdapterSpecificData>> adapterSpecificDataClasses) {
        this.context = context;
        this.adapterClasses = adapterClasses;
        this.adapterSpecificDataClasses = adapterSpecificDataClasses;
    }

    @PostConstruct
    public void setAdapters() {
        for (TreeAdapterId adapterId : TreeAdapterId.values()) {
            Class<? extends TreeAdapter> adapterClass = adapterClasses.get(adapterId);
            if (adapterClass != null) {
                TreeAdapter adapter = context.getBean(adapterClass);
                this.adapters.put(adapterId, adapter);
            }
        }
    }

    public TreeAdapter getTreeAdapter(TreeAdapterId adapterId) {
        TreeAdapter adapter = adapters.get(adapterId);
        if (adapter == null) {
            throw new IllegalStateException("No TreeAdapter found for id: " + adapterId);
        }
        return adapter;
    }

    @Override
    public Class<? extends TreeAdapterSpecificData> getTreeAdapterSpecificDataClass(TreeAdapterId adapterId) {
        return adapterSpecificDataClasses.get(adapterId);
    }
}
