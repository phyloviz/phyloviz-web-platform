package org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.access;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.access.api.ApiAccessDetailsTemplate;
import org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.access.library.LibraryAccessDetailsTemplate;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.AccessType;

@RequiredArgsConstructor
public enum AccessTypeTemplate {
    LIBRARY,
    API;

    public AccessType build() {
        return AccessType.valueOf(this.name());
    }

    public static AccessTypeTemplate parse(String value) {
        return switch (value) {
            case "library" -> LIBRARY;
            case "api" -> API;
            default -> throw new IllegalArgumentException("Unknown access type: " + value);
        };
    }

    public Class<? extends AccessDetailsTemplate> getDetailsClass() {
        return switch (this) {
            case LIBRARY -> LibraryAccessDetailsTemplate.class;
            case API -> ApiAccessDetailsTemplate.class;
        };
    }
}


