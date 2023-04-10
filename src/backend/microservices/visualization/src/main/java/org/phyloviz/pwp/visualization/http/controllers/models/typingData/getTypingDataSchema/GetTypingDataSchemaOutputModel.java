package org.phyloviz.pwp.visualization.http.controllers.models.typingData.getTypingDataSchema;

import lombok.Data;
import org.phyloviz.pwp.visualization.service.dtos.typingData.getTypingDataSchema.GetTypingDataSchemaOutputDTO;

/**
 * Output model for the get typing data profiles endpoint.
 */
@Data
public class GetTypingDataSchemaOutputModel {
    private final String type;
    private final String[] loci;
    private final int totalCount;

    public GetTypingDataSchemaOutputModel(GetTypingDataSchemaOutputDTO getTypingDataSchemaOutputDTO) {
        this.type = getTypingDataSchemaOutputDTO.getType();
        this.loci = getTypingDataSchemaOutputDTO.getLoci();
        this.totalCount = getTypingDataSchemaOutputDTO.getTotalCount();
    }
}
