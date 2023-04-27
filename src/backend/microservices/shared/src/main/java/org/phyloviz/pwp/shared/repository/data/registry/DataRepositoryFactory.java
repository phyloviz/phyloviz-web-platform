package org.phyloviz.pwp.shared.repository.data.registry;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class DataRepositoryFactory<I extends Enum<I>, R, D> {

    private final DataRepositoryRegistry<I, R, D> dataRepositoryRegistry;

    /**
     * Returns the repository for the given repositoryId.
     *
     * @param repositoryId the id of the repository
     * @return the repository
     */
    public R getRepository(I repositoryId) {
        return dataRepositoryRegistry.getRepository(repositoryId);
    }
}
