package org.phyloviz.pwp.shared.repository.metadata.tree.documents.source;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TreeSourceType {
    ALGORITHM_DISTANCE_MATRIX(TreeSourceAlgorithmDistanceMatrix.class),
    ALGORITHM_TYPING_DATA(TreeSourceAlgorithmTypingData.class),
    FILE(TreeSourceFile.class);

    private final Class<? extends TreeSource> sourceClass;
}
