package org.phyloviz.pwp.shared.config;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.distance_matrix.DistanceMatrixAdapterRegistry;
import org.phyloviz.pwp.shared.adapters.isolate_data.IsolateDataAdapterRegistry;
import org.phyloviz.pwp.shared.adapters.tree.TreeAdapterRegistry;
import org.phyloviz.pwp.shared.adapters.tree_view.TreeViewAdapterRegistry;
import org.phyloviz.pwp.shared.adapters.typing_data.TypingDataAdapterRegistry;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.converters.DistanceMatrixMetadataDeserializer;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.converters.IsolateDataMetadataDeserializer;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.converters.IsolateDataMetadataSerializer;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.converters.TreeMetadataDeserializer;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.converters.TreeViewMetadataDeserializer;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.documents.converters.TypingDataMetadataDeserializer;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.documents.converters.TypingDataMetadataSerializer;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@Configuration
@EnableMongoRepositories(basePackages = "org.phyloviz.pwp")
@RequiredArgsConstructor
public class MongoConfig {

    private final TypingDataAdapterRegistry typingDataAdapterRegistry;
    private final IsolateDataAdapterRegistry isolateDataAdapterRegistry;
    private final DistanceMatrixAdapterRegistry distanceMatrixAdapterRegistry;
    private final TreeAdapterRegistry treeAdapterRegistry;
    private final TreeViewAdapterRegistry treeViewAdapterRegistry;

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
                new TypingDataMetadataDeserializer(mongoConverter, typingDataAdapterRegistry),
                new TypingDataMetadataSerializer(mongoConverter),
                new IsolateDataMetadataDeserializer(mongoConverter, isolateDataAdapterRegistry),
                new IsolateDataMetadataSerializer(mongoConverter),
                new DistanceMatrixMetadataDeserializer(mongoConverter, distanceMatrixAdapterRegistry),
                new TreeMetadataDeserializer(mongoConverter, treeAdapterRegistry),
                new TreeViewMetadataDeserializer(mongoConverter, treeViewAdapterRegistry)
        );

        return new MongoCustomConversions(converters);
    }
}
