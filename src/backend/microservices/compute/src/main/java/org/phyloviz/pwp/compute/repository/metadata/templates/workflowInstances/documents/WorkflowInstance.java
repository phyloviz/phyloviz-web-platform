package org.phyloviz.pwp.compute.repository.metadata.templates.workflowInstances.documents;

import lombok.Data;
import org.phyloviz.pwp.compute.service.flowviz.models.workflow.Workflow;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document(collection = "workflow-instances")
public class WorkflowInstance {
    @Id
    private String id;

    private Workflow workflow;

    private Map<String, Object> results;
}
