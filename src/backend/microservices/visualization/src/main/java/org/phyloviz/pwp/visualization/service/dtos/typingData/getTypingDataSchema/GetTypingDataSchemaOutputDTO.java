package org.phyloviz.pwp.visualization.service.dtos.typingData.getTypingDataSchema;

import lombok.Data;

/**
 * Output DTO for the getTypingDataDetails service.
 */
@Data
public class GetTypingDataSchemaOutputDTO {
    private final String type;
    private final String[] loci;
    private final int totalCount;
}
