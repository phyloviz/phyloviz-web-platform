package org.phyloviz.pwp.shared.adapters.isolate_data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.isolate_data.adapter.IsolateDataAdapter;
import org.phyloviz.pwp.shared.adapters.isolate_data.adapter.S3IsolateDataAdapter;
import org.phyloviz.pwp.shared.adapters.isolate_data.adapter.specific_data.IsolateDataAdapterSpecificData;
import org.phyloviz.pwp.shared.adapters.isolate_data.adapter.specific_data.IsolateDataS3AdapterSpecificData;

@RequiredArgsConstructor
@Getter
public enum IsolateDataAdapterId {
    S3(S3IsolateDataAdapter.class, IsolateDataS3AdapterSpecificData.class);

    private final Class<? extends IsolateDataAdapter> adapterClass;
    private final Class<? extends IsolateDataAdapterSpecificData> adapterSpecificDataClass;
}
