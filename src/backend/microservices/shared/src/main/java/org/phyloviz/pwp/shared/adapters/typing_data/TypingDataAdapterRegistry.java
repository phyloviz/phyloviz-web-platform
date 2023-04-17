package org.phyloviz.pwp.shared.adapters.typing_data;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.typing_data.adapter.TypingDataAdapter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TypingDataAdapterRegistry {

    private final ApplicationContext context;

    private final Map<TypingDataAdapterId, TypingDataAdapter> adapters = new EnumMap<>(TypingDataAdapterId.class);

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
