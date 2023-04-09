package org.phyloviz.pwp.compute.service.flowviz.models.workflow;


import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import org.phyloviz.pwp.compute.service.flowviz.models.workflow.tasks.Task;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Workflow {

    private final String name;


    private final String description;

    @SerializedName("start_date")
    private final LocalDateTime startDate;

    private final List<Task> tasks;
}



