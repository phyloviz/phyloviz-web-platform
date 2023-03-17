package org.phyloviz.pwp.administration.service;

import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import org.phyloviz.pwp.administration.service.dtos.ProjectDTO;
import org.phyloviz.pwp.administration.service.dtos.createProject.CreateProjectInputDTO;
import org.phyloviz.pwp.administration.service.dtos.createProject.CreateProjectOutputDTO;
import org.phyloviz.pwp.shared.repository.metadata.documents.Project;
import org.phyloviz.pwp.shared.repository.metadata.mongo.ProjectMongoRepository;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.UnauthorizedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of the {@link AdministrationService} interface.
 */
@Service
@Transactional
@AllArgsConstructor
public class AdministrationServiceImpl implements AdministrationService {

    private final ProjectMongoRepository projectMongoRepository;

    @Override
    public CreateProjectOutputDTO createProject(CreateProjectInputDTO createProjectInputDTO) {
        Project project = new Project(
                createProjectInputDTO.getName(),
                createProjectInputDTO.getDescription(),
                createProjectInputDTO.getOwner().getId(),
                Collections.emptyList()
        );

        Project storedProject = projectMongoRepository.save(project);
        return new CreateProjectOutputDTO(storedProject.getId());
    }

    @Override
    public void deleteProject(String projectId, UserDTO userDTO) {
        Project project = projectMongoRepository
                .findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        if (!project.getOwner().equals(userDTO.getId()))
            throw new UnauthorizedException("User is not the owner of the project");

        projectMongoRepository.delete(project);
    }

    @Override
    public ProjectDTO getProject(String projectId, UserDTO toDTO) {
        Project project = projectMongoRepository
                .findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        if (!project.getOwner().equals(toDTO.getId()))
            throw new UnauthorizedException("User is not the owner of the project");

        return new ProjectDTO(project);
    }

    @Override
    public List<ProjectDTO> getProjects(UserDTO userDTO) {
        List<Project> projects = projectMongoRepository
                .findAllByOwner(userDTO.getId());

        return projects.stream().map(ProjectDTO::new).toList();
    }
}
