package org.phyloviz.pwp.shared.service.adapters.distanceMatrix;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.adapterSpecificData.DistanceMatrixAdapterId;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DistanceMatrixAdapterFactory {

    private final DistanceMatrixAdapterRegistry distanceMatrixAdapterRegistry;

    public DistanceMatrixAdapter getDistanceMatrixAdapter(DistanceMatrixAdapterId adapterId) {
        return distanceMatrixAdapterRegistry.getDistanceMatrixAdapter(adapterId);
    }
}
