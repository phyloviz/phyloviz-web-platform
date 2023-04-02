package org.phyloviz.pwp.shared.config;

import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.deserializer.DistanceMatrixMetadataDeserializer;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.deserializer.IsolateDataMetadataDeserializer;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.deserializer.TreeMetadataDeserializer;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.deserializer.TreeViewMetadataDeserializer;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.deserializer.TypingDataMetadataDeserializer;
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
        return mappingConverter;
    }

    public MongoCustomConversions mongoCustomConversions(MongoConverter mongoConverter) {
        List<Converter<?, ?>> converters = List.of(
                new TypingDataMetadataDeserializer(mongoConverter),
                new IsolateDataMetadataDeserializer(mongoConverter),
                new DistanceMatrixMetadataDeserializer(mongoConverter),
                new TreeMetadataDeserializer(mongoConverter),
                new TreeViewMetadataDeserializer(mongoConverter)
        );

        return new MongoCustomConversions(converters);
    }
}
