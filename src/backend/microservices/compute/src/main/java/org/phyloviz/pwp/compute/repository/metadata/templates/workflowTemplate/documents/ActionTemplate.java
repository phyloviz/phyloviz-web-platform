package org.phyloviz.pwp.compute.repository.metadata.templates.workflowTemplate.documents;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.text.StringSubstitutor;
import org.phyloviz.pwp.compute.service.flowviz.models.workflow.tasks.Action;

@Data
@Builder
public class ActionTemplate {
    private final String command;

    public Action buildAction(WorkflowTemplateData workflowTemplateData) {
        StringSubstitutor sub = new StringSubstitutor(workflowTemplateData.toMap())
                .setEnableUndefinedVariableException(true);

        String command = sub.replace(this.command);

        return Action.builder()
                .command(command)
                .build();
    }
}
