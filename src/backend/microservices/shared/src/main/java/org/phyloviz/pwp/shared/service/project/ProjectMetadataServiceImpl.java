package org.phyloviz.pwp.shared.service.project;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectMetadataServiceImpl implements ProjectMetadataService {

    private final ProjectRepository projectRepository;

    @Override
    public Project getProject(String projectId, String userId) {
        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        if (!project.getOwnerId().equals(userId))
            throw new ProjectNotFoundException();

        return project;
    }

    @Override
    public List<Project> getProjects(String userId) {
        return projectRepository.findAllByOwnerId(userId);
    }

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }

    @Override
    public void assertExists(String projectId, String userId) {
        getProject(projectId, userId);
    }
}
