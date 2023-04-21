package org.phyloviz.pwp.administration.http.service.project;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.http.service.project.dataset.DatasetService;
import org.phyloviz.pwp.administration.http.service.project.file.IsolateDataService;
import org.phyloviz.pwp.administration.http.service.project.file.TypingDataService;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.FileIds;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.service.dtos.files.FilesInfo;
import org.phyloviz.pwp.shared.service.dtos.project.CreateProjectOutput;
import org.phyloviz.pwp.shared.service.dtos.project.FullProjectInfo;
import org.phyloviz.pwp.shared.service.exceptions.InvalidArgumentException;
import org.phyloviz.pwp.shared.service.project.ProjectMetadataService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectMetadataService projectMetadataService;

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
                userId,
                Collections.emptyList(),
                new FileIds(
                        Collections.emptyList(),
                        Collections.emptyList()
                )
        );

        Project storedProject = projectMetadataService.saveProject(project);
        return new CreateProjectOutput(storedProject.getId());
    }

    @Override
    public FullProjectInfo getFullProjectInfo(String projectId, String userId) {
        Project project = projectMetadataService.getProject(projectId, userId);

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
        return projectMetadataService.getProjects(userId);
    }

    @Override
    public void deleteProject(String projectId, String userId) {
        Project project = projectMetadataService.getProject(projectId, userId);

        /*
        datasetService.deleteAllByProjectId(projectId); TODO potentially implement this, removing ids from the project, do the same for dataset
        typingDataService.deleteAllByProjectId(projectId);
        isolateDataService.deleteAllByProjectId(projectId);
         */

        project.getDatasetIds().forEach(datasetService::deleteDataset);
        project.getFileIds().getTypingDataIds().forEach(typingDataService::deleteTypingData);
        project.getFileIds().getIsolateDataIds().forEach(isolateDataService::deleteIsolateData);

        projectMetadataService.deleteProject(project);
    }
}
