package org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.converters;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;
import org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.access.AccessDetailsTemplate;
import org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.access.AccessTemplate;
import org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.access.AccessTypeTemplate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

@ReadingConverter
@RequiredArgsConstructor
public class AccessTemplateDeserializer implements Converter<Document, AccessTemplate> {
    private final MongoConverter mongoConverter;

    @Override
    public AccessTemplate convert(@NotNull Document document) {
        try {
            AccessTypeTemplate type = AccessTypeTemplate.parse(document.getString("_type"));

            Class<? extends AccessDetailsTemplate> detailsClass = type.getDetailsClass();
            Document detailsDocument = (Document) document.get("details");
            AccessDetailsTemplate details = mongoConverter.read(detailsClass, detailsDocument);

            return AccessTemplate.builder()
                    .type(type)
                    .details(details)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Error converting Document to AccessTemplate", e);
        }
    }
}

