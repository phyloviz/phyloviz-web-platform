package org.phyloviz.pwp.shared.service.adapters.typingData;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.adapterSpecificData.TypingDataAdapterId;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TypingDataAdapterFactory {

    private final S3TypingDataAdapter s3TypingDataAdapter;

    public TypingDataAdapter getTypingDataAdapter(TypingDataAdapterId adapterId) {
        return switch (adapterId) {
            case S3 -> s3TypingDataAdapter;
            case PHYLODB -> throw new UnsupportedOperationException("Not implemented yet");
        };
    }
}
