package org.phyloviz.pwp.shared.repository.data.isolate_data.repository.specific_data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class IsolateDataPhyloDBDataRepositorySpecificData implements IsolateDataDataRepositorySpecificData {
    private String projectId;
    private List<String> datasetIds;
}
