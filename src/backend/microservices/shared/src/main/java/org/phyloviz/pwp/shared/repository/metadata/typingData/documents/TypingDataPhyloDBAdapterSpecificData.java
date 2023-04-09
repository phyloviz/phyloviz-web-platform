package org.phyloviz.pwp.shared.repository.metadata.typingData.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeAlias("typingDataPhyloDBAdapterSpecificData")
public class TypingDataPhyloDBAdapterSpecificData implements TypingDataAdapterSpecificData {
    private List<String> datasetIds;
}
