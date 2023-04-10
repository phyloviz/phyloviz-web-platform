package org.phyloviz.pwp.visualization.http.controllers.models.isolateData.getIsolateDataRows;

import lombok.Data;
import org.phyloviz.pwp.visualization.http.controllers.models.isolateData.IsolateDataRowModel;
import org.phyloviz.pwp.visualization.service.dtos.isolateData.getIsolateDataRows.GetIsolateDataRowsOutputDTO;

import java.util.List;

@Data
public class GetIsolateDataRowsOutputModel {
    private List<IsolateDataRowModel> rows;
    private int totalCount;

    public GetIsolateDataRowsOutputModel(GetIsolateDataRowsOutputDTO getIsolateDataRowsOutputDTO) {
        this.rows = getIsolateDataRowsOutputDTO.getRows().stream().map(IsolateDataRowModel::new).toList();
        this.totalCount = getIsolateDataRowsOutputDTO.getTotalCount();
    }
}
