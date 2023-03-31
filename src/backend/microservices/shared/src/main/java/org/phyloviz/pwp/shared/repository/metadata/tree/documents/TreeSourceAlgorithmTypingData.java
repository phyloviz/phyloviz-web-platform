package org.phyloviz.pwp.shared.repository.metadata.tree.documents;

import lombok.Data;

@Data
public class TreeSourceAlgorithmTypingData implements TreeSource {
    private final String algorithm;
    private final String typingDataId;
    private final String parameters;
}
