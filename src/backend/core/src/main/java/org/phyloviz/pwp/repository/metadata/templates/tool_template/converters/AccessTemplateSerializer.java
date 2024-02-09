package org.phyloviz.pwp.repository.metadata.templates.tool_template.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;
import org.phyloviz.pwp.repository.metadata.templates.tool_template.documents.access.AccessDetailsTemplate;
import org.phyloviz.pwp.repository.metadata.templates.tool_template.documents.access.AccessTemplate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;


@WritingConverter
public class AccessTemplateSerializer implements Converter<AccessTemplate, Document> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public Document convert(@NotNull AccessTemplate accessTemplate) {
        JsonObject json = new JsonObject();
        json.addProperty("_type", accessTemplate.getType().toString());
        AccessDetailsTemplate details = accessTemplate.getDetails();

        json.addProperty("details", objectMapper.writeValueAsString(details));

        return Document.parse(json.toString());
    }
}
