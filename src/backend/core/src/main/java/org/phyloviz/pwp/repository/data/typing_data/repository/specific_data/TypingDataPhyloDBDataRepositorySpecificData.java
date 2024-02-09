package org.phyloviz.pwp.repository.data.typing_data.repository.specific_data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TypingDataPhyloDBDataRepositorySpecificData implements TypingDataDataRepositorySpecificData {
    private String projectId;
    private List<String> datasetIds;
}
