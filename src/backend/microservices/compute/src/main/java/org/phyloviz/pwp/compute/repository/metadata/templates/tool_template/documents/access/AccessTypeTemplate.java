package org.phyloviz.pwp.compute.repository.metadata.templates.tool_template.documents.access;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.compute.repository.metadata.templates.tool_template.documents.access.api.ApiAccessDetailsTemplate;
import org.phyloviz.pwp.compute.repository.metadata.templates.tool_template.documents.access.library.LibraryAccessDetailsTemplate;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.AccessType;

@RequiredArgsConstructor
public enum AccessTypeTemplate {
    LIBRARY,
    API;

    public AccessType build() {
        return AccessType.valueOf(this.name());
    }

    public Class<? extends AccessDetailsTemplate> getDetailsClass() {
        return switch (this) {
            case LIBRARY -> LibraryAccessDetailsTemplate.class;
            case API -> ApiAccessDetailsTemplate.class;
        };
    }
}


