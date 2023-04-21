package org.phyloviz.pwp.shared.adapters.isolate_data;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.isolate_data.adapter.IsolateDataAdapter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IsolateDataAdapterFactory {

    private final IsolateDataAdapterRegistry isolateDataAdapterRegistry;

    /**
     * Returns the IsolateDataAdapter for the given adapterId.
     *
     * @param adapterId the id of the adapter
     * @return the IsolateDataAdapter
     */
    public IsolateDataAdapter getIsolateDataAdapter(IsolateDataAdapterId adapterId) {
        return isolateDataAdapterRegistry.getIsolateDataAdapter(adapterId);
    }
}
