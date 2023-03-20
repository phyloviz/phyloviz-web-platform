package org.phyloviz.pwp.administration.http.controllers.models.deleteProject;


import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.createProject.CreateProjectOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.deleteProject.DeleteProjectOutputDTO;

@Data
public class DeleteProjectOutputModel {
    private String projectId;

    public DeleteProjectOutputModel(DeleteProjectOutputDTO deleteProjectOutputDTO) {
        this.projectId = deleteProjectOutputDTO.getProjectId();
    }
}
