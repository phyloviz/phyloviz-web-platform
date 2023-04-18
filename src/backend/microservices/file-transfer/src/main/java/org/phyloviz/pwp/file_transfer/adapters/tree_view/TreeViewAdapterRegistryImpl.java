package org.phyloviz.pwp.file_transfer.adapters.tree_view;

import org.phyloviz.pwp.shared.adapters.tree_view.TreeViewAbstractAdapterRegistry;
import org.phyloviz.pwp.shared.adapters.tree_view.TreeViewAdapterId;
import org.phyloviz.pwp.shared.adapters.tree_view.adapter.S3TreeViewAdapter;
import org.phyloviz.pwp.shared.adapters.tree_view.adapter.specific_data.TreeViewS3AdapterSpecificData;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TreeViewAdapterRegistryImpl extends TreeViewAbstractAdapterRegistry {
    public TreeViewAdapterRegistryImpl(ApplicationContext context) {
        super(context,
                Map.of(
                        TreeViewAdapterId.S3, S3TreeViewAdapter.class
                ),
                Map.of(
                        TreeViewAdapterId.S3, TreeViewS3AdapterSpecificData.class
                ));
    }
}