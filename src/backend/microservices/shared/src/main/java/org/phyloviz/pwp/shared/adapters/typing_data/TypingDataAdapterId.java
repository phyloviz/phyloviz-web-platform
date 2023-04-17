package org.phyloviz.pwp.shared.adapters.typing_data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.typing_data.adapter.PhyloDBTypingDataAdapter;
import org.phyloviz.pwp.shared.adapters.typing_data.adapter.S3TypingDataAdapter;
import org.phyloviz.pwp.shared.adapters.typing_data.adapter.TypingDataAdapter;
import org.phyloviz.pwp.shared.adapters.typing_data.adapter.specific_data.TypingDataAdapterSpecificData;
import org.phyloviz.pwp.shared.adapters.typing_data.adapter.specific_data.TypingDataPhyloDBAdapterSpecificData;
import org.phyloviz.pwp.shared.adapters.typing_data.adapter.specific_data.TypingDataS3AdapterSpecificData;

@RequiredArgsConstructor
@Getter
public enum TypingDataAdapterId {
    S3(S3TypingDataAdapter.class, TypingDataS3AdapterSpecificData.class),
    PHYLODB(PhyloDBTypingDataAdapter.class, TypingDataPhyloDBAdapterSpecificData.class);

    private final Class<? extends TypingDataAdapter> adapterClass;
    private final Class<? extends TypingDataAdapterSpecificData> adapterSpecificDataClass;
}
