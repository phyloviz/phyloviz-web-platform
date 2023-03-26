package org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents;


import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.access.AccessDetailsTemplate;
import org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.access.AccessTemplate;
import org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.library.LibraryTemplate;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.Tool;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "tool-templates")
public class ToolTemplate {
    @Id
    private String id;

    // TODO: Maybe remove this duplicated GeneralTemplate/General code? doing this so there's no coupling between the two
    private final GeneralTemplate general;

    private final AccessTemplate access;

    private final List<LibraryTemplate> library;

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
