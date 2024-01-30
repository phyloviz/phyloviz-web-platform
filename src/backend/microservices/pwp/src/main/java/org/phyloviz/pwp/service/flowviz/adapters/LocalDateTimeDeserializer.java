package org.phyloviz.pwp.service.flowviz.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * Deserializes {@link LocalDateTime} objects from JSON.
 */
public class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(
            JsonElement json,
            Type type,
            JsonDeserializationContext jsonDeserializationContext
    ) {
        return ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()).toLocalDateTime();
    }
}
