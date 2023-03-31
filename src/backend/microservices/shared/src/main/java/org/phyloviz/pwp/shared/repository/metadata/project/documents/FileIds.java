package org.phyloviz.pwp.shared.repository.metadata.project.documents;

import lombok.Data;

import java.util.List;

@Data
public class FileIds {
    private final List<String> typingDataIds;
    private final List<String> isolateDataIds;
}
