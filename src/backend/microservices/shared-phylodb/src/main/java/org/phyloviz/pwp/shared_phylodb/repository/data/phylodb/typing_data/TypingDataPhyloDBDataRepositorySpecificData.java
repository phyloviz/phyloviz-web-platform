package org.phyloviz.pwp.shared_phylodb.repository.data.phylodb.typing_data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.phyloviz.pwp.shared.repository.data.typing_data.repository.specific_data.TypingDataDataRepositorySpecificData;

import java.util.List;

@Data
@AllArgsConstructor
public class TypingDataPhyloDBDataRepositorySpecificData implements TypingDataDataRepositorySpecificData {
    private String projectId;
    private List<String> datasetIds;
}
