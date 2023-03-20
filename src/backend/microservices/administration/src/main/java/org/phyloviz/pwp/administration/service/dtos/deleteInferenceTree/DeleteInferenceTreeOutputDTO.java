package org.phyloviz.pwp.administration.service.dtos.deleteInferenceTree;

import lombok.Data;

@Data
public class DeleteInferenceTreeOutputDTO {
    private final String projectId;
    private final String inferenceTreeId;
}
