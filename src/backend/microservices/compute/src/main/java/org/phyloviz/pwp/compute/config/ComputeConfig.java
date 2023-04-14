package org.phyloviz.pwp.compute.config;

import org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.converters.AccessTemplateDeserializer;
import org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.converters.AccessTemplateSerializer;
import org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.converters.CaseInsensitiveEnumDeserializerFactory;
import org.phyloviz.pwp.compute.service.flowviz.FLOWViZClient;
import org.phyloviz.pwp.shared.config.ResourceServerSharedConfig;
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
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.List;

/**
 * Configuration class for the Compute microservice.
 */
@Configuration
@Import({ResourceServerSharedConfig.class})
public class ComputeConfig {

    private final String flowVizUsername;
    private final String flowVizPassword;
    private final String flowVizUrl;

    public ComputeConfig(
            @Value("${flowviz.username}") String flowVizUsername,
            @Value("${flowviz.password}") String flowVizPassword,
            @Value("${flowviz.url}") String flowVizUrl) {
        this.flowVizUsername = flowVizUsername;
        this.flowVizPassword = flowVizPassword;
        this.flowVizUrl = flowVizUrl;
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
                new AccessTemplateSerializer(),
                new AccessTemplateDeserializer(mongoConverter),
                //https://stackoverflow.com/questions/12385920/spring-mongodb-storing-retrieving-enums-as-int-not-string/30024621#30024621
                new CaseInsensitiveEnumDeserializerFactory.CaseInsensitiveEnumDeserializer(null)
        );

        return new MongoCustomConversions(converters);
    }

}