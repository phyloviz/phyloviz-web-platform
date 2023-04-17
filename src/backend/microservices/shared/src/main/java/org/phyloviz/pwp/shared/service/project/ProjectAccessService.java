package org.phyloviz.pwp.shared.service.project;

import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;

import java.util.List;

public interface ProjectAccessService {

    Project getProject(String projectId, String userId);

    List<Project> getProjects(String userId);

    Project saveProject(Project project);

    void deleteProject(String projectId, String userId);

    void assertExists(String projectId, String userId);
}
