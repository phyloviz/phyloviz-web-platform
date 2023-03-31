package org.phyloviz.pwp.shared.repository.metadata.tree.documents;

import lombok.Data;

@Data
public class TreeSourceFile implements TreeSource {
    private final String fileType;
    private final String fileName;
}
