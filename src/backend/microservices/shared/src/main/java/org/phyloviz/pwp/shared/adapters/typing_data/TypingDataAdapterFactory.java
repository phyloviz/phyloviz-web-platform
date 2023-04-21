package org.phyloviz.pwp.shared.adapters.typing_data;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.typing_data.adapter.TypingDataAdapter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TypingDataAdapterFactory {

    private final TypingDataAdapterRegistry typingDataAdapterRegistry;

    /**
     * Returns the TypingDataAdapter for the given adapterId.
     *
     * @param adapterId the id of the adapter
     * @return the TypingDataAdapter
     */
    public TypingDataAdapter getTypingDataAdapter(TypingDataAdapterId adapterId) {
        return typingDataAdapterRegistry.getTypingDataAdapter(adapterId);
    }
}
