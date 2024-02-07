package org.phyloviz.pwp.repository.metadata.distance_matrix.documents.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistanceMatrixSourceFunction implements DistanceMatrixSource {
    private String function;
}
