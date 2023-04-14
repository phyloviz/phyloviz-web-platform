package org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.adapterSpecificData;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.adapterSpecificData.DistanceMatrixAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.adapterSpecificData.DistanceMatrixS3AdapterSpecificData;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IsolateDataAdapterSpecificDataFactory {

    public Class<? extends IsolateDataAdapterSpecificData> getClass(String adapterId) {
        return switch (adapterId) {
            case "s3" -> IsolateDataS3AdapterSpecificData.class;
            default -> throw new IllegalArgumentException("Unknown adapter id: " + adapterId);
        };
    }
}
