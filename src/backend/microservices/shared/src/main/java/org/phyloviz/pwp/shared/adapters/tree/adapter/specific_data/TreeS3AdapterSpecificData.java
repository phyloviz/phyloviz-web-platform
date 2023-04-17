package org.phyloviz.pwp.shared.adapters.tree.adapter.specific_data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TreeS3AdapterSpecificData implements TreeAdapterSpecificData {
    private String url;
}
