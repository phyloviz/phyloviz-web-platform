package org.phyloviz.pwp.shared.repository.metadata;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

/**
 * Metadata of the uploaded file.
 */
@Data
public abstract class Metadata {
    @Id
    private String id;

    private final String projectId;
    private final String resourceId;
    private final String url;
    private final String adapterId;

    /**
     * Additional info useful for the adapter that is going to read.
     */
    private final Object adapterSpecificData;
}