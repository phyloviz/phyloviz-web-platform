package org.phyloviz.pwp.shared.service.dtos.files.typing_data;

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
