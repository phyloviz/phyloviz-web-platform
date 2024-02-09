package org.phyloviz.pwp.repository.metadata.templates.workflow_template.documents.arguments;

import lombok.Getter;
import org.phyloviz.pwp.service.exceptions.WorkflowTemplateConfigurationException;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
public class WorkflowTemplateArgumentProperties {
    private final WorkflowTemplateArgumentType type;

    private final boolean required;

    private final String prefix;

    @Field("allowed-values")
    private final List<String> allowedValues;

    private final String pattern;

    public WorkflowTemplateArgumentProperties(WorkflowTemplateArgumentType type, Boolean required, String prefix, List<String> allowedValues,
                                              String pattern) {
        this.type = type;

        if (type == WorkflowTemplateArgumentType.STRING) {
            if (allowedValues == null || allowedValues.isEmpty()) {
                throw new WorkflowTemplateConfigurationException("Arguments of type 'string' must have allowed values");
            }
        } else if (allowedValues != null) {
            throw new WorkflowTemplateConfigurationException("Only arguments of type 'string' can have allowed values");
        }

        this.allowedValues = allowedValues;
        this.required = required == null || required;
        this.prefix = prefix;
        this.pattern = pattern;
    }
}
