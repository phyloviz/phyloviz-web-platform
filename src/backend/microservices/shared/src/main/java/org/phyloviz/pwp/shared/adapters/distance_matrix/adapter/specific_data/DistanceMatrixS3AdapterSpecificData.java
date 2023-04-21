package org.phyloviz.pwp.shared.adapters.distance_matrix.adapter.specific_data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DistanceMatrixS3AdapterSpecificData implements DistanceMatrixAdapterSpecificData {
    private String url;
}
