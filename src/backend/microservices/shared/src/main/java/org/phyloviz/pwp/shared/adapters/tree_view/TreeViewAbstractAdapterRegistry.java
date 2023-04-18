package org.phyloviz.pwp.shared.adapters.tree_view;

import jakarta.annotation.PostConstruct;
import org.phyloviz.pwp.shared.adapters.tree_view.adapter.TreeViewAdapter;
import org.phyloviz.pwp.shared.adapters.tree_view.adapter.specific_data.TreeViewAdapterSpecificData;
import org.springframework.context.ApplicationContext;

import java.util.EnumMap;
import java.util.Map;

public abstract class TreeViewAbstractAdapterRegistry implements TreeViewAdapterRegistry {

    private final ApplicationContext context;

    private final Map<TreeViewAdapterId, TreeViewAdapter> adapters = new EnumMap<>(TreeViewAdapterId.class);

    private final Map<TreeViewAdapterId, Class<? extends TreeViewAdapter>> adapterClasses;
    private final Map<TreeViewAdapterId, Class<? extends TreeViewAdapterSpecificData>> adapterSpecificDataClasses;

    protected TreeViewAbstractAdapterRegistry(
            ApplicationContext context,
            Map<TreeViewAdapterId, Class<? extends TreeViewAdapter>> adapterClasses,
            Map<TreeViewAdapterId, Class<? extends TreeViewAdapterSpecificData>> adapterSpecificDataClasses) {
        this.context = context;
        this.adapterClasses = adapterClasses;
        this.adapterSpecificDataClasses = adapterSpecificDataClasses;
    }

    @PostConstruct
    public void setAdapters() {
        for (TreeViewAdapterId adapterId : TreeViewAdapterId.values()) {
            Class<? extends TreeViewAdapter> adapterClass = adapterClasses.get(adapterId);
            if (adapterClass != null) {
                TreeViewAdapter adapter = context.getBean(adapterClass);
                this.adapters.put(adapterId, adapter);
            }
        }
    }

    public TreeViewAdapter getTreeViewAdapter(TreeViewAdapterId adapterId) {
        TreeViewAdapter adapter = adapters.get(adapterId);
        if (adapter == null) {
            throw new IllegalStateException("No TreeViewAdapter found for id: " + adapterId);
        }
        return adapter;
    }

    @Override
    public Class<? extends TreeViewAdapterSpecificData> getTreeViewAdapterSpecificDataClass(TreeViewAdapterId adapterId) {
        return adapterSpecificDataClasses.get(adapterId);
    }
}
