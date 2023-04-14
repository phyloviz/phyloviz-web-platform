package org.phyloviz.pwp.shared.config;

import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.adapterSpecificData.DistanceMatrixAdapterSpecificDataFactory;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.deserializer.DistanceMatrixMetadataDeserializer;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.source.DistanceMatrixSourceFactory;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.adapterSpecificData.IsolateDataAdapterSpecificDataFactory;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.deserializer.IsolateDataMetadataDeserializer;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.adapterSpecificData.TreeAdapterSpecificDataFactory;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.deserializer.TreeMetadataDeserializer;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSourceFactory;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.adapterSpecificData.TreeViewAdapterSpecificDataFactory;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.deserializer.TreeViewMetadataDeserializer;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.adapterSpecificData.TypingDataAdapterSpecificDataFactory;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.deserializer.TypingDataMetadataDeserializer;
import org.reflections.Reflections;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.convert.ConfigurableTypeInformationMapper;
import org.springframework.data.convert.SimpleTypeInformationMapper;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableMongoRepositories(basePackages = "org.phyloviz.pwp")
public class MongoConfig {

    @Bean
    public MappingMongoConverter customMappingMongoConverter(
            MongoDatabaseFactory factory,
            MongoMappingContext context,
            BeanFactory beanFactory
    ) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);
        mappingConverter.setCustomConversions(mongoCustomConversions(mappingConverter));
        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        // mappingConverter.setTypeMapper(new ReflectiveMongoTypeMapper());

        return mappingConverter;
    }

    public MongoCustomConversions mongoCustomConversions(MongoConverter mongoConverter) {
        List<Converter<?, ?>> converters = List.of(
                new TypingDataMetadataDeserializer(mongoConverter, new TypingDataAdapterSpecificDataFactory()),
                new IsolateDataMetadataDeserializer(mongoConverter, new IsolateDataAdapterSpecificDataFactory()),
                new DistanceMatrixMetadataDeserializer(mongoConverter,
                        new DistanceMatrixSourceFactory(),
                        new DistanceMatrixAdapterSpecificDataFactory()),
                new TreeMetadataDeserializer(mongoConverter,
                        new TreeSourceFactory(),
                        new TreeAdapterSpecificDataFactory()),
                new TreeViewMetadataDeserializer(mongoConverter, new TreeViewAdapterSpecificDataFactory())
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

    /**
     * A custom type mapper that uses reflection to find all classes annotated with {@link TypeAlias} and
     * register them with the {@link ConfigurableTypeInformationMapper}. This allows us to use
     * {@link TypeAlias} annotations in our domain classes without having to explicitly register them
     * with the {@link ConfigurableTypeInformationMapper}.
     * <p>
     * Fix for the problem of application not maintaining type knowledge between restarts. Would always work correctly
     * within a single run of the application, caching the types of the saved documents appropriately. However, failed
     * when restarting the application, as the type information was lost.
     *
     * @see <a href="https://blog.monosoul.dev/2022/09/16/spring-data-mongodb-polymorphic-fields/">Monosoul's Dev Blog</a>
     */
    static class ReflectiveMongoTypeMapper extends DefaultMongoTypeMapper {
        public ReflectiveMongoTypeMapper() {
            this(new Reflections("org.phyloviz.pwp"));
        }

        public ReflectiveMongoTypeMapper(Reflections reflections) {
            super(DEFAULT_TYPE_KEY, List.of(
                    new ConfigurableTypeInformationMapper(
                            reflections.getTypesAnnotatedWith(TypeAlias.class).stream()
                                    .collect(Collectors.toMap(
                                            clazz -> clazz,
                                            clazz -> clazz.getAnnotation(TypeAlias.class).value()
                                    ))
                    ),
                    new SimpleTypeInformationMapper()
            ));
        }
    }
}
