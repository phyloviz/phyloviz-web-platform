package org.phyloviz.pwp.shared.adapters.typingData;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TypingDataAdapterFactory {

    private final S3TypingDataAdapter s3TypingDataAdapter;

    public TypingDataAdapter getTypingDataAdapter(String adapterId) {
        return switch (adapterId) {
            case "s3" -> s3TypingDataAdapter;
            default -> throw new IllegalArgumentException("Unknown adapter id: " + adapterId);
        };
    }
}
