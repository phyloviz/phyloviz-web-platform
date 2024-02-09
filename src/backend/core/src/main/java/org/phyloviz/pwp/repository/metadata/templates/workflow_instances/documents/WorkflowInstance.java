package org.phyloviz.pwp.repository.metadata.templates.workflow_instances.documents;

import lombok.Data;
import org.phyloviz.pwp.service.flowviz.models.workflow.Workflow;
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

    private String name;

    private Workflow workflow;

    private WorkflowStatus status;

    private String failureReason;

    private Map<String, String> logs;

    private double progress;

    private Map<String, Object> data;
}
