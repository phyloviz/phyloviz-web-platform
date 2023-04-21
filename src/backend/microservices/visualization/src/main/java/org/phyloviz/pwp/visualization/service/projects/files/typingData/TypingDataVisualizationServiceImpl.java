package org.phyloviz.pwp.visualization.service.projects.files.typingData;

import org.phyloviz.pwp.visualization.service.dtos.typingData.getTypingDataProfiles.GetTypingDataProfilesInputDTO;
import org.phyloviz.pwp.visualization.service.dtos.typingData.getTypingDataProfiles.GetTypingDataProfilesOutputDTO;
import org.phyloviz.pwp.visualization.service.dtos.typingData.getTypingDataSchema.GetTypingDataSchemaInputDTO;
import org.phyloviz.pwp.visualization.service.dtos.typingData.getTypingDataSchema.GetTypingDataSchemaOutputDTO;
import org.springframework.stereotype.Service;

@Service
public class TypingDataVisualizationServiceImpl implements TypingDataVisualizationService {
    @Override
    public GetTypingDataSchemaOutputDTO getTypingDataSchema(GetTypingDataSchemaInputDTO getTypingDataSchemaInputDTO) {
        return null;
    }

    @Override
    public GetTypingDataProfilesOutputDTO getTypingDataProfiles(GetTypingDataProfilesInputDTO getTypingDataProfilesInputDTO) {
        return null;
    }
}
