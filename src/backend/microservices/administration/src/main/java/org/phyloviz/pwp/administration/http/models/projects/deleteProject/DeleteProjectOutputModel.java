package org.phyloviz.pwp.administration.http.models.projects.deleteProject;


import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.projects.deleteProject.DeleteProjectOutputDTO;

@Data
public class DeleteProjectOutputModel {
    private String projectId;

    public DeleteProjectOutputModel(DeleteProjectOutputDTO deleteProjectOutputDTO) {
        this.projectId = deleteProjectOutputDTO.getProjectId();
    }
}
