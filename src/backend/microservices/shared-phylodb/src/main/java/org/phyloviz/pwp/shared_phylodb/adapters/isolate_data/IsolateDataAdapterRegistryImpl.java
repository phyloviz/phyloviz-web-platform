package org.phyloviz.pwp.shared_phylodb.adapters.isolate_data;

import org.phyloviz.pwp.shared.adapters.isolate_data.IsolateDataAbstractAdapterRegistry;
import org.phyloviz.pwp.shared.adapters.isolate_data.IsolateDataAdapterId;
import org.phyloviz.pwp.shared.adapters.isolate_data.adapter.S3IsolateDataAdapter;
import org.phyloviz.pwp.shared.adapters.isolate_data.adapter.specific_data.IsolateDataS3AdapterSpecificData;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class IsolateDataAdapterRegistryImpl extends IsolateDataAbstractAdapterRegistry {
    public IsolateDataAdapterRegistryImpl(ApplicationContext context) {
        super(context,
                Map.of(
                        IsolateDataAdapterId.S3, S3IsolateDataAdapter.class
                ),
                Map.of(
                        IsolateDataAdapterId.S3, IsolateDataS3AdapterSpecificData.class
                ));
    }
}