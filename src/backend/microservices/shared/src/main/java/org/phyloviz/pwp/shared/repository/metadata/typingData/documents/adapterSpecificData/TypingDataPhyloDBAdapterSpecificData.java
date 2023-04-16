package org.phyloviz.pwp.shared.repository.metadata.typingData.documents.adapterSpecificData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypingDataPhyloDBAdapterSpecificData implements TypingDataAdapterSpecificData {
    private List<String> datasetIds;
}
