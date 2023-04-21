package org.phyloviz.pwp.shared.adapters.distance_matrix.adapter;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.distance_matrix.adapter.specific_data.DistanceMatrixAdapterSpecificData;
import org.phyloviz.pwp.shared.adapters.distance_matrix.adapter.specific_data.DistanceMatrixS3AdapterSpecificData;
import org.phyloviz.pwp.shared.repository.data.S3FileRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class S3DistanceMatrixAdapter implements DistanceMatrixAdapter {

    private final S3FileRepository s3FileRepository;

    @Override
    public String getDistanceMatrix(DistanceMatrixAdapterSpecificData distanceMatrixAdapterSpecificData) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean isFileAdapter() {
        return true;
    }

    @Override
    public void deleteDistanceMatrix(DistanceMatrixAdapterSpecificData distanceMatrixAdapterSpecificData) {
        DistanceMatrixS3AdapterSpecificData distanceMatrixS3AdapterSpecificData =
                (DistanceMatrixS3AdapterSpecificData) distanceMatrixAdapterSpecificData;

        s3FileRepository.delete(distanceMatrixS3AdapterSpecificData.getUrl());
    }
}
