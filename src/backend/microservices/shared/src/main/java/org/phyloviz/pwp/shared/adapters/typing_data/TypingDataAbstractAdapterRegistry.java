package org.phyloviz.pwp.shared.adapters.typing_data;

import jakarta.annotation.PostConstruct;
import org.phyloviz.pwp.shared.adapters.typing_data.adapter.TypingDataAdapter;
import org.phyloviz.pwp.shared.adapters.typing_data.adapter.specific_data.TypingDataAdapterSpecificData;
import org.springframework.context.ApplicationContext;

import java.util.EnumMap;
import java.util.Map;

/**
 * Implementation of {@link TypingDataAdapterRegistry} that uses Spring to
 * inject the adapters, only needing the classes passed as constructor parameters.
 */
public abstract class TypingDataAbstractAdapterRegistry implements TypingDataAdapterRegistry {

    private final ApplicationContext context;

    private final Map<TypingDataAdapterId, TypingDataAdapter> adapters = new EnumMap<>(TypingDataAdapterId.class);

    private final Map<TypingDataAdapterId, Class<? extends TypingDataAdapter>> adapterClasses;
    private final Map<TypingDataAdapterId, Class<? extends TypingDataAdapterSpecificData>> adapterSpecificDataClasses;

    protected TypingDataAbstractAdapterRegistry(
            ApplicationContext context,
            Map<TypingDataAdapterId, Class<? extends TypingDataAdapter>> adapterClasses,
            Map<TypingDataAdapterId, Class<? extends TypingDataAdapterSpecificData>> adapterSpecificDataClasses) {
        this.context = context;
        this.adapterClasses = adapterClasses;
        this.adapterSpecificDataClasses = adapterSpecificDataClasses;
    }

    @PostConstruct
    public void setAdapters() {
        for (TypingDataAdapterId adapterId : TypingDataAdapterId.values()) {
            Class<? extends TypingDataAdapter> adapterClass = adapterClasses.get(adapterId);
            if (adapterClass != null) {
                TypingDataAdapter adapter = context.getBean(adapterClass);
                this.adapters.put(adapterId, adapter);
            }
        }
    }

    @Override
    public TypingDataAdapter getTypingDataAdapter(TypingDataAdapterId adapterId) {
        TypingDataAdapter adapter = adapters.get(adapterId);
        if (adapter == null) {
            throw new IllegalStateException("No TypingDataAdapter found for id: " + adapterId);
        }
        return adapter;
    }

    @Override
    public Class<? extends TypingDataAdapterSpecificData> getTypingDataAdapterSpecificDataClass(TypingDataAdapterId adapterId) {
        return adapterSpecificDataClasses.get(adapterId);
    }
}
