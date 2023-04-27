package org.phyloviz.pwp.shared.repository.data.tree.repository.specific_data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TreeS3DataRepositorySpecificData implements TreeDataRepositorySpecificData {
    private String url;
}
