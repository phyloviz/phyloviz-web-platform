package org.phyloviz.pwp.shared_phylodb.adapters.distance_matrix;

import org.phyloviz.pwp.shared.adapters.distance_matrix.DistanceMatrixAbstractAdapterRegistry;
import org.phyloviz.pwp.shared.adapters.distance_matrix.DistanceMatrixAdapterId;
import org.phyloviz.pwp.shared.adapters.distance_matrix.adapter.S3DistanceMatrixAdapter;
import org.phyloviz.pwp.shared.adapters.distance_matrix.adapter.specific_data.DistanceMatrixS3AdapterSpecificData;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DistanceMatrixAdapterRegistryImpl extends DistanceMatrixAbstractAdapterRegistry {
    public DistanceMatrixAdapterRegistryImpl(ApplicationContext context) {
        super(context,
                Map.of(
                        DistanceMatrixAdapterId.S3, S3DistanceMatrixAdapter.class
                ),
                Map.of(
                        DistanceMatrixAdapterId.S3, DistanceMatrixS3AdapterSpecificData.class
                ));
    }
}