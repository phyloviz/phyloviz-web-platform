package org.phyloviz.pwp.shared.service.adapters.typingData;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.adapterSpecificData.TypingDataAdapterId;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TypingDataAdapterFactory {

    private final TypingDataAdapterRegistry typingDataAdapterRegistry;

    public TypingDataAdapter getTypingDataAdapter(TypingDataAdapterId adapterId) {
        return typingDataAdapterRegistry.getTypingDataAdapter(adapterId);
    }
}
