package org.phyloviz.pwp.compute.repository.metadata.templates.workflow_template.documents;

import lombok.Builder;
import lombok.Data;
import org.phyloviz.pwp.compute.service.flowviz.models.workflow.Workflow;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Data
@Builder
@Document(collection = "workflow-templates")
public class WorkflowTemplate {

    private final String name;
    private final String description;
    private final List<TaskTemplate> tasks;
    @Id
    private String id;

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
