package org.phyloviz.pwp.shared.adapters.typing_data;

import org.phyloviz.pwp.shared.adapters.typing_data.adapter.TypingDataAdapter;
import org.phyloviz.pwp.shared.adapters.typing_data.adapter.specific_data.TypingDataAdapterSpecificData;


public interface TypingDataAdapterRegistry {

    TypingDataAdapter getTypingDataAdapter(TypingDataAdapterId adapterId);

    Class<? extends TypingDataAdapterSpecificData> getTypingDataAdapterSpecificDataClass(TypingDataAdapterId adapterId);
}
