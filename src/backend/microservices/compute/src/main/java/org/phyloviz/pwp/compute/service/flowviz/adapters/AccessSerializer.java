package org.phyloviz.pwp.compute.service.flowviz.adapters;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.Access;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.AccessDetails;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.api.ApiAccess;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.library.LibraryAccess;
import org.phyloviz.pwp.compute.service.flowviz.models.workflow.tasks.Task;

public class AccessSerializer implements JsonSerializer<Access> {
    @Override
    public JsonElement serialize(Access access, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.addProperty("_type", access.getType().toString());
        AccessDetails details = access.getDetails();

        if (details instanceof LibraryAccess) {
            json.add("library", context.serialize(details, AccessDetails.class));
        } else if (details instanceof ApiAccess) {
            json.add("api", context.serialize(details, AccessDetails.class));
        } else {
            throw new IllegalArgumentException("Unknown access details type: " + details.getClass().getName());
        }

        return json;
    }
}
