package org.phyloviz.pwp.repository.metadata.templates.workflow_template.documents;

import lombok.Builder;
import lombok.Data;
import org.phyloviz.pwp.service.flowviz.models.workflow.tasks.Task;

import java.util.List;

@Data
@Builder
public class TaskTemplate {

    private final String taskId;

    private final String tool;

    private final ActionTemplate action;

    private final List<String> children;

    public Task buildTask(WorkflowTemplateData workflowTemplateData) {
        return Task.builder()
                .id(taskId)
                .tool(tool + "-" + workflowTemplateData.getWorkflowId())
                .action(action.buildAction(workflowTemplateData))
                .children(this.children != null ? List.copyOf(this.children) : List.of())
                .build();
    }
}
