package org.phyloviz.pwp.compute.service.flowviz.models.get_workflow;

import com.google.gson.annotations.SerializedName;
import lombok.Data;


@Data
public class AirflowWorkflowStatus {

    @SerializedName("dag_run_id")
    private final String dagRunId;
    private final String state;
}
