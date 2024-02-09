package org.phyloviz.pwp.repository.data.registry.isolate_data;

import org.phyloviz.pwp.repository.data.isolate_data.IsolateDataDataRepositoryId;
import org.phyloviz.pwp.repository.data.isolate_data.repository.IsolateDataDataRepository;
import org.phyloviz.pwp.repository.data.isolate_data.repository.specific_data.IsolateDataDataRepositorySpecificData;
import org.phyloviz.pwp.repository.data.registry.DataRepositoryRegistry;

public interface IsolateDataDataRepositoryRegistry extends
        DataRepositoryRegistry<IsolateDataDataRepositoryId, IsolateDataDataRepository, IsolateDataDataRepositorySpecificData> {
}
