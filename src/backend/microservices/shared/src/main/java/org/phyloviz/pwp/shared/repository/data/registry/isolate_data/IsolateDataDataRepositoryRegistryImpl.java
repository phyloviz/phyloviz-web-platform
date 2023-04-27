package org.phyloviz.pwp.shared.repository.data.registry.isolate_data;

import org.phyloviz.pwp.shared.repository.data.isolate_data.IsolateDataDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.IsolateDataDataRepository;
import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.specific_data.IsolateDataDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.registry.AbstractDataRepositoryRegistry;
import org.springframework.context.ApplicationContext;

import java.util.Map;

public class IsolateDataDataRepositoryRegistryImpl
        extends AbstractDataRepositoryRegistry<IsolateDataDataRepositoryId, IsolateDataDataRepository, IsolateDataDataRepositorySpecificData>
        implements IsolateDataDataRepositoryRegistry {
    public IsolateDataDataRepositoryRegistryImpl(
            ApplicationContext context,
            Map<IsolateDataDataRepositoryId, Class<? extends IsolateDataDataRepository>> repositoryClasses,
            Map<IsolateDataDataRepositoryId, Class<? extends IsolateDataDataRepositorySpecificData>> repositorySpecificDataClasses
    ) {
        super(context, IsolateDataDataRepositoryId.class, repositoryClasses, repositorySpecificDataClasses);
    }
}