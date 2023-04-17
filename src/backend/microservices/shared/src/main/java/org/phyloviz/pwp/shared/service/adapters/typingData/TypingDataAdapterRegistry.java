package org.phyloviz.pwp.shared.service.adapters.typingData;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.adapterSpecificData.TreeViewAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.adapterSpecificData.TypingDataAdapterId;
import org.phyloviz.pwp.shared.service.adapters.treeView.TreeViewAdapter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TypingDataAdapterRegistry {

    private final ApplicationContext context;

    private final Map<TypingDataAdapterId, TypingDataAdapter> adapters = new HashMap<>();

    @PostConstruct
    public void setAdapters() {
        for (TypingDataAdapterId adapterId : TypingDataAdapterId.values()) {
            TypingDataAdapter adapter = context.getBean(adapterId.getAdapterClass());
            this.adapters.put(adapterId, adapter);
        }
    }

    public TypingDataAdapter getTypingDataAdapter(TypingDataAdapterId adapterId) {
        TypingDataAdapter adapter = adapters.get(adapterId);
        if (adapter == null) {
            throw new IllegalArgumentException("No TypingDataAdapter found for id: " + adapterId);
        }
        return adapter;
    }
}
