package org.phyloviz.pwp.shared.repository.metadata.project.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeAlias("fileIds")
public class FileIds {
    private List<String> typingDataIds;
    private List<String> isolateDataIds;
}
