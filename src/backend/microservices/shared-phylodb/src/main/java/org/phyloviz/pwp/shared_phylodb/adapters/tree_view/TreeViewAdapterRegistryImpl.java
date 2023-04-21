package org.phyloviz.pwp.shared_phylodb.adapters.tree_view;

import org.phyloviz.pwp.shared.adapters.tree_view.TreeViewAbstractAdapterRegistry;
import org.phyloviz.pwp.shared.adapters.tree_view.TreeViewAdapterId;
import org.phyloviz.pwp.shared.adapters.tree_view.adapter.S3TreeViewAdapter;
import org.phyloviz.pwp.shared.adapters.tree_view.adapter.specific_data.TreeViewS3AdapterSpecificData;
import org.phyloviz.pwp.shared_phylodb.adapters.tree_view.phylodb.PhyloDBTreeViewAdapter;
import org.phyloviz.pwp.shared_phylodb.adapters.tree_view.phylodb.TreeViewPhyloDBAdapterSpecificData;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TreeViewAdapterRegistryImpl extends TreeViewAbstractAdapterRegistry {
    public TreeViewAdapterRegistryImpl(ApplicationContext context) {
        super(context,
                Map.of(
                        TreeViewAdapterId.S3, S3TreeViewAdapter.class,
                        TreeViewAdapterId.PHYLODB, PhyloDBTreeViewAdapter.class
                ),
                Map.of(
                        TreeViewAdapterId.S3, TreeViewS3AdapterSpecificData.class,
                        TreeViewAdapterId.PHYLODB, TreeViewPhyloDBAdapterSpecificData.class
                ));
    }
}