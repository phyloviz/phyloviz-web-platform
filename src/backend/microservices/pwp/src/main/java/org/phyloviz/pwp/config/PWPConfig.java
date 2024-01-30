package org.phyloviz.pwp.config;

import org.phyloviz.pwp.repository.metadata.templates.tool_template.converters.AccessTemplateDeserializer;
import org.phyloviz.pwp.repository.metadata.templates.tool_template.converters.AccessTemplateSerializer;
import org.phyloviz.pwp.repository.metadata.templates.tool_template.converters.CaseInsensitiveEnumDeserializerFactory;
import org.phyloviz.pwp.service.flowviz.FLOWViZClient;
import org.phyloviz.pwp.shared.config.ResourceServerSharedConfig;
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
import org.phyloviz.pwp.shared_phylodb.config.ResourceServerSharedPhylodbConfig;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.List;

/**
 * Configuration class for the Compute Microservice.
 */
@Configuration
@Import({ResourceServerSharedConfig.class, ResourceServerSharedPhylodbConfig.class})
public class PWPConfig {
    private final String flowVizUsername;
    private final String flowVizPassword;
    private final String flowVizUrl;
    private final TypingDataDataRepositoryRegistry typingDataDataRepositoryRegistry;
    private final IsolateDataDataRepositoryRegistry isolateDataDataRepositoryRegistry;
    private final DistanceMatrixDataRepositoryRegistry distanceMatrixDataRepositoryRegistry;
    private final TreeDataRepositoryRegistry treeDataRepositoryRegistry;
    private final TreeViewDataRepositoryRegistry treeViewDataRepositoryRegistry;

    public PWPConfig(
            @Value("${flowviz.username}") String flowVizUsername,
            @Value("${flowviz.password}") String flowVizPassword,
            @Value("${flowviz.url}") String flowVizUrl,
            TypingDataDataRepositoryRegistry typingDataDataRepositoryRegistry,
            IsolateDataDataRepositoryRegistry isolateDataDataRepositoryRegistry,
            DistanceMatrixDataRepositoryRegistry distanceMatrixDataRepositoryRegistry,
            TreeDataRepositoryRegistry treeDataRepositoryRegistry,
            TreeViewDataRepositoryRegistry treeViewDataRepositoryRegistry
    ) {

        this.flowVizUsername = flowVizUsername;
        this.flowVizPassword = flowVizPassword;
        this.flowVizUrl = flowVizUrl;
        this.typingDataDataRepositoryRegistry = typingDataDataRepositoryRegistry;
        this.isolateDataDataRepositoryRegistry = isolateDataDataRepositoryRegistry;
        this.distanceMatrixDataRepositoryRegistry = distanceMatrixDataRepositoryRegistry;
        this.treeDataRepositoryRegistry = treeDataRepositoryRegistry;
        this.treeViewDataRepositoryRegistry = treeViewDataRepositoryRegistry;
    }

    @Bean
    FLOWViZClient flowVizClient() {
        return FLOWViZClient
                .builder()
                .baseUrl(flowVizUrl)
                .credentials(flowVizUsername, flowVizPassword)
                .authenticate();
    }

    @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

    @Bean
    @Primary
    public MappingMongoConverter mappingMongoConverter(
            MongoDatabaseFactory factory,
            MongoMappingContext context,
            BeanFactory beanFactory
    ) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);
        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        mappingConverter.setCustomConversions(mongoCustomConversions(mappingConverter));
        ConversionService convService = mappingConverter.getConversionService();
        ((GenericConversionService) convService).addConverterFactory(new CaseInsensitiveEnumDeserializerFactory());

        mappingConverter.afterPropertiesSet();

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
                new TreeViewMetadataSerializer(mongoConverter),
                new AccessTemplateSerializer(),
                new AccessTemplateDeserializer(mongoConverter),
                // https://stackoverflow.com/questions/12385920/spring-mongodb-storing-retrieving-enums-as-int-not-string/30024621#30024621
                new CaseInsensitiveEnumDeserializerFactory.CaseInsensitiveEnumDeserializer(null)
        );

        return new MongoCustomConversions(converters);
    }

}