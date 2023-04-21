package org.phyloviz.pwp.compute.repository.metadata.templates.workflow_template.documents;

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

        return Action.builder()
                .command(sub.replace(this.command))
                .build();
    }
}
