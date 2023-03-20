package org.phyloviz.pwp.administration.http.controllers.models.deleteInferenceTree;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.deleteInferenceTree.DeleteInferenceTreeOutputDTO;

@Data
public class DeleteInferenceTreeOutputModel {
    private String projectId;
    private String inferenceTreeId;

    public DeleteInferenceTreeOutputModel(DeleteInferenceTreeOutputDTO deleteInferenceTreeOutputDTO) {
        this.projectId = deleteInferenceTreeOutputDTO.getProjectId();
        this.inferenceTreeId = deleteInferenceTreeOutputDTO.getInferenceTreeId();
    }
}
