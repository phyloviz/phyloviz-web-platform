package org.phyloviz.pwp.repository.metadata.templates.tool_template.documents.access.api;

import lombok.Data;
import org.phyloviz.pwp.repository.metadata.templates.tool_template.documents.access.AccessDetailsTemplate;
import org.phyloviz.pwp.service.flowviz.models.tool.access.api.ApiAccessDetails;

@Data
public class ApiAccessDetailsTemplate implements AccessDetailsTemplate {

    private final String url;

    private final String apiKey;

    @Override
    public ApiAccessDetails build() {
        return ApiAccessDetails.builder()
                .url(url)
                .apiKey(apiKey)
                .build();
    }
}
