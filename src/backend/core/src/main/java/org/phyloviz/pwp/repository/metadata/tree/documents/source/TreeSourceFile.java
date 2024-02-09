package org.phyloviz.pwp.repository.metadata.tree.documents.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeSourceFile implements TreeSource {
    private String fileType;
    private String fileName;
}
