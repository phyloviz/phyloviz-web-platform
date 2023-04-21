package org.phyloviz.pwp.compute.service.flowviz.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.Access;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.AccessDetails;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.AccessType;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.api.ApiAccessDetails;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.library.LibraryAccessDetails;

import java.lang.reflect.Type;
import java.util.Map;

// TODO: Comment this class
public class AccessDeserializer implements JsonDeserializer<Access> {
    @Override
    public Access deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context
    ) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        AccessType type = null;
        AccessDetails details = null;

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String name = entry.getKey();

            switch (name) {
                case "_type" -> type = AccessType.valueOf(entry.getValue().getAsString().toUpperCase());
                case "api" -> details = context.deserialize(entry.getValue(), ApiAccessDetails.class);
                case "library" -> details = context.deserialize(entry.getValue(), LibraryAccessDetails.class);
                default -> throw new JsonParseException("Unknown access field: " + name);
            }
        }

        if (type == null)
            throw new JsonParseException("Access type is missing");

        if (details == null)
            throw new JsonParseException("Access details are missing");

        if (type == AccessType.LIBRARY && !(details instanceof LibraryAccessDetails))
            throw new JsonParseException(
                    "Access details are not of type LibraryAccessDetails even though type is LIBRARY"
            );

        if (type == AccessType.API && !(details instanceof ApiAccessDetails))
            throw new JsonParseException("Access details are not of type ApiAccessDetails even though type is API");

        return Access.builder()
                .type(type)
                .details(details)
                .build();
    }
}


