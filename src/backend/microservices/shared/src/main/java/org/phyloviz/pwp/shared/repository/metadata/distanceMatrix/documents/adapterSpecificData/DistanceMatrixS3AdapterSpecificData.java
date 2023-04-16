package org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.adapterSpecificData;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DistanceMatrixS3AdapterSpecificData implements DistanceMatrixAdapterSpecificData {
    private String url;
}
