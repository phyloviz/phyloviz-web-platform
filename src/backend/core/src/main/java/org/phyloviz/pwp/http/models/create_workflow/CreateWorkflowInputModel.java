package org.phyloviz.pwp.http.models.create_workflow;

import lombok.Data;

import java.util.Map;

@Data
public class CreateWorkflowInputModel {
    private String type;
    private Map<String, String> properties;
}
