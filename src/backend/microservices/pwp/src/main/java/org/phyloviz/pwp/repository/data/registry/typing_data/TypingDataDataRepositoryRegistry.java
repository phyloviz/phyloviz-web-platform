package org.phyloviz.pwp.repository.data.registry.typing_data;

import org.phyloviz.pwp.repository.data.registry.DataRepositoryRegistry;
import org.phyloviz.pwp.repository.data.typing_data.TypingDataDataRepositoryId;
import org.phyloviz.pwp.repository.data.typing_data.repository.TypingDataDataRepository;
import org.phyloviz.pwp.repository.data.typing_data.repository.specific_data.TypingDataDataRepositorySpecificData;

public interface TypingDataDataRepositoryRegistry extends
        DataRepositoryRegistry<TypingDataDataRepositoryId, TypingDataDataRepository, TypingDataDataRepositorySpecificData> {
}
