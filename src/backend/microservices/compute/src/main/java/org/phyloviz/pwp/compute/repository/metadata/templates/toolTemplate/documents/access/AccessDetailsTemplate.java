package org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.access;

import org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.ToolTemplateData;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.Access;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.AccessDetails;

public interface AccessDetailsTemplate {
    AccessDetails build();
}
