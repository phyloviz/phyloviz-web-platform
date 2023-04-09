package org.phyloviz.pwp.compute.service.flowviz.adapters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.Access;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.AccessDetails;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.api.ApiAccessDetails;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.library.LibraryAccessDetails;

import java.lang.reflect.Type;

public class AccessSerializer implements JsonSerializer<Access> {
    @Override
    public JsonElement serialize(Access access, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.addProperty("_type", access.getType().toString().toLowerCase());
        AccessDetails details = access.getDetails();

        if (details instanceof LibraryAccessDetails) {
            json.add("library", context.serialize(details, LibraryAccessDetails.class));
        } else if (details instanceof ApiAccessDetails) {
            json.add("api", context.serialize(details, ApiAccessDetails.class));
        } else {
            throw new IllegalArgumentException("Unknown access details type: " + details.getClass().getName());
        }

        return json;
    }
}
