package org.phyloviz.pwp.shared.adapters.distance_matrix;

import jakarta.annotation.PostConstruct;
import org.phyloviz.pwp.shared.adapters.distance_matrix.adapter.DistanceMatrixAdapter;
import org.phyloviz.pwp.shared.adapters.distance_matrix.adapter.specific_data.DistanceMatrixAdapterSpecificData;
import org.springframework.context.ApplicationContext;

import java.util.EnumMap;
import java.util.Map;

/**
 * Implementation of {@link DistanceMatrixAdapterRegistry} that uses Spring to
 * inject the adapters, only needing the classes passed as constructor parameters.
 */
public abstract class DistanceMatrixAbstractAdapterRegistry implements DistanceMatrixAdapterRegistry {

    private final ApplicationContext context;

    private final Map<DistanceMatrixAdapterId, DistanceMatrixAdapter> adapters = new EnumMap<>(DistanceMatrixAdapterId.class);

    private final Map<DistanceMatrixAdapterId, Class<? extends DistanceMatrixAdapter>> adapterClasses;
    private final Map<DistanceMatrixAdapterId, Class<? extends DistanceMatrixAdapterSpecificData>> adapterSpecificDataClasses;

    protected DistanceMatrixAbstractAdapterRegistry(
            ApplicationContext context,
            Map<DistanceMatrixAdapterId, Class<? extends DistanceMatrixAdapter>> adapterClasses,
            Map<DistanceMatrixAdapterId, Class<? extends DistanceMatrixAdapterSpecificData>> adapterSpecificDataClasses) {
        this.context = context;
        this.adapterClasses = adapterClasses;
        this.adapterSpecificDataClasses = adapterSpecificDataClasses;
    }

    @PostConstruct
    public void setAdapters() {
        for (DistanceMatrixAdapterId adapterId : DistanceMatrixAdapterId.values()) {
            Class<? extends DistanceMatrixAdapter> adapterClass = adapterClasses.get(adapterId);
            if (adapterClass != null) {
                DistanceMatrixAdapter adapter = context.getBean(adapterClass);
                this.adapters.put(adapterId, adapter);
            }
        }
    }

    @Override
    public DistanceMatrixAdapter getDistanceMatrixAdapter(DistanceMatrixAdapterId adapterId) {
        DistanceMatrixAdapter adapter = adapters.get(adapterId);
        if (adapter == null) {
            throw new IllegalStateException("No DistanceMatrixAdapter found for id: " + adapterId);
        }
        return adapter;
    }

    @Override
    public Class<? extends DistanceMatrixAdapterSpecificData> getDistanceMatrixAdapterSpecificDataClass(DistanceMatrixAdapterId adapterId) {
        return adapterSpecificDataClasses.get(adapterId);
    }
}
