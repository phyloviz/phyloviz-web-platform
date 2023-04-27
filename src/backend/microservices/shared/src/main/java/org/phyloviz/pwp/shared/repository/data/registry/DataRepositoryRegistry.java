package org.phyloviz.pwp.shared.repository.data.registry;

public interface DataRepositoryRegistry<I extends Enum<I>, R, D> {

    /**
     * Returns the repository for the given repositoryId.
     *
     * @param repositoryId the id of the repository
     * @return the repository
     */
    R getRepository(I repositoryId);

    /**
     * Returns the RepositorySpecificData class for the given repositoryId.
     *
     * @param repositoryId the id of the repository
     * @return the RepositorySpecificData class
     */
    Class<? extends D> getRepositorySpecificDataClass(I repositoryId);
}