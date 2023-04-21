package org.phyloviz.pwp.compute.repository.metadata.templates.tool_template.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class CaseInsensitiveEnumSerializer implements Converter<Enum<?>, String> {
    @Override
    public String convert(Enum<?> source) {
        return source.name().toLowerCase();
    }
}
