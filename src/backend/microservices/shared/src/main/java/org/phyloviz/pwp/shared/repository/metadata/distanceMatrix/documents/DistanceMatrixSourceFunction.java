package org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistanceMatrixSourceFunction implements DistanceMatrixSource {
    private String function;
}