package org.phyloviz.pwp.compute.repository.metadata.templates.tool_template.documents;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class ToolTemplateData {
    @Getter
    private final String projectId;

    @Getter
    private final String workflowId;

    private final Map<String, String> values;

    private ToolTemplateData(String projectId, String workflowId, Map<String, String> values) {
        this.projectId = projectId;
        this.workflowId = workflowId;
        this.values = values;
    }

    public static ToolTemplateDataBuilder builder() {
        return new ToolTemplateDataBuilder();
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

    public static class ToolTemplateDataBuilder {
        private final Map<String, String> values = new HashMap<>();
        private String projectId;
        private String workflowId;

        public ToolTemplateDataBuilder projectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public ToolTemplateDataBuilder workflowId(String workflowId) {
            this.workflowId = workflowId;
            return this;
        }

        public ToolTemplateDataBuilder put(String key, String value) {
            values.put(key, value);
            return this;
        }

        public ToolTemplateDataBuilder putAll(Map<String, String> properties) {
            this.values.putAll(properties);
            return this;
        }

        public ToolTemplateData build() {
            if (projectId == null)
                throw new IllegalStateException("ProjectId is required");

            if (workflowId == null)
                throw new IllegalStateException("WorkflowId is required");

            return new ToolTemplateData(projectId, workflowId, values);
        }
    }
}
