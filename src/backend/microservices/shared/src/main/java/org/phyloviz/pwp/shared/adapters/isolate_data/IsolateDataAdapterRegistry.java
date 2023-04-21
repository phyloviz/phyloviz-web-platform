package org.phyloviz.pwp.shared.adapters.isolate_data;

import org.phyloviz.pwp.shared.adapters.isolate_data.adapter.IsolateDataAdapter;
import org.phyloviz.pwp.shared.adapters.isolate_data.adapter.specific_data.IsolateDataAdapterSpecificData;

public interface IsolateDataAdapterRegistry {

    /**
     * Returns the IsolateDataAdapter for the given adapterId.
     *
     * @param adapterId the id of the adapter
     * @return the IsolateDataAdapter
     */
    IsolateDataAdapter getIsolateDataAdapter(IsolateDataAdapterId adapterId);

    /**
     * Returns the IsolateDataAdapterSpecificData class for the given adapterId.
     *
     * @param adapterId the id of the adapter
     * @return the IsolateDataAdapterSpecificData class
     */
    Class<? extends IsolateDataAdapterSpecificData> getIsolateDataAdapterSpecificDataClass(IsolateDataAdapterId adapterId);
}
