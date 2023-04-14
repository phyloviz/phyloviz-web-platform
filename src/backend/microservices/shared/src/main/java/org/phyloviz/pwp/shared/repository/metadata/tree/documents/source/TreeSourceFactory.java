package org.phyloviz.pwp.shared.repository.metadata.tree.documents.source;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.source.DistanceMatrixSource;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.source.DistanceMatrixSourceFunction;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TreeSourceFactory {

    public Class<? extends TreeSource> getClass(String sourceType) {
        return switch (sourceType) {
            case "algorithmDistanceMatrix" -> TreeSourceAlgorithmDistanceMatrix.class;
            case "algorithmTypingData" -> TreeSourceAlgorithmTypingData.class;
            case "file" -> TreeSourceFile.class;
            default -> throw new IllegalArgumentException("Unknown source type: " + sourceType);
        };
    }
}
