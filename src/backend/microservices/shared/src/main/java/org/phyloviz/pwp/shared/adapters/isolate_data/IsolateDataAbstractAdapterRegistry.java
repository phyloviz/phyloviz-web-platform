package org.phyloviz.pwp.shared.adapters.isolate_data;

import jakarta.annotation.PostConstruct;
import org.phyloviz.pwp.shared.adapters.isolate_data.adapter.IsolateDataAdapter;
import org.phyloviz.pwp.shared.adapters.isolate_data.adapter.specific_data.IsolateDataAdapterSpecificData;
import org.springframework.context.ApplicationContext;

import java.util.EnumMap;
import java.util.Map;

public abstract class IsolateDataAbstractAdapterRegistry implements IsolateDataAdapterRegistry {

    private final ApplicationContext context;

    private final Map<IsolateDataAdapterId, IsolateDataAdapter> adapters = new EnumMap<>(IsolateDataAdapterId.class);

    private final Map<IsolateDataAdapterId, Class<? extends IsolateDataAdapter>> adapterClasses;
    private final Map<IsolateDataAdapterId, Class<? extends IsolateDataAdapterSpecificData>> adapterSpecificDataClasses;

    protected IsolateDataAbstractAdapterRegistry(
            ApplicationContext context,
            Map<IsolateDataAdapterId, Class<? extends IsolateDataAdapter>> adapterClasses,
            Map<IsolateDataAdapterId, Class<? extends IsolateDataAdapterSpecificData>> adapterSpecificDataClasses) {
        this.context = context;
        this.adapterClasses = adapterClasses;
        this.adapterSpecificDataClasses = adapterSpecificDataClasses;
    }

    @PostConstruct
    public void setAdapters() {
        for (IsolateDataAdapterId adapterId : IsolateDataAdapterId.values()) {
            Class<? extends IsolateDataAdapter> adapterClass = adapterClasses.get(adapterId);
            if (adapterClass != null) {
                IsolateDataAdapter adapter = context.getBean(adapterClass);
                this.adapters.put(adapterId, adapter);
            }
        }
    }

    public IsolateDataAdapter getIsolateDataAdapter(IsolateDataAdapterId adapterId) {
        IsolateDataAdapter adapter = adapters.get(adapterId);
        if (adapter == null) {
            throw new IllegalStateException("No IsolateDataAdapter found for id: " + adapterId);
        }
        return adapter;
    }

    @Override
    public Class<? extends IsolateDataAdapterSpecificData> getIsolateDataAdapterSpecificDataClass(IsolateDataAdapterId adapterId) {
        return adapterSpecificDataClasses.get(adapterId);
    }
}
