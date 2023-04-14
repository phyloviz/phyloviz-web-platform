package org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.adapterSpecificData;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;

@Data
@AllArgsConstructor
@TypeAlias("distanceMatrixS3AdapterSpecificData")
public class DistanceMatrixS3AdapterSpecificData implements DistanceMatrixAdapterSpecificData {
}
