package org.phyloviz.pwp.shared.adapters.distance_matrix;

import org.phyloviz.pwp.shared.adapters.distance_matrix.adapter.DistanceMatrixAdapter;
import org.phyloviz.pwp.shared.adapters.distance_matrix.adapter.specific_data.DistanceMatrixAdapterSpecificData;

public interface DistanceMatrixAdapterRegistry {

    /**
     * Returns the DistanceMatrixAdapter for the given adapterId.
     *
     * @param adapterId the id of the adapter
     * @return the DistanceMatrixAdapter
     */
    DistanceMatrixAdapter getDistanceMatrixAdapter(DistanceMatrixAdapterId adapterId);

    /**
     * Returns the DistanceMatrixAdapterSpecificData class for the given adapterId.
     *
     * @param adapterId the id of the adapter
     * @return the DistanceMatrixAdapterSpecificData class
     */
    Class<? extends DistanceMatrixAdapterSpecificData> getDistanceMatrixAdapterSpecificDataClass(DistanceMatrixAdapterId adapterId);
}
