package org.phyloviz.pwp.visualization.http.models.typing_data.get_typing_data_schema;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.GetTypingDataSchemaOutput;

import java.util.List;

/**
 * Output model for the get typing data profiles endpoint.
 */
@Data
public class GetTypingDataSchemaOutputModel {
    private final String type;
    private final List<String> loci;

    public GetTypingDataSchemaOutputModel(GetTypingDataSchemaOutput getTypingDataSchemaOutput) {
        this.type = getTypingDataSchemaOutput.getType();
        this.loci = getTypingDataSchemaOutput.getLoci();
    }
}
