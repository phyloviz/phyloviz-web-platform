package org.phyloviz.pwp.shared.adapters.typing_data.adapter.specific_data;

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
