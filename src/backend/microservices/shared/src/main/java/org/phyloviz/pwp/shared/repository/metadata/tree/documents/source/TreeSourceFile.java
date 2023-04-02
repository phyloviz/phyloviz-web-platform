package org.phyloviz.pwp.shared.repository.metadata.tree.documents.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeAlias("treeSourceFile")
public class TreeSourceFile implements TreeSource {
    private String fileType;
    private String fileName;
}
