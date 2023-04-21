package org.phyloviz.pwp.shared.adapters.distance_matrix;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.distance_matrix.adapter.DistanceMatrixAdapter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DistanceMatrixAdapterFactory {

    private final DistanceMatrixAdapterRegistry distanceMatrixAdapterRegistry;

    /**
     * Returns the DistanceMatrixAdapter for the given adapterId.
     *
     * @param adapterId the id of the adapter
     * @return the DistanceMatrixAdapter
     */
    public DistanceMatrixAdapter getDistanceMatrixAdapter(DistanceMatrixAdapterId adapterId) {
        return distanceMatrixAdapterRegistry.getDistanceMatrixAdapter(adapterId);
    }
}
