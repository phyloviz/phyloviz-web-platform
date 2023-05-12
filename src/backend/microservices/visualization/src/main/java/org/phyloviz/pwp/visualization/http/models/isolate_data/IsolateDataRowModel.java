package org.phyloviz.pwp.visualization.http.models.isolate_data;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.IsolateDataRow;

import java.util.List;

/**
 * Model for the isolate data.
 */
@Data
public class IsolateDataRowModel {
    private String id;
    private List<String> row;

    public IsolateDataRowModel(IsolateDataRow isolateDataRow) {
        this.id = isolateDataRow.getId();
        this.row = isolateDataRow.getRow().stream().toList();
    }
}
