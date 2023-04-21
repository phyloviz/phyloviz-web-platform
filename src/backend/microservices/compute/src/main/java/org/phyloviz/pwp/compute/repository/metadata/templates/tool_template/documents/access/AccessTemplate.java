package org.phyloviz.pwp.compute.repository.metadata.templates.tool_template.documents.access;

import lombok.Builder;
import lombok.Data;
import org.phyloviz.pwp.compute.repository.metadata.templates.tool_template.documents.ToolTemplateData;
import org.phyloviz.pwp.compute.repository.metadata.templates.tool_template.documents.access.library.LibraryAccessDetailsTemplate;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.Access;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
public class AccessTemplate {
    @Field("_type")
    private final AccessTypeTemplate type;

    private final AccessDetailsTemplate details;

    public Access build(ToolTemplateData data) {
        if (!(details instanceof LibraryAccessDetailsTemplate libraryDetails))
            throw new IllegalArgumentException("Details must be of type LibraryAccessTemplate to instantiate template with volumes");

        return Access.builder()
                .type(type.build())
                .details(libraryDetails.build(data))
                .build();
    }

    public Access build() {
        return Access.builder()
                .type(type.build())
                .details(details.build())
                .build();
    }

}
