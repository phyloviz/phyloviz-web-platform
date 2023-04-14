package org.phyloviz.pwp.shared.repository.metadata.typingData.documents.adapterSpecificData;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData.TreeAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData.TreePhyloDBAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData.TreeS3AdapterSpecificData;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TypingDataAdapterSpecificDataFactory {

    public Class<? extends TypingDataAdapterSpecificData> getClass(String adapterId) {
        return switch (adapterId) {
            case "s3" -> TypingDataS3AdapterSpecificData.class;
            default -> throw new IllegalArgumentException("Unknown adapter id: " + adapterId);
        };
    }
}
