package org.phyloviz.pwp.administration.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.phyloviz.pwp.administration.service.dtos.project.CreateProjectOutput;
import org.phyloviz.pwp.administration.service.dtos.project.FullProjectInfo;
import org.phyloviz.pwp.administration.service.dtos.project.UpdateProjectOutput;
import org.phyloviz.pwp.administration.service.project.ProjectService;
import org.phyloviz.pwp.administration.service.project.dataset.DatasetService;
import org.phyloviz.pwp.administration.service.project.file.IsolateDataService;
import org.phyloviz.pwp.administration.service.project.file.TypingDataService;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.service.exceptions.InvalidArgumentException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProjectsServiceTests {

    @MockBean
    private ProjectRepository projectRepository;

    @MockBean
    private DatasetService datasetService;

    @MockBean
    private TypingDataService typingDataService;

    @MockBean
    private IsolateDataService isolateDataService;

    @Autowired
    private ProjectService projectService;

    // createProject
    @Test
    void createProjectIsSuccessful() {
        String projectName = "project1";
        String projectDescription = "project1 description";
        String userId = "2da00a2f-21bd-43fd-8c07-0e74725c851a";
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";

        when(projectRepository.save(any(Project.class)))
                .thenReturn(
                        new Project(
                                projectId,
                                projectName,
                                projectDescription,
                                userId
                        )
                );

        CreateProjectOutput createProjectOutput = projectService.createProject(
                projectName,
                projectDescription,
                userId
        );

        assertEquals(projectId, createProjectOutput.getProjectId());
    }

    @Test
    void createProjectWithEmptyNameThrowsException() {
        String projectName = "";
        String projectDescription = "project1 description";
        String userId = "2da00a2f-21bd-43fd-8c07-0e74725c851a";

        assertThrows(InvalidArgumentException.class, () ->
                projectService.createProject(
                        projectName,
                        projectDescription,
                        userId
                ));
    }


    // getFullProjectInfo
    @Test
    void getFullProjectInfoIsSuccessful() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String projectName = "project1";
        String projectDescription = "project1 description";
        String userId = "2da00a2f-21bd-43fd-8c07-0e74725c851a";

        when(projectRepository.findByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(
                        Optional.of(new Project(
                                projectId,
                                projectName,
                                projectDescription,
                                userId
                        ))
                );

        when(datasetService.getFullDatasetInfos(any(String.class))).thenReturn(List.of());
        when(typingDataService.getTypingDataInfos(any(String.class))).thenReturn(List.of());
        when(isolateDataService.getIsolateDataInfos(any(String.class))).thenReturn(List.of());

        FullProjectInfo fullProjectInfo = projectService.getFullProjectInfo(projectId, userId);

        assertEquals(projectId, fullProjectInfo.getProjectId());
        assertEquals(projectName, fullProjectInfo.getName());
        assertEquals(projectDescription, fullProjectInfo.getDescription());
        assertEquals(userId, fullProjectInfo.getOwner());
        assertTrue(fullProjectInfo.getDatasets().isEmpty());
        assertTrue(fullProjectInfo.getFiles().getTypingData().isEmpty());
        assertTrue(fullProjectInfo.getFiles().getIsolateData().isEmpty());
    }

    @Test
    void getFullProjectInfoWithInexistentProjectIdThrowsException() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String userId = "2da00a2f-21bd-43fd-8c07-0e74725c851a";

        when(projectRepository.findByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(Optional.empty());

        assertThrows(ProjectNotFoundException.class, () ->
                projectService.getFullProjectInfo(projectId, userId)
        );
    }

    @Test
    void getFullProjectInfoWithInexistentUserIdThrowsException() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String userId = "2da00a2f-21bd-43fd-8c07-0e74725c851a";

        when(projectRepository.findByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(Optional.empty());

        assertThrows(ProjectNotFoundException.class, () ->
                projectService.getFullProjectInfo(projectId, userId)
        );
    }


    // getProjects
    @Test
    void getProjectsIsSuccessful() {
        String userId = "2da00a2f-21bd-43fd-8c07-0e74725c851a";
        List<Project> projects = List.of(
                new Project(
                        "ec7bae63-3238-4044-8d03-e2d9911f50f8",
                        "project1",
                        "project1 description",
                        userId
                ),
                new Project(
                        "ec7bae63-3238-4044-8d03-e2d9911f50f9",
                        "project2",
                        "project2 description",
                        userId
                )
        );

        when(projectRepository.findAllByOwnerId(any(String.class)))
                .thenReturn(projects);

        List<Project> userProjects = projectService.getProjects(userId);
        assertEquals(projects.size(), userProjects.size());
        assertEquals(projects, userProjects);
    }


    // deleteProject
    @Test
    void deleteProjectIsSuccessful() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String userId = "2da00a2f-21bd-43fd-8c07-0e74725c851a";

        when(projectRepository.findByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(
                        Optional.of(new Project(
                                projectId,
                                "project1",
                                "project1 description",
                                userId
                        ))
                );

        projectService.deleteProject(projectId, userId);

        verify(projectRepository, times(1)).delete(any(Project.class));
    }

    @Test
    void deleteProjectWithInexistentProjectIdThrowsException() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String userId = "2da00a2f-21bd-43fd-8c07-0e74725c851a";

        when(projectRepository.findByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(Optional.empty());

        assertThrows(ProjectNotFoundException.class, () ->
                projectService.deleteProject(projectId, userId)
        );
    }

    @Test
    void deleteProjectWithInexistentUserIdThrowsException() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String userId = "2da00a2f-21bd-43fd-8c07-0e74725c851a";

        when(projectRepository.findByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(Optional.empty());

        assertThrows(ProjectNotFoundException.class, () ->
                projectService.deleteProject(projectId, userId)
        );
    }


    // updateProject
    @Test
    void updateProjectIsSuccessful() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String projectName = "project1";
        String projectDescription = "project1 description";
        String userId = "2da00a2f-21bd-43fd-8c07-0e74725c851a";
        String newProjectName = "project1 updated";
        String newProjectDescription = "project1 description updated";

        when(projectRepository.findByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(
                        Optional.of(new Project(
                                projectId,
                                projectName,
                                projectDescription,
                                userId
                        ))
                );

        when(projectRepository.save(any(Project.class)))
                .thenReturn(new Project(
                        projectId,
                        newProjectName,
                        newProjectDescription,
                        userId
                ));

        UpdateProjectOutput updateProjectOutput = projectService.updateProject(
                newProjectName,
                newProjectDescription,
                projectId,
                userId
        );

        assertEquals(projectName, updateProjectOutput.getPreviousName());
        assertEquals(projectDescription, updateProjectOutput.getPreviousDescription());
        assertEquals(newProjectName, updateProjectOutput.getNewName());
        assertEquals(newProjectDescription, updateProjectOutput.getNewDescription());
    }

    @Test
    void updateProjectWithInexistentProjectIdThrowsException() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String userId = "2da00a2f-21bd-43fd-8c07-0e74725c851a";
        String newProjectName = "project1 updated";
        String newProjectDescription = "project1 description updated";

        when(projectRepository.findByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(Optional.empty());

        assertThrows(ProjectNotFoundException.class, () ->
                projectService.updateProject(
                        newProjectName,
                        newProjectDescription,
                        projectId,
                        userId
                ));
    }

    @ParameterizedTest
    @CsvSource({
            ",",
            "'', description",
            "name,''",
    })
    void updateProjectWithInvalidFieldsThrowsException(String newProjectName, String newProjectDescription) {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String projectName = "project1";
        String projectDescription = "project1 description";
        String userId = "2da00a2f-21bd-43fd-8c07-0e74725c851a";

        when(projectRepository.findByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(
                        Optional.of(new Project(
                                projectId,
                                projectName,
                                projectDescription,
                                userId
                        ))
                );

        assertThrows(InvalidArgumentException.class, () ->
                projectService.updateProject(
                        newProjectName,
                        newProjectDescription,
                        projectId,
                        userId
                ));
    }
}
