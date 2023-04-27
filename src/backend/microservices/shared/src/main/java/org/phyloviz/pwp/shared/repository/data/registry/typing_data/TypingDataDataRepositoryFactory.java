package org.phyloviz.pwp.shared.repository.data.registry.typing_data;

import org.phyloviz.pwp.shared.repository.data.registry.DataRepositoryFactory;
import org.phyloviz.pwp.shared.repository.data.typing_data.TypingDataDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.typing_data.repository.TypingDataDataRepository;
import org.phyloviz.pwp.shared.repository.data.typing_data.repository.specific_data.TypingDataDataRepositorySpecificData;
import org.springframework.stereotype.Component;

@Component
public class TypingDataDataRepositoryFactory extends
        DataRepositoryFactory<TypingDataDataRepositoryId, TypingDataDataRepository, TypingDataDataRepositorySpecificData> {
    public TypingDataDataRepositoryFactory(
            @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") TypingDataDataRepositoryRegistry dataRepositoryRegistry
    ) {
        super(dataRepositoryRegistry);
    }
}
