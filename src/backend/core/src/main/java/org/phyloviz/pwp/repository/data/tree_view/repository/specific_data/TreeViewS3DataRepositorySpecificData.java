package org.phyloviz.pwp.repository.data.tree_view.repository.specific_data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TreeViewS3DataRepositorySpecificData implements TreeViewDataRepositorySpecificData {
    private String url;
}
