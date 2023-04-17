package org.phyloviz.pwp.shared.service.adapters.isolateData;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.adapterSpecificData.IsolateDataAdapterId;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IsolateDataAdapterFactory {

    private final IsolateDataAdapterRegistry isolateDataAdapterRegistry;

    public IsolateDataAdapter getIsolateDataAdapter(IsolateDataAdapterId adapterId) {
        return isolateDataAdapterRegistry.getIsolateDataAdapter(adapterId);
    }
}
