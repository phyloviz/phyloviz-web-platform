package org.phyloviz.pwp.shared.service.dtos.files;

import lombok.Data;

@Data
public class UploadIsolateDataOutput {
    private final String projectId;
    private final String isolateDataId;
}
