package org.phyloviz.pwp.visualization.http.controllers.models.isolateData;

import lombok.Data;
import org.phyloviz.pwp.visualization.service.dtos.isolateData.IsolateDataRowDTO;

/**
 * Model for the isolate data.
 */
@Data
public class IsolateDataRowModel {
    private int id;
    private String[] row;

    public IsolateDataRowModel(IsolateDataRowDTO isolateDataRowDTO) {
        this.id = isolateDataRowDTO.getId();
        this.row = isolateDataRowDTO.getRow();
    }
}
