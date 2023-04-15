package org.phyloviz.pwp.shared.service.dtos.files;

import lombok.Data;

/**
 * Output DTO for the getTypingDataDetails service.
 */
@Data
public class GetTypingDataSchemaOutput {
    private final String type;
    private final String[] loci;
    private final int totalCount;
}
