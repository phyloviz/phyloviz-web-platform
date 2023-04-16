package org.phyloviz.pwp.shared.service.adapters.distanceMatrix;

import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.adapterSpecificData.DistanceMatrixAdapterSpecificData;

public interface DistanceMatrixAdapter {

    String getDistanceMatrix(DistanceMatrixAdapterSpecificData distanceMatrixAdapterSpecificData);

    boolean isFileAdapter();

    void deleteDistanceMatrix(DistanceMatrixAdapterSpecificData distanceMatrixAdapterSpecificData);
}
