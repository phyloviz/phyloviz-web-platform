package org.phyloviz.pwp.repository.metadata.project.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileIds {
    private List<String> typingDataIds;
    private List<String> isolateDataIds;
}
