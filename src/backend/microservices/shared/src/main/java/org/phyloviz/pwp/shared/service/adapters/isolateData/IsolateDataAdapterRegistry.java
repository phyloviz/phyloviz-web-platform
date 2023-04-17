package org.phyloviz.pwp.shared.service.adapters.isolateData;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.adapterSpecificData.IsolateDataAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData.TreeAdapterId;
import org.phyloviz.pwp.shared.service.adapters.tree.TreeAdapter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class IsolateDataAdapterRegistry {

    private final ApplicationContext context;

    private final Map<IsolateDataAdapterId, IsolateDataAdapter> adapters = new HashMap<>();

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
