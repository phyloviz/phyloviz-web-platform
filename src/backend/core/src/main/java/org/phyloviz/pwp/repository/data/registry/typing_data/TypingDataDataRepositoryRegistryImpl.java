package org.phyloviz.pwp.repository.data.registry.typing_data;

import org.phyloviz.pwp.repository.data.registry.AbstractDataRepositoryRegistry;
import org.phyloviz.pwp.repository.data.typing_data.TypingDataDataRepositoryId;
import org.phyloviz.pwp.repository.data.typing_data.repository.TypingDataDataRepository;
import org.phyloviz.pwp.repository.data.typing_data.repository.specific_data.TypingDataDataRepositorySpecificData;
import org.springframework.context.ApplicationContext;

import java.util.Map;

public class TypingDataDataRepositoryRegistryImpl
        extends AbstractDataRepositoryRegistry<TypingDataDataRepositoryId, TypingDataDataRepository, TypingDataDataRepositorySpecificData>
        implements TypingDataDataRepositoryRegistry {
    public TypingDataDataRepositoryRegistryImpl(
            ApplicationContext context,
            Map<TypingDataDataRepositoryId, Class<? extends TypingDataDataRepository>> repositoryClasses,
            Map<TypingDataDataRepositoryId, Class<? extends TypingDataDataRepositorySpecificData>> repositorySpecificDataClasses
    ) {
        super(context, TypingDataDataRepositoryId.class, repositoryClasses, repositorySpecificDataClasses);
    }
}