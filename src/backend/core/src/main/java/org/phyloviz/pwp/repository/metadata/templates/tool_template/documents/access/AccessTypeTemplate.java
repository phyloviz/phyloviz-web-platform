package org.phyloviz.pwp.repository.metadata.templates.tool_template.documents.access;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.repository.metadata.templates.tool_template.documents.access.api.ApiAccessDetailsTemplate;
import org.phyloviz.pwp.repository.metadata.templates.tool_template.documents.access.library.LibraryAccessDetailsTemplate;
import org.phyloviz.pwp.service.flowviz.models.tool.access.AccessType;

@RequiredArgsConstructor
public enum AccessTypeTemplate {
    LIBRARY(LibraryAccessDetailsTemplate.class),
    API(ApiAccessDetailsTemplate.class);

    @Getter
    private final Class<? extends AccessDetailsTemplate> detailsClass;

    public AccessType build() {
        return AccessType.valueOf(this.name());
    }
}


