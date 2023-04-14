package org.phyloviz.pwp.administration.service.projects.files.typingData;

import org.phyloviz.pwp.administration.service.dtos.files.TypingDataDTO;
import org.phyloviz.pwp.administration.service.dtos.files.deleteTypingData.DeleteTypingDataInputDTO;
import org.phyloviz.pwp.administration.service.dtos.files.deleteTypingData.DeleteTypingDataOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.files.uploadTypingData.UploadTypingDataInputDTO;
import org.phyloviz.pwp.administration.service.dtos.files.uploadTypingData.UploadTypingDataOutputDTO;

/**
 * Service for operations related to typing data files.
 */
public interface TypingDataService {

    /**
     * Uploads a typing data file.
     *
     * @param uploadTypingDataInputDTO the input data for the typing data upload
     * @return information about the uploaded typing data file
     */
    UploadTypingDataOutputDTO uploadTypingData(UploadTypingDataInputDTO uploadTypingDataInputDTO);

    /**
     * Deletes a typing data file.
     *
     * @param deleteTypingDataInputDTO the input data for the typing data deletion
     * @return information about the deleted typing data file
     */
    DeleteTypingDataOutputDTO deleteTypingData(DeleteTypingDataInputDTO deleteTypingDataInputDTO);

    /**
     * Get a typing data file information.
     * This method is also used by other services (ProjectsService) to allow for the recursive retrieval of resources.
     *
     * @param typingDataId the id of the typing data
     * @return the typing data information
     */
    TypingDataDTO getTypingData(String typingDataId);

    /**
     * Deletes a typing data file.
     * This method is also used by other services (ProjectsService) to allow for the recursive deletion of resources.
     * Does not delete its own id from the project.
     *
     * @param typingDataId the id of the typing data
     */
    void deleteTypingData(String typingDataId);
}
