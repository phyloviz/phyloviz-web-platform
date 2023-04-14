package org.phyloviz.pwp.administration.repository.data;

import org.springframework.stereotype.Repository;

/**
 * Repository interface for storing data resources.
 */
@Repository
public interface DataStorageRepository {

    /**
     * Stores the resource.
     *
     * @param url  url where the resource will be stored
     * @param data resource to be stored
     * @return true if the resource was stored successfully, false otherwise
     */
    boolean store(String url, String data); // TODO think about adapter specific object for data

    /**
     * Deletes the file.
     *
     * @param url location where the resource is stored
     * @return true if the resource was deleted successfully, false otherwise
     */
    boolean delete(String url);

    /**
     * Get location of the repository.
     *
     * @return location of the repository
     */
    String getLocation();

    /**
     * Get the adapter id.
     *
     * @return adapter id
     */
    String getAdapterId();
}
