package org.phyloviz.pwp.visualization.http.controllers.models.isolateData.getIsolateDataRows;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.files.GetIsolateDataRowsOutput;
import org.phyloviz.pwp.visualization.http.controllers.models.isolateData.IsolateDataRowModel;

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
