package org.phyloviz.pwp.file_transfer.adapters.tree;

import org.phyloviz.pwp.shared.adapters.tree.TreeAbstractAdapterRegistry;
import org.phyloviz.pwp.shared.adapters.tree.TreeAdapterId;
import org.phyloviz.pwp.shared.adapters.tree.adapter.S3TreeAdapter;
import org.phyloviz.pwp.shared.adapters.tree.adapter.specific_data.TreeS3AdapterSpecificData;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TreeAdapterRegistryImpl extends TreeAbstractAdapterRegistry {
    public TreeAdapterRegistryImpl(ApplicationContext context) {
        super(context,
                Map.of(
                        TreeAdapterId.S3, S3TreeAdapter.class
                ),
                Map.of(
                        TreeAdapterId.S3, TreeS3AdapterSpecificData.class
                ));
    }
}