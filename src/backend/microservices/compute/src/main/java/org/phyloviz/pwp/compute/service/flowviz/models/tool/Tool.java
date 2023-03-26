package org.phyloviz.pwp.compute.service.flowviz.models.tool;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.Access;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.library.Library;

@Data
@Builder
public class Tool {

    private final General general;

    private final Access access;

    private final List<Library> library;

}
