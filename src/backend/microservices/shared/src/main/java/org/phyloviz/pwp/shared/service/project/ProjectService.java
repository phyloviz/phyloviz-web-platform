package org.phyloviz.pwp.shared.service.project;

import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.service.dtos.project.CreateProjectOutput;
import org.phyloviz.pwp.shared.service.dtos.project.ProjectDTO;

import java.util.List;

public interface ProjectService {

    CreateProjectOutput createProject(String name, String description, String userId);

    Project getProject(String projectId, String userId);

    ProjectDTO getProjectDTO(String projectId, String userId);

    List<Project> getProjects(String userId);

    List<ProjectDTO> getProjectDTOs(String userId);

    void assertExists(String projectId, String userId);

    Project saveProject(Project project);

    void deleteProject(String projectId, String userId);
}
