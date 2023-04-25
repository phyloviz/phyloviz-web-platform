package org.phyloviz.pwp.administration.service.project;

import org.phyloviz.pwp.administration.service.dtos.project.UpdateProjectOutput;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.administration.service.dtos.project.CreateProjectOutput;
import org.phyloviz.pwp.administration.service.dtos.project.FullProjectInfo;

import java.util.List;

public interface ProjectService {

    CreateProjectOutput createProject(String name, String description, String userId);

    FullProjectInfo getFullProjectInfo(String projectId, String userId);

    List<Project> getProjects(String userId);

    void deleteProject(String projectId, String userId);

    UpdateProjectOutput updateProject(String name, String description, String projectId, String userId);
}
