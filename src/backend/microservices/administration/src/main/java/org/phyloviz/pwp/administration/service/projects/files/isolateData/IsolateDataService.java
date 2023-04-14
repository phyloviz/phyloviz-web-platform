package org.phyloviz.pwp.administration.service.projects.files.isolateData;

import org.phyloviz.pwp.administration.service.dtos.files.IsolateDataDTO;
import org.phyloviz.pwp.administration.service.dtos.files.deleteIsolateData.DeleteIsolateDataInputDTO;
import org.phyloviz.pwp.administration.service.dtos.files.deleteIsolateData.DeleteIsolateDataOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.files.uploadIsolateData.UploadIsolateDataInputDTO;
import org.phyloviz.pwp.administration.service.dtos.files.uploadIsolateData.UploadIsolateDataOutputDTO;

/**
 * Service for operations related to isolate data files.
 */
public interface IsolateDataService {

    /**
     * Uploads an isolate data file.
     *
     * @param uploadIsolateDataInputDTO the input data for the isolate data upload
     * @return information about the uploaded isolate data file
     */
    UploadIsolateDataOutputDTO uploadIsolateData(UploadIsolateDataInputDTO uploadIsolateDataInputDTO);

    /**
     * Deletes an isolate data file.
     *
     * @param deleteIsolateDataInputDTO the input data for the isolate data deletion
     * @return information about the deleted isolate data file
     */
    DeleteIsolateDataOutputDTO deleteIsolateData(DeleteIsolateDataInputDTO deleteIsolateDataInputDTO);

    /**
     * Get an isolate data file information.
     * This method is also used by other services (ProjectsService) to allow for the recursive retrieval of resources.
     *
     * @param isolateDataId the id of the isolate data
     * @return the isolate data information
     */
    IsolateDataDTO getIsolateData(String isolateDataId);

    /**
     * Deletes an isolate data file.
     * This method is also used by other services (ProjectsService) to allow for the recursive deletion of resources.
     * Does not delete its own id from the project.
     *
     * @param isolateDataId the id of the isolate data
     */
    void deleteIsolateData(String isolateDataId);
}
