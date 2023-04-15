package org.phyloviz.pwp.shared.service.dtos.files;

import lombok.Data;

/**
 * DTO for the isolate data.
 */
@Data
public class IsolateDataRowDTO {
    private final int id;
    private final String[] row;
}
