package org.phyloviz.pwp.service.dtos.files.typing_data;

import lombok.Data;

import java.util.List;

/**
 * Output for the getTypingDataDetails service.
 */
@Data
public class GetTypingDataSchemaOutput {
    private final String type;
    private final List<String> loci;
}
