package org.phyloviz.pwp.shared.adapters.isolateData;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IsolateDataAdapterFactory {

    private final S3IsolateDataAdapter s3IsolateDataAdapter;

    public IsolateDataAdapter getIsolateDataAdapter(String adapterId) {
        return switch (adapterId) {
            case "s3" -> s3IsolateDataAdapter;
            default -> throw new IllegalArgumentException("Unknown adapter id: " + adapterId);
        };
    }
}
