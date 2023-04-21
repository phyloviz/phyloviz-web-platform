package org.phyloviz.pwp.compute.repository.metadata.templates.workflow_template.documents;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class WorkflowTemplateData {

    @Getter
    private final String workflowId;

    private final Map<String, String> values;

    private WorkflowTemplateData(String workflowId, Map<String, String> values) {
        this.workflowId = workflowId;
        this.values = values;
    }

    public static WorkflowTemplateDataBuilder builder() {
        return new WorkflowTemplateDataBuilder();
    }

    public Map<String, String> toMap() {
        HashMap<String, String> map = new HashMap<>(values);
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
        private String workflowId;

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
            if (workflowId == null)
                throw new IllegalStateException("WorkflowId is required");

            return new WorkflowTemplateData(workflowId, values);
        }
    }
}
