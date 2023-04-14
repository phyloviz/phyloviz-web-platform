package org.phyloviz.pwp.compute.http.controllers.models.createWorkflow;

import java.util.Map;
import lombok.Data;

@Data
public class CreateWorkflowInputModel {
    private String type;
    private Map<String, String> properties;
}
