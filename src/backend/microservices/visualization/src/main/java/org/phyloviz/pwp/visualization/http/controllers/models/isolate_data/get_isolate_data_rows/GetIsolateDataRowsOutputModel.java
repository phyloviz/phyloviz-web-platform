package org.phyloviz.pwp.visualization.http.controllers.models.isolate_data.get_isolate_data_rows;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.GetIsolateDataRowsOutput;
import org.phyloviz.pwp.visualization.http.controllers.models.isolate_data.IsolateDataRowModel;

import java.util.List;

@Data
public class GetIsolateDataRowsOutputModel {
    private List<IsolateDataRowModel> rows;
    private int totalCount;

    public GetIsolateDataRowsOutputModel(GetIsolateDataRowsOutput getIsolateDataRowsOutput) {
        this.rows = getIsolateDataRowsOutput.getRows().stream().map(IsolateDataRowModel::new).toList();
        this.totalCount = getIsolateDataRowsOutput.getTotalCount();
    }
}
