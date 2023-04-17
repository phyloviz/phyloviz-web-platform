package org.phyloviz.pwp.shared.service.project;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.FileIds;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.service.dtos.files.FilesInfo;
import org.phyloviz.pwp.shared.service.dtos.project.CreateProjectOutput;
import org.phyloviz.pwp.shared.service.dtos.project.FullProjectInfo;
import org.phyloviz.pwp.shared.service.exceptions.EmptyProjectNameException;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetService;
import org.phyloviz.pwp.shared.service.project.file.isolate_data.IsolateDataService;
import org.phyloviz.pwp.shared.service.project.file.typing_data.TypingDataService;
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

        Project storedProject = projectAccessService.saveProject(project);
        return new CreateProjectOutput(storedProject.getId());
    }

    @Override
    public FullProjectInfo getFullProjectInfo(String projectId, String userId) {
        Project project = projectAccessService.getProject(projectId, userId);

        return getFullProjectInfo(project);
    }

    @Override
    public List<Project> getProjects(String userId) {
        return projectAccessService.getProjects(userId);
    }

    @Override
    public void deleteProject(String projectId, String userId) {
        Project project = projectAccessService.getProject(projectId, userId);

        project.getDatasetIds().forEach(datasetService::deleteDataset);
        project.getFileIds().getTypingDataIds().forEach(typingDataService::deleteTypingData);
        project.getFileIds().getIsolateDataIds().forEach(isolateDataService::deleteIsolateData);

        projectAccessService.deleteProject(projectId, userId);
    }

    /**
     * Gets a ProjectDTO from a Project, fetching the datasets and files, since the Project only stores the ids.
     */
    private FullProjectInfo getFullProjectInfo(Project project) {
        return new FullProjectInfo(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getOwnerId(),
                project.getDatasetIds().stream().map(datasetService::getFullDatasetInfo).toList(),
                new FilesInfo(
                        project.getFileIds().getTypingDataIds().stream().map(typingDataService::getTypingDataInfo).toList(),
                        project.getFileIds().getIsolateDataIds().stream().map(isolateDataService::getIsolateDataInfo).toList()
                )
        );
    }
}
