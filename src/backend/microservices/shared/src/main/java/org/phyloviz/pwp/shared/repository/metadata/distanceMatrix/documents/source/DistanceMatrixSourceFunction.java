package org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistanceMatrixSourceFunction implements DistanceMatrixSource {
    private String function;
}
