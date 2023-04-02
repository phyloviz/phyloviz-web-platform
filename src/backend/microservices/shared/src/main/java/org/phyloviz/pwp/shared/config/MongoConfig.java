package org.phyloviz.pwp.shared.config;

import org.reflections.Reflections;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.convert.ConfigurableTypeInformationMapper;
import org.springframework.data.convert.SimpleTypeInformationMapper;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableMongoRepositories(basePackages = "org.phyloviz.pwp")
public class MongoConfig {

    /**
     * A custom type mapper that uses reflection to find all classes annotated with {@link TypeAlias} and
     * register them with the {@link ConfigurableTypeInformationMapper}. This allows us to use
     * {@link TypeAlias} annotations in our domain classes without having to explicitly register them
     * with the {@link ConfigurableTypeInformationMapper}.
     * <p>
     * Fix for the problem of application not maintaining type knowledge between restarts. Would always work correctly
     * within a single run of the application, caching the types of the saved documents appropriately. However, failed
     * when restarting the application, as the type information was lost.
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

    @Bean
    public MappingMongoConverter customMappingMongoConverter(
            MongoDatabaseFactory factory,
            MongoMappingContext context,
            BeanFactory beanFactory
    ) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);
        mappingConverter.setTypeMapper(new ReflectiveMongoTypeMapper());
        return mappingConverter;
    }
}
