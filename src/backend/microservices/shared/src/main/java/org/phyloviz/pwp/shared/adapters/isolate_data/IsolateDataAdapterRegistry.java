package org.phyloviz.pwp.shared.adapters.isolate_data;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.isolate_data.adapter.IsolateDataAdapter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class IsolateDataAdapterRegistry {

    private final ApplicationContext context;

    private final Map<IsolateDataAdapterId, IsolateDataAdapter> adapters = new EnumMap<>(IsolateDataAdapterId.class);

    @PostConstruct
    public void setAdapters() {
        for (IsolateDataAdapterId adapterId : IsolateDataAdapterId.values()) {
            IsolateDataAdapter adapter = context.getBean(adapterId.getAdapterClass());
            this.adapters.put(adapterId, adapter);
        }
    }

    public IsolateDataAdapter getIsolateDataAdapter(IsolateDataAdapterId adapterId) {
        IsolateDataAdapter adapter = adapters.get(adapterId);
        if (adapter == null) {
            throw new IllegalArgumentException("No IsolateDataAdapter found for id: " + adapterId);
        }
        return adapter;
    }
}
