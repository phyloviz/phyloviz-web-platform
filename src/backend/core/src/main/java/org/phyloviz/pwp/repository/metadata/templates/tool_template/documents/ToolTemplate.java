package org.phyloviz.pwp.repository.metadata.templates.tool_template.documents;


import lombok.Builder;
import lombok.Data;
import org.phyloviz.pwp.repository.metadata.templates.tool_template.documents.access.AccessTemplate;
import org.phyloviz.pwp.repository.metadata.templates.tool_template.documents.library.LibraryTemplate;
import org.phyloviz.pwp.service.flowviz.models.tool.Tool;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "tool-templates")
public class ToolTemplate {
    // TODO: Maybe remove this duplicated GeneralTemplate/General code? doing this so there's no coupling between the two
    private final GeneralTemplate general;
    private final AccessTemplate access;
    private final List<LibraryTemplate> library;
    @Id
    private String id;

    public Tool buildApiTool(ToolTemplateData data) {
        return Tool.builder()
                .general(general.build(data))
                .access(access.build())
                .library(library.stream().map(LibraryTemplate::build).toList())
                .build();
    }

    public Tool buildLibraryTool(ToolTemplateData data) {
        return Tool.builder()
                .general(general.build(data))
                .access(access.build(data))
                .library(library.stream().map(LibraryTemplate::build).toList())
                .build();
    }

}
