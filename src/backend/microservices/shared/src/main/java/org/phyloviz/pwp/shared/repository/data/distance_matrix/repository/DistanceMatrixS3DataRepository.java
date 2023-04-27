package org.phyloviz.pwp.shared.repository.data.distance_matrix.repository;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.S3FileRepository;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.repository.specific_data.DistanceMatrixDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.repository.specific_data.DistanceMatrixS3DataRepositorySpecificData;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DistanceMatrixS3DataRepository implements DistanceMatrixDataRepository {

    private final S3FileRepository s3FileRepository;

    @Override
    public String getDistanceMatrix(DistanceMatrixDataRepositorySpecificData distanceMatrixDataRepositorySpecificData) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean isFileRepository() {
        return true;
    }

    @Override
    public void deleteDistanceMatrix(DistanceMatrixDataRepositorySpecificData distanceMatrixDataRepositorySpecificData) {
        DistanceMatrixS3DataRepositorySpecificData distanceMatrixS3DataRepositorySpecificData =
                (DistanceMatrixS3DataRepositorySpecificData) distanceMatrixDataRepositorySpecificData;

        s3FileRepository.delete(distanceMatrixS3DataRepositorySpecificData.getUrl());
    }
}
