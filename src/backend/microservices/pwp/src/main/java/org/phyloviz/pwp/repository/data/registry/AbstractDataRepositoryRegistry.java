package org.phyloviz.pwp.repository.data.registry;

import jakarta.annotation.PostConstruct;
import org.springframework.context.ApplicationContext;

import java.util.EnumMap;
import java.util.Map;

public abstract class AbstractDataRepositoryRegistry<I extends Enum<I>, R, D> implements DataRepositoryRegistry<I, R, D> {

    private final Class<I> idEnumClass;

    private final ApplicationContext context;

    private final Map<I, R> repositories;
    private final Map<I, Class<? extends R>> repositoryClasses;
    private final Map<I, Class<? extends D>> repositorySpecificDataClasses;

    protected AbstractDataRepositoryRegistry(
            ApplicationContext context,
            Class<I> idEnumClass,
            Map<I, Class<? extends R>> repositoryClasses,
            Map<I, Class<? extends D>> repositorySpecificDataClasses) {
        this.idEnumClass = idEnumClass;
        this.context = context;
        this.repositories = new EnumMap<>(idEnumClass);
        this.repositoryClasses = repositoryClasses;
        this.repositorySpecificDataClasses = repositorySpecificDataClasses;
    }

    @PostConstruct
    public void setRepositories() {
        for (I repositoryId : idEnumClass.getEnumConstants()) {
            Class<? extends R> repositoryClass = repositoryClasses.get(repositoryId);
            if (repositoryClass != null) {
                R repository = context.getBean(repositoryClass);
                this.repositories.put(repositoryId, repository);
            }
        }
    }

    @Override
    public R getRepository(I repositoryId) {
        R repository = repositories.get(repositoryId);
        if (repository == null) {
            throw new IllegalStateException("No Repository found for id: " + repositoryId);
        }
        return repository;
    }

    @Override
    public Class<? extends D> getRepositorySpecificDataClass(I repositoryId) {
        Class<? extends D> repositorySpecificDataClass = repositorySpecificDataClasses.get(repositoryId);
        if (repositorySpecificDataClass == null) {
            throw new IllegalStateException("No Repository Specific Data Class found for id: " + repositoryId);
        }
        return repositorySpecificDataClass;
    }
}