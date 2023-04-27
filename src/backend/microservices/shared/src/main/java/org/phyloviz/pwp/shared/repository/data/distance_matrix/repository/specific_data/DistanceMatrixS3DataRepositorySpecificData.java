package org.phyloviz.pwp.shared.repository.data.distance_matrix.repository.specific_data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DistanceMatrixS3DataRepositorySpecificData implements DistanceMatrixDataRepositorySpecificData {
    private String url;
}
