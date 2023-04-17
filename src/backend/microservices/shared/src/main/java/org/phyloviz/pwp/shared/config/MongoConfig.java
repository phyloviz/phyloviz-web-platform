package org.phyloviz.pwp.shared.config;

import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.deserializer.DistanceMatrixMetadataDeserializer;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.converters.IsolateDataMetadataDeserializer;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.converters.IsolateDataMetadataSerializer;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.deserializer.TreeMetadataDeserializer;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.deserializer.TreeViewMetadataDeserializer;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.documents.converters.TypingDataMetadataDeserializer;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.documents.converters.TypingDataMetadataSerializer;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@Configuration
@EnableMongoRepositories(basePackages = "org.phyloviz.pwp")
public class MongoConfig {

    @Bean
    public MappingMongoConverter customMappingMongoConverter(
            @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") MongoDatabaseFactory factory,
            @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") MongoMappingContext context,
            BeanFactory beanFactory
    ) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);
        mappingConverter.setCustomConversions(mongoCustomConversions(mappingConverter));
        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return mappingConverter;
    }

    public MongoCustomConversions mongoCustomConversions(MongoConverter mongoConverter) {
        List<Converter<?, ?>> converters = List.of(
                new TypingDataMetadataDeserializer(mongoConverter),
                new TypingDataMetadataSerializer(mongoConverter),
                new IsolateDataMetadataDeserializer(mongoConverter),
                new IsolateDataMetadataSerializer(mongoConverter),
                new DistanceMatrixMetadataDeserializer(mongoConverter),
                new TreeMetadataDeserializer(mongoConverter),
                new TreeViewMetadataDeserializer(mongoConverter)
        );

        /*return new MongoCustomConversions(
                new Reflections("org.phyloviz.pwp").getTypesAnnotatedWith(ReadingConverter.class).stream()
                        .map(clazz -> {
                            try {
                                return clazz.getDeclaredConstructor().newInstance(mongoConverter);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .toList()
        );*/

        return new MongoCustomConversions(converters);
    }
}
