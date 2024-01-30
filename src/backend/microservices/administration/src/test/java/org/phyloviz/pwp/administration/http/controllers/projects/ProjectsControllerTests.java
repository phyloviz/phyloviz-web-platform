package org.phyloviz.pwp.administration.http.controllers.projects;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.phyloviz.pwp.administration.http.models.projects.create_project.CreateProjectInputModel;
import org.phyloviz.pwp.administration.service.dtos.project.CreateProjectOutput;
import org.phyloviz.pwp.administration.service.project.ProjectService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProjectsControllerTests {

    @MockBean
    private ProjectService projectService;

    @Test
    void createProjectIsSuccessful() {
        CreateProjectInputModel createProjectInputModel = new CreateProjectInputModel();
        createProjectInputModel.setName("project1");
        createProjectInputModel.setDescription("project1 description");

        String projectId = "projectId";
        String userId = "userId";
        Mockito.when(projectService.createProject(
                createProjectInputModel.getName(),
                createProjectInputModel.getDescription(),
                userId
        )).thenReturn(
                new CreateProjectOutput(projectId)
        );

        CreateProjectOutput createProjectOutput = projectService.createProject(
                createProjectInputModel.getName(),
                createProjectInputModel.getDescription(),
                userId
        );

        assertEquals(projectId, createProjectOutput.getProjectId());
    }
}
