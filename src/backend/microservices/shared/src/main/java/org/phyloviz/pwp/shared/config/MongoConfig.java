package org.phyloviz.pwp.shared.config;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.registry.distance_matrix.DistanceMatrixDataRepositoryRegistry;
import org.phyloviz.pwp.shared.repository.data.registry.isolate_data.IsolateDataDataRepositoryRegistry;
import org.phyloviz.pwp.shared.repository.data.registry.tree.TreeDataRepositoryRegistry;
import org.phyloviz.pwp.shared.repository.data.registry.tree_view.TreeViewDataRepositoryRegistry;
import org.phyloviz.pwp.shared.repository.data.registry.typing_data.TypingDataDataRepositoryRegistry;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.converters.DistanceMatrixMetadataDeserializer;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.converters.IsolateDataMetadataDeserializer;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.converters.IsolateDataMetadataSerializer;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.converters.TreeMetadataDeserializer;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.converters.TreeViewMetadataDeserializer;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.converters.TreeViewMetadataSerializer;
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

    private final TypingDataDataRepositoryRegistry typingDataDataRepositoryRegistry;
    private final IsolateDataDataRepositoryRegistry isolateDataDataRepositoryRegistry;
    private final DistanceMatrixDataRepositoryRegistry distanceMatrixDataRepositoryRegistry;
    private final TreeDataRepositoryRegistry treeDataRepositoryRegistry;
    private final TreeViewDataRepositoryRegistry treeViewDataRepositoryRegistry;

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
                new TypingDataMetadataDeserializer(mongoConverter, typingDataDataRepositoryRegistry),
                new TypingDataMetadataSerializer(mongoConverter),
                new IsolateDataMetadataDeserializer(mongoConverter, isolateDataDataRepositoryRegistry),
                new IsolateDataMetadataSerializer(mongoConverter),
                new DistanceMatrixMetadataDeserializer(mongoConverter, distanceMatrixDataRepositoryRegistry),
                new TreeMetadataDeserializer(mongoConverter, treeDataRepositoryRegistry),
                new TreeViewMetadataDeserializer(mongoConverter, treeViewDataRepositoryRegistry),
                new TreeViewMetadataSerializer(mongoConverter)
        );

        return new MongoCustomConversions(converters);
    }
}
