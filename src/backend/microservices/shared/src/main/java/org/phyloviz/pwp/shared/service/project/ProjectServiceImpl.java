package org.phyloviz.pwp.shared.service.project;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.FileIds;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.service.dtos.files.FilesDTO;
import org.phyloviz.pwp.shared.service.dtos.project.CreateProjectOutput;
import org.phyloviz.pwp.shared.service.dtos.project.ProjectDTO;
import org.phyloviz.pwp.shared.service.exceptions.EmptyProjectNameException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetService;
import org.phyloviz.pwp.shared.service.project.file.isolateData.IsolateDataService;
import org.phyloviz.pwp.shared.service.project.file.typingData.TypingDataService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectAccessService projectAccessService;

    private final DatasetService datasetService;
    private final TypingDataService typingDataService;
    private final IsolateDataService isolateDataService;

    @Override
    public CreateProjectOutput createProject(String name, String description, String userId) {
        if (name == null || name.isBlank())
            throw new EmptyProjectNameException();

        Project project = new Project(
                name,
                description,
                userId,
                Collections.emptyList(),
                new FileIds(
                        Collections.emptyList(),
                        Collections.emptyList()
                )
        );

        Project storedProject = saveProject(project);
        return new CreateProjectOutput(storedProject.getId());
    }

    @Override
    public Project getProject(String projectId, String userId) {
        return projectAccessService.getProject(projectId, userId);
    }

    @Override
    public ProjectDTO getProjectDTO(String projectId, String userId) {
        Project project = getProject(projectId, userId);

        return getProjectDTO(project);
    }

    @Override
    public List<Project> getProjects(String userId) {
        return projectAccessService.getProjects(userId);
    }

    @Override
    public List<ProjectDTO> getProjectDTOs(String userId) {
        return getProjects(userId).stream().map(this::getProjectDTO).toList();
    }

    @Override
    public void assertExists(String projectId, String userId) {
        projectAccessService.assertExists(projectId, userId);
    }

    @Override
    public Project saveProject(Project project) {
        return projectAccessService.saveProject(project);
    }

    @Override
    public void deleteProject(String projectId, String userId) {
        Project project = getProject(projectId, userId);

        project.getDatasetIds().forEach(datasetService::deleteDataset);
        project.getFileIds().getTypingDataIds().forEach(typingDataService::deleteTypingData);
        project.getFileIds().getIsolateDataIds().forEach(isolateDataService::deleteIsolateData);

        projectAccessService.deleteProject(projectId, userId);
    }

    /**
     * Gets a ProjectDTO from a Project, fetching the datasets and files, since the Project only stores the ids.
     */
    private ProjectDTO getProjectDTO(Project project) {
        return new ProjectDTO(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getOwnerId(),
                project.getDatasetIds().stream().map(datasetService::getDatasetDTO).toList(),
                new FilesDTO(
                        project.getFileIds().getTypingDataIds().stream().map(typingDataService::getTypingDataMetadataDTO).toList(),
                        project.getFileIds().getIsolateDataIds().stream().map(isolateDataService::getIsolateDataMetadataDTO).toList()
                )
        );
    }
}
