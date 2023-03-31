package org.phyloviz.pwp.administration.service.projects.files.isolateData;

import org.phyloviz.pwp.administration.service.dtos.files.IsolateDataDTO;
import org.phyloviz.pwp.administration.service.dtos.files.deleteIsolateData.DeleteIsolateDataInputDTO;
import org.phyloviz.pwp.administration.service.dtos.files.deleteIsolateData.DeleteIsolateDataOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.files.uploadIsolateData.UploadIsolateDataInputDTO;
import org.phyloviz.pwp.administration.service.dtos.files.uploadIsolateData.UploadIsolateDataOutputDTO;

public interface IsolateDataService {

    /**
     * Uploads an isolate data file.
     *
     * @param uploadIsolateDataInputDTO the input data
     * @return
     */
    UploadIsolateDataOutputDTO uploadIsolateData(UploadIsolateDataInputDTO uploadIsolateDataInputDTO);

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
     *
     * @param deleteIsolateDataInputDTO the input data
     * @return
     */
    DeleteIsolateDataOutputDTO deleteIsolateData(DeleteIsolateDataInputDTO deleteIsolateDataInputDTO);
}
