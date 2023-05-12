package org.phyloviz.pwp.shared_phylodb.repository.data.phylodb.isolate_data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.specific_data.IsolateDataDataRepositorySpecificData;

import java.util.List;

@Data
@AllArgsConstructor
public class IsolateDataPhyloDBDataRepositorySpecificData implements IsolateDataDataRepositorySpecificData {
    private String projectId;
    private List<String> datasetIds;
}
