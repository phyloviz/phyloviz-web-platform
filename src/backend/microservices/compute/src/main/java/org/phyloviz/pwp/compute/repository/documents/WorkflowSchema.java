package org.phyloviz.pwp.compute.repository.documents;

import com.google.gson.annotations.SerializedName;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.phyloviz.pwp.compute.service.flowviz.models.workflow.tasks.Task;
import org.springframework.data.annotation.Id;


@Data
@Builder
public class WorkflowSchema {

    @Id
    private String id;

    private final String name;
    private final String description;

    @SerializedName("start_date")
    private final LocalDateTime startDate;

    @SerializedName("end_date")
    private final LocalDateTime endDate;

    private final List<Task> tasks;
}
