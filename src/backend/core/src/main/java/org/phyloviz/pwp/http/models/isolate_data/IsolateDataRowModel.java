package org.phyloviz.pwp.http.models.isolate_data;

import lombok.Data;
import org.phyloviz.pwp.service.dtos.files.isolate_data.IsolateDataRow;

import java.util.Map;

/**
 * Model for the isolate data.
 */
@Data
public class IsolateDataRowModel {
    private String id;
    private String profileId;
    private Map<String, String> row;

    public IsolateDataRowModel(IsolateDataRow isolateDataRow) {
        this.id = isolateDataRow.getId();
        this.profileId = isolateDataRow.getProfileId();
        this.row = isolateDataRow.getRow();
    }
}
