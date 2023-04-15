package org.phyloviz.pwp.compute.http.controllers.models.createWorkflow;

import lombok.Data;

import java.util.Map;

@Data
public class CreateWorkflowInputModel {
    private String type;
    private Map<String, String> properties;
}
