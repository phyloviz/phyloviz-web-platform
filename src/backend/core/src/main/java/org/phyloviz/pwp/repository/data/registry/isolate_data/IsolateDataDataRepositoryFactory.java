package org.phyloviz.pwp.repository.data.registry.isolate_data;

import org.phyloviz.pwp.repository.data.isolate_data.IsolateDataDataRepositoryId;
import org.phyloviz.pwp.repository.data.isolate_data.repository.IsolateDataDataRepository;
import org.phyloviz.pwp.repository.data.isolate_data.repository.specific_data.IsolateDataDataRepositorySpecificData;
import org.phyloviz.pwp.repository.data.registry.DataRepositoryFactory;
import org.springframework.stereotype.Component;

@Component
public class IsolateDataDataRepositoryFactory extends
        DataRepositoryFactory<IsolateDataDataRepositoryId, IsolateDataDataRepository, IsolateDataDataRepositorySpecificData> {
    public IsolateDataDataRepositoryFactory(
            @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") IsolateDataDataRepositoryRegistry dataRepositoryRegistry
    ) {
        super(dataRepositoryRegistry);
    }
}
