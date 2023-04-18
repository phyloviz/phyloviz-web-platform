package org.phyloviz.pwp.shared.adapters.isolate_data;

import org.phyloviz.pwp.shared.adapters.isolate_data.adapter.IsolateDataAdapter;
import org.phyloviz.pwp.shared.adapters.isolate_data.adapter.specific_data.IsolateDataAdapterSpecificData;


public interface IsolateDataAdapterRegistry {

    IsolateDataAdapter getIsolateDataAdapter(IsolateDataAdapterId adapterId);

    Class<? extends IsolateDataAdapterSpecificData> getIsolateDataAdapterSpecificDataClass(IsolateDataAdapterId adapterId);
}
