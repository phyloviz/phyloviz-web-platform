package org.phyloviz.pwp.compute.repository.metadata.templates.workflow_template.documents;

import lombok.Data;
import org.phyloviz.pwp.compute.repository.metadata.templates.workflow_template.documents.arguments.WorkflowTemplateArgumentProperties;
import org.phyloviz.pwp.compute.service.exceptions.WorkflowTemplateConfigurationException;
import org.phyloviz.pwp.compute.service.flowviz.models.workflow.Workflow;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;


@Data
@Document(collection = "workflow-templates")
public class WorkflowTemplate {

    private final String name;
    private final String description;
    private final Map<String, WorkflowTemplateArgumentProperties> arguments;
    private final List<TaskTemplate> tasks;
    @Id
    private String id;

    public WorkflowTemplate(String id, String name, String description, Map<String, WorkflowTemplateArgumentProperties> arguments, List<TaskTemplate> tasks) {
        if (name == null || name.isBlank()) {
            throw new WorkflowTemplateConfigurationException(
                    String.format("Workflow template %s - name cannot be null or blank", id));
        }
        if (description == null || description.isBlank()) {
            throw new WorkflowTemplateConfigurationException(
                    String.format("Workflow template %s - description cannot be null or blank", id));
        }
        if (arguments == null || arguments.isEmpty()) {
            throw new WorkflowTemplateConfigurationException(
                    String.format("Workflow template %s - arguments cannot be null or empty", id));
        }
        if (tasks == null || tasks.isEmpty()) {
            throw new WorkflowTemplateConfigurationException(
                    String.format("Workflow template %s - tasks cannot be null or empty", id));
        }

        arguments.forEach((key, value) -> {
            if (key.equals("projectId")) {
                throw new WorkflowTemplateConfigurationException(
                        String.format("Workflow template %s - arguments - projectId is a reserved argument name", id));
            }
            if (key.equals("workflowId")) {
                throw new WorkflowTemplateConfigurationException(
                        String.format("Workflow template %s - arguments - workflowId is a reserved argument name", id));
            }
        });

        this.name = name;
        this.description = description;
        this.arguments = arguments;
        this.tasks = tasks;
    }

    public Workflow buildWorkflow(WorkflowTemplateData workflowTemplateData) {

        return Workflow.builder()
                .name(name + "-" + workflowTemplateData.getWorkflowId())
                .description(description)
                // Need to set start date to be in the past to allow workflow to run immediately
                .startDate(LocalDateTime.now().minus(1, ChronoUnit.HOURS))
                .tasks(tasks.stream().map(task -> task.buildTask(workflowTemplateData)).toList())
                .build();
    }
}
