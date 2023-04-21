package org.phyloviz.pwp.shared_phylodb.adapters.typing_data.phylodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.phyloviz.pwp.shared.adapters.typing_data.adapter.specific_data.TypingDataAdapterSpecificData;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypingDataPhyloDBAdapterSpecificData implements TypingDataAdapterSpecificData {
    private List<String> datasetIds;
}
