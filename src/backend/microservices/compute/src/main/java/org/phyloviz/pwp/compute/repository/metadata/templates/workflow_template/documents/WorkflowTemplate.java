package org.phyloviz.pwp.compute.repository.metadata.templates.workflow_template.documents;

import lombok.Builder;
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
@Builder
@Document(collection = "workflow-templates")
public class WorkflowTemplate {

    private final String name;
    private final String description;
    private final Map<String, WorkflowTemplateArgumentProperties> arguments;
    private final List<TaskTemplate> tasks;
    @Id
    private String id;

    public WorkflowTemplate(String name, String description, Map<String, WorkflowTemplateArgumentProperties> arguments, List<TaskTemplate> tasks) {
        this.name = name;
        this.description = description;
        arguments.forEach((key, value) -> {
            if (key.equals("projectId")) {
                throw new WorkflowTemplateConfigurationException("projectId is a reserved argument name");
            }
            if (key.equals("workflowId")) {
                throw new WorkflowTemplateConfigurationException("workflowId is a reserved argument name");
            }
        });
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
