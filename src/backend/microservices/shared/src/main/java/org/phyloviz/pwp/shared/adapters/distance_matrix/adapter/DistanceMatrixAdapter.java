package org.phyloviz.pwp.shared.adapters.distance_matrix.adapter;

import org.phyloviz.pwp.shared.adapters.distance_matrix.adapter.specific_data.DistanceMatrixAdapterSpecificData;

public interface DistanceMatrixAdapter {

    String getDistanceMatrix(DistanceMatrixAdapterSpecificData distanceMatrixAdapterSpecificData);

    boolean isFileAdapter();

    void deleteDistanceMatrix(DistanceMatrixAdapterSpecificData distanceMatrixAdapterSpecificData);
}
