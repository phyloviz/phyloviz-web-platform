package org.phyloviz.pwp.shared.adapters.distance_matrix;

import org.phyloviz.pwp.shared.adapters.distance_matrix.adapter.DistanceMatrixAdapter;
import org.phyloviz.pwp.shared.adapters.distance_matrix.adapter.specific_data.DistanceMatrixAdapterSpecificData;

public interface DistanceMatrixAdapterRegistry {

    DistanceMatrixAdapter getDistanceMatrixAdapter(DistanceMatrixAdapterId adapterId);

    Class<? extends DistanceMatrixAdapterSpecificData> getDistanceMatrixAdapterSpecificDataClass(DistanceMatrixAdapterId adapterId);
}
