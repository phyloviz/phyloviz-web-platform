package org.phyloviz.pwp.administration.service.project;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.service.dtos.files.FilesInfo;
import org.phyloviz.pwp.administration.service.dtos.project.CreateProjectOutput;
import org.phyloviz.pwp.administration.service.dtos.project.FullProjectInfo;
import org.phyloviz.pwp.administration.service.dtos.project.UpdateProjectOutput;
import org.phyloviz.pwp.administration.service.project.dataset.DatasetService;
import org.phyloviz.pwp.administration.service.project.file.IsolateDataService;
import org.phyloviz.pwp.administration.service.project.file.TypingDataService;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.service.exceptions.InvalidArgumentException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
@Service
//@Transactional
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final DatasetService datasetService;
    private final TypingDataService typingDataService;
    private final IsolateDataService isolateDataService;

    @Override
    public CreateProjectOutput createProject(String name, String description, String userId) {
        if (name == null || name.isBlank())
            throw new InvalidArgumentException("Project name cannot be empty");

        Project project = new Project(
                name,
                description,
                userId
        );

        Project storedProject = projectRepository.save(project);
        return new CreateProjectOutput(storedProject.getId());
    }

    @Override
    public FullProjectInfo getFullProjectInfo(String projectId, String userId) {
        Project project = projectRepository.findByIdAndOwnerId(projectId, userId)
                .orElseThrow(ProjectNotFoundException::new);

        return new FullProjectInfo(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getOwnerId(),
                datasetService.getFullDatasetInfos(project.getId()),
                new FilesInfo(
                        typingDataService.getTypingDataInfos(project.getId()),
                        isolateDataService.getIsolateDataInfos(project.getId())
                )
        );
    }

    @Override
    public List<Project> getProjects(String userId) {
        return projectRepository.findAllByOwnerId(userId);
    }

    @Override
    public void deleteProject(String projectId, String userId) {
        Project project = projectRepository.findByIdAndOwnerId(projectId, userId)
                .orElseThrow(ProjectNotFoundException::new);

        datasetService.deleteAllByProjectId(projectId);
        typingDataService.deleteAllByProjectId(projectId);
        isolateDataService.deleteAllByProjectId(projectId);

        projectRepository.delete(project);
    }

    @Override
    public UpdateProjectOutput updateProject(String name, String description, String projectId, String userId) {
        Project project = projectRepository.findByIdAndOwnerId(projectId, userId)
                .orElseThrow(ProjectNotFoundException::new);

        String previousName = project.getName();
        String previousDescription = project.getDescription();

        if (name == null && description == null)
            throw new InvalidArgumentException("You have to provide at least one field to update");

        if (name != null && name.isBlank())
            throw new InvalidArgumentException("Name can't be blank");

        if (description != null && description.isBlank())
            throw new InvalidArgumentException("Description can't be blank");

        project.setName(name);
        project.setDescription(description);

        if (!project.getName().equals(previousName) || !project.getDescription().equals(previousDescription))
            projectRepository.save(project);

        return new UpdateProjectOutput(previousName, name, previousDescription, description);
    }
}
