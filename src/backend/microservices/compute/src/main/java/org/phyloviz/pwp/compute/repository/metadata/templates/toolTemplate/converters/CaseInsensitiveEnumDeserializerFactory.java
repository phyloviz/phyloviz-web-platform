package org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.data.convert.ReadingConverter;

public class CaseInsensitiveEnumDeserializerFactory implements ConverterFactory<String, Enum> {
    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        Class<?> enumType = targetType;
        while (enumType != null && !enumType.isEnum())
            enumType = enumType.getSuperclass();

        if (enumType == null)
            throw new IllegalArgumentException("The target type " + targetType.getName() + " does not refer to an enum");

        return new CaseInsensitiveEnumDeserializer(enumType);
    }

    @ReadingConverter
    public static class CaseInsensitiveEnumDeserializer<T extends Enum<T>> implements Converter<String, Enum<T>> {
        private final Class<T> enumType;

        public CaseInsensitiveEnumDeserializer(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public Enum<T> convert(String source) {
            return Enum.valueOf(enumType, source.toUpperCase());
        }
    }
}

