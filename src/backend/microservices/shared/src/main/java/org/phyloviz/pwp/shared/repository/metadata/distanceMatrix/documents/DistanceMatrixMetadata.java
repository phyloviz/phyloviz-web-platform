package org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "distance-matrices")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeAlias("distanceMatrixMetadata")
public class DistanceMatrixMetadata {
    @Id
    private String id;

    private String projectId;
    private String distanceMatrixId;
    private String name;
    private String sourceType;
    private DistanceMatrixSource source;
    private String url;
    private String adapterId;
    private DistanceMatrixAdapterSpecificData adapterSpecificData;

    public DistanceMatrixMetadata(String projectId, String distanceMatrixId, String name, String sourceType,
                                  DistanceMatrixSource source, String url, String adapterId,
                                  DistanceMatrixAdapterSpecificData adapterSpecificData) {
        this.projectId = projectId;
        this.distanceMatrixId = distanceMatrixId;
        this.name = name;
        this.sourceType = sourceType;
        this.source = source;
        this.url = url;
        this.adapterId = adapterId;
        this.adapterSpecificData = adapterSpecificData;
    }
}
