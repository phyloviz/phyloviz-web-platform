package org.phyloviz.pwp.compute.repository.metadata.templates.workflow_template.documents.arguments;

import lombok.Getter;
import org.phyloviz.pwp.compute.service.exceptions.WorkflowTemplateConfigurationException;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
public class WorkflowTemplateArgumentProperties {

    public WorkflowTemplateArgumentProperties(WorkflowTemplateArgumentType type, List<String> allowedValues, ObtainExtra obtainExtra) {
        this.type = type;

        if (type == WorkflowTemplateArgumentType.STRING) {
            if (allowedValues == null || allowedValues.isEmpty()) {
                throw new WorkflowTemplateConfigurationException("Arguments of type 'string' must have allowed values");
            }
        } else if (allowedValues != null) {
            throw new WorkflowTemplateConfigurationException("Only arguments of type 'string' can have allowed values");
        }

        this.allowedValues = allowedValues;

        if (type != WorkflowTemplateArgumentType.DATASETID && obtainExtra != null) {
            throw new WorkflowTemplateConfigurationException("Only arguments of type 'datasetId' can have extra arguments to obtain");
        }
        this.obtainExtra = obtainExtra;
    }

    private final WorkflowTemplateArgumentType type;

    @Field("allowed-values")
    private final List<String> allowedValues;

    @Field("obtain-extra")
    private final ObtainExtra obtainExtra;
}
