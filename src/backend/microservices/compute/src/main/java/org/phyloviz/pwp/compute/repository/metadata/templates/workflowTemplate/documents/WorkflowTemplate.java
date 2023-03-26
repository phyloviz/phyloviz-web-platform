package org.phyloviz.pwp.compute.repository.metadata.templates.workflowTemplate.documents;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.phyloviz.pwp.compute.service.flowviz.models.workflow.Workflow;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Builder
@Document(collection = "workflow-templates")
public class WorkflowTemplate {

    @Id
    private String id;

    private final String name;
    private final String description;

    private final List<TaskTemplate> tasks;

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
