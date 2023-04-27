package org.phyloviz.pwp.visualization.http.models.isolate_data;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.IsolateDataRow;

/**
 * Model for the isolate data.
 */
@Data
public class IsolateDataRowModel {
    private int id;
    private String[] row;

    public IsolateDataRowModel(IsolateDataRow isolateDataRow) {
        this.id = isolateDataRow.getId();
        this.row = isolateDataRow.getRow();
    }
}
