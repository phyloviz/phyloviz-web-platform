package org.phyloviz.pwp.repository.metadata.templates.workflow_template.documents;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class WorkflowTemplateData {
    @Getter
    private final String projectId;

    @Getter
    private final String workflowId;

    private final Map<String, String> values;

    private WorkflowTemplateData(String projectId, String workflowId, Map<String, String> values) {
        this.projectId = projectId;
        this.workflowId = workflowId;
        this.values = values;
    }

    public static WorkflowTemplateDataBuilder builder() {
        return new WorkflowTemplateDataBuilder();
    }

    public Map<String, String> toMap() {
        HashMap<String, String> map = new HashMap<>(values);
        map.put("projectId", projectId);
        map.put("workflowId", workflowId);

        return map;
    }

    public void put(String key, String value) {
        values.put(key, value);
    }

    public Map<String, String> getValues() {
        return Map.copyOf(values);
    }

    public static class WorkflowTemplateDataBuilder {
        private final Map<String, String> values = new HashMap<>();
        private String projectId;
        private String workflowId;

        public WorkflowTemplateDataBuilder projectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public WorkflowTemplateDataBuilder workflowId(String workflowId) {
            this.workflowId = workflowId;
            return this;
        }

        public WorkflowTemplateDataBuilder put(String key, String value) {
            values.put(key, value);
            return this;
        }

        public WorkflowTemplateDataBuilder putAll(Map<String, String> values) {
            this.values.putAll(values);
            return this;
        }

        public WorkflowTemplateData build() {
            if (projectId == null)
                throw new IllegalStateException("ProjectId is required");

            if (workflowId == null)
                throw new IllegalStateException("WorkflowId is required");

            return new WorkflowTemplateData(projectId, workflowId, values);
        }
    }
}
