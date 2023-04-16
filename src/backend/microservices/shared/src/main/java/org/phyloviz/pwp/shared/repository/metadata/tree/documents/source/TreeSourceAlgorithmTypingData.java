package org.phyloviz.pwp.shared.repository.metadata.tree.documents.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeSourceAlgorithmTypingData implements TreeSource {
    private String algorithm;
    private String typingDataId;
    private String parameters;
}
