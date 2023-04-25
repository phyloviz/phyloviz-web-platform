package org.phyloviz.pwp.compute.repository.metadata.templates.workflow_instances.documents;

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

    private String projectId;

    private String type;

    private Workflow workflow;

    private WorkflowStatus status;

    private Map<String, Object> data;
}
