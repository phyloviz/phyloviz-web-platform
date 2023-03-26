package org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.access.api;

import lombok.Data;
import org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.access.AccessDetailsTemplate;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.Access;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.api.ApiAccessDetails;

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
