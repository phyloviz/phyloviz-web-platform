package org.phyloviz.pwp.shared.adapters.typing_data;

import org.phyloviz.pwp.shared.adapters.typing_data.adapter.TypingDataAdapter;
import org.phyloviz.pwp.shared.adapters.typing_data.adapter.specific_data.TypingDataAdapterSpecificData;

public interface TypingDataAdapterRegistry {

    /**
     * Returns the TypingDataAdapter for the given adapterId.
     *
     * @param adapterId the id of the adapter
     * @return the TypingDataAdapter
     */
    TypingDataAdapter getTypingDataAdapter(TypingDataAdapterId adapterId);

    /**
     * Returns the TypingDataAdapterSpecificData class for the given adapterId.
     *
     * @param adapterId the id of the adapter
     * @return the TypingDataAdapterSpecificData class
     */
    Class<? extends TypingDataAdapterSpecificData> getTypingDataAdapterSpecificDataClass(TypingDataAdapterId adapterId);
}
