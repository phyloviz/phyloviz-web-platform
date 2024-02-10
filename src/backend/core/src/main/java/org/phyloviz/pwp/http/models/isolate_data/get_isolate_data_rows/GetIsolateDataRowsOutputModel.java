package org.phyloviz.pwp.http.models.isolate_data.get_isolate_data_rows;

import lombok.Data;
import org.phyloviz.pwp.http.models.isolate_data.IsolateDataRowModel;
import org.phyloviz.pwp.service.dtos.files.isolate_data.GetIsolateDataRowsOutput;

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
