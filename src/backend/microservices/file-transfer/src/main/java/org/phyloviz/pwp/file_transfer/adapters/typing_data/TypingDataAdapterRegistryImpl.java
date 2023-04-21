package org.phyloviz.pwp.file_transfer.adapters.typing_data;

import org.phyloviz.pwp.shared.adapters.typing_data.TypingDataAbstractAdapterRegistry;
import org.phyloviz.pwp.shared.adapters.typing_data.TypingDataAdapterId;
import org.phyloviz.pwp.shared.adapters.typing_data.adapter.S3TypingDataAdapter;
import org.phyloviz.pwp.shared.adapters.typing_data.adapter.specific_data.TypingDataS3AdapterSpecificData;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TypingDataAdapterRegistryImpl extends TypingDataAbstractAdapterRegistry {
    public TypingDataAdapterRegistryImpl(ApplicationContext context) {
        super(context,
                Map.of(
                        TypingDataAdapterId.S3, S3TypingDataAdapter.class
                ),
                Map.of(
                        TypingDataAdapterId.S3, TypingDataS3AdapterSpecificData.class
                ));
    }
}