package org.phyloviz.pwp.administration.service.projects.files.typingData;

import org.phyloviz.pwp.administration.service.dtos.files.TypingDataDTO;
import org.phyloviz.pwp.administration.service.dtos.files.deleteTypingData.DeleteTypingDataInputDTO;
import org.phyloviz.pwp.administration.service.dtos.files.deleteTypingData.DeleteTypingDataOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.files.uploadTypingData.UploadTypingDataInputDTO;
import org.phyloviz.pwp.administration.service.dtos.files.uploadTypingData.UploadTypingDataOutputDTO;

public interface TypingDataService {

    /**
     * Uploads a typing data file.
     *
     * @param uploadTypingDataInputDTO the input data
     * @return
     */
    UploadTypingDataOutputDTO uploadTypingData(UploadTypingDataInputDTO uploadTypingDataInputDTO);

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
     *
     * @param deleteTypingDataInputDTO the input data
     * @return
     */
    DeleteTypingDataOutputDTO deleteTypingData(DeleteTypingDataInputDTO deleteTypingDataInputDTO);
}
