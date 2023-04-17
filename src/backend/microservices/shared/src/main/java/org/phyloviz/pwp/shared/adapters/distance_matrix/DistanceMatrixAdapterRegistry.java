package org.phyloviz.pwp.shared.adapters.distance_matrix;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.distance_matrix.adapter.DistanceMatrixAdapter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DistanceMatrixAdapterRegistry {

    private final ApplicationContext context;

    private final Map<DistanceMatrixAdapterId, DistanceMatrixAdapter> adapters = new HashMap<>();

    @PostConstruct
    public void setAdapters() {
        for (DistanceMatrixAdapterId adapterId : DistanceMatrixAdapterId.values()) {
            DistanceMatrixAdapter adapter = context.getBean(adapterId.getAdapterClass());
            this.adapters.put(adapterId, adapter);
        }
    }

    public DistanceMatrixAdapter getDistanceMatrixAdapter(DistanceMatrixAdapterId adapterId) {
        DistanceMatrixAdapter adapter = adapters.get(adapterId);
        if (adapter == null) {
            throw new IllegalArgumentException("No DistanceMatrixAdapter found for id: " + adapterId);
        }
        return adapter;
    }
}
