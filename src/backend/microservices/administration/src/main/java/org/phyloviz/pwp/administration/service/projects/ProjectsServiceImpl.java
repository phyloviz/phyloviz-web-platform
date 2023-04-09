package org.phyloviz.pwp.administration.service.projects;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.service.dtos.files.FilesDTO;
import org.phyloviz.pwp.administration.service.dtos.projects.ProjectDTO;
import org.phyloviz.pwp.administration.service.dtos.projects.createProject.CreateProjectInputDTO;
import org.phyloviz.pwp.administration.service.dtos.projects.createProject.CreateProjectOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.projects.deleteProject.DeleteProjectInputDTO;
import org.phyloviz.pwp.administration.service.dtos.projects.deleteProject.DeleteProjectOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.projects.getProject.GetProjectInputDTO;
import org.phyloviz.pwp.administration.service.dtos.projects.getProjects.GetProjectsInputDTO;
import org.phyloviz.pwp.administration.service.exceptions.EmptyProjectNameException;
import org.phyloviz.pwp.administration.service.projects.datasets.DatasetsService;
import org.phyloviz.pwp.administration.service.projects.files.isolateData.IsolateDataService;
import org.phyloviz.pwp.administration.service.projects.files.typingData.TypingDataService;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.FileIds;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.UnauthorizedException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectsServiceImpl implements ProjectsService {

    private final ProjectRepository projectRepository;

    private final DatasetsService datasetsService;
    private final TypingDataService typingDataService;
    private final IsolateDataService isolateDataService;

    @Override
    public CreateProjectOutputDTO createProject(CreateProjectInputDTO createProjectInputDTO) {
        String name = createProjectInputDTO.getName();

        if (name == null || name.isBlank())
            throw new EmptyProjectNameException();

        Project project = new Project(
                createProjectInputDTO.getName(),
                createProjectInputDTO.getDescription(),
                createProjectInputDTO.getOwner().getId(),
                Collections.emptyList(),
                new FileIds(
                        Collections.emptyList(),
                        Collections.emptyList()
                )
        );

        Project storedProject = projectRepository.save(project);
        return new CreateProjectOutputDTO(storedProject.getId());
    }

    @Override
    public ProjectDTO getProject(GetProjectInputDTO getProjectInputDTO) {
        Project project = projectRepository.findById(getProjectInputDTO.getProjectId())
                .orElseThrow(ProjectNotFoundException::new);

        if (!project.getOwnerId().equals(getProjectInputDTO.getUser().getId()))
            throw new UnauthorizedException();

        return getProjectDTO(project);
    }

    @Override
    public DeleteProjectOutputDTO deleteProject(DeleteProjectInputDTO deleteProjectInputDTO) throws ProjectNotFoundException {
        String projectId = deleteProjectInputDTO.getProjectId();
        String userId = deleteProjectInputDTO.getUser().getId();

        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        if (!project.getOwnerId().equals(userId))
            throw new UnauthorizedException();

        project.getDatasetIds().forEach(datasetsService::deleteDataset);
        project.getFileIds().getTypingDataIds().forEach(typingDataService::deleteTypingData);
        project.getFileIds().getIsolateDataIds().forEach(isolateDataService::deleteIsolateData);

        projectRepository.delete(project);

        return new DeleteProjectOutputDTO(projectId);
    }

    @Override
    public List<ProjectDTO> getProjects(GetProjectsInputDTO getProjectsInputDTO) {
        List<Project> projects = projectRepository.findAllByOwnerId(getProjectsInputDTO.getUser().getId());

        return projects.stream().map(this::getProjectDTO).toList();
    }

    /**
     * Gets a ProjectDTO from a Project, fetching the datasets and files, since the Project only stores the ids.
     *
     * @param project the project to get the DTO from
     * @return the dto of the project
     */
    private ProjectDTO getProjectDTO(Project project) {
        return new ProjectDTO(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getOwnerId(),
                project.getDatasetIds().stream().map(datasetsService::getDataset).toList(),
                new FilesDTO(
                        project.getFileIds().getTypingDataIds().stream().map(typingDataService::getTypingData).toList(),
                        project.getFileIds().getIsolateDataIds().stream().map(isolateDataService::getIsolateData).toList()
                )
        );
    }
}
