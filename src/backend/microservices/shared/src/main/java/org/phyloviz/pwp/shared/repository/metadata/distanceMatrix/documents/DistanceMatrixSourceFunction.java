package org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeAlias("distanceMatrixSourceFunction")
public class DistanceMatrixSourceFunction implements DistanceMatrixSource {
    private String function;
}
