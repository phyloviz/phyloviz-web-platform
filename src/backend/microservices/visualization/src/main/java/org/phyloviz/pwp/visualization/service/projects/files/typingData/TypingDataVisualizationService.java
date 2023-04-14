package org.phyloviz.pwp.visualization.service.projects.files.typingData;

import org.phyloviz.pwp.visualization.service.dtos.typingData.getTypingDataProfiles.GetTypingDataProfilesInputDTO;
import org.phyloviz.pwp.visualization.service.dtos.typingData.getTypingDataProfiles.GetTypingDataProfilesOutputDTO;
import org.phyloviz.pwp.visualization.service.dtos.typingData.getTypingDataSchema.GetTypingDataSchemaInputDTO;
import org.phyloviz.pwp.visualization.service.dtos.typingData.getTypingDataSchema.GetTypingDataSchemaOutputDTO;

public interface TypingDataVisualizationService {

    /**
     * Gets a typing data's schema, given its id.
     *
     * @param getTypingDataSchemaInputDTO the input DTO
     * @return the typing data schema
     */
    GetTypingDataSchemaOutputDTO getTypingDataSchema(GetTypingDataSchemaInputDTO getTypingDataSchemaInputDTO);

    /**
     * Gets a typing data's profiles, given its id, with pagination.
     *
     * @param getTypingDataProfilesInputDTO the input DTO
     * @return the typing data profiles
     */
    GetTypingDataProfilesOutputDTO getTypingDataProfiles(GetTypingDataProfilesInputDTO getTypingDataProfilesInputDTO);
}
