package org.phyloviz.pwp.shared.adapters.distanceMatrix;

import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.adapterSpecificData.DistanceMatrixAdapterSpecificData;
import org.springframework.stereotype.Component;

@Component
public class S3DistanceMatrixAdapter implements DistanceMatrixAdapter {
    @Override
    public String getDistanceMatrix(DistanceMatrixAdapterSpecificData distanceMatrixAdapterSpecificData) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean isFileAdapter() {
        return true;
    }
}
