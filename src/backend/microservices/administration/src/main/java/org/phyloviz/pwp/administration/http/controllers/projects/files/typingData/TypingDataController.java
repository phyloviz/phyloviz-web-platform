package org.phyloviz.pwp.administration.http.controllers.projects.files.typingData;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.http.models.files.typingData.deleteTypingData.DeleteTypingDataOutputModel;
import org.phyloviz.pwp.administration.http.models.files.typingData.uploadTypingData.UploadTypingDataOutputModel;
import org.phyloviz.pwp.shared.domain.User;
import org.phyloviz.pwp.shared.service.dtos.files.UploadTypingDataOutput;
import org.phyloviz.pwp.shared.service.project.file.typingData.TypingDataService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller that handles requests related to typing data files.
 */
@RestController
@RequiredArgsConstructor
public class TypingDataController {

    private final TypingDataService typingDataService;

    /**
     * Uploads a typing data.
     *
     * @param projectId the name of the project to which the typing data will be uploaded
     * @param file      the file to be uploaded
     * @param user      the user that is uploading the typing data
     * @return information about the uploaded typing data
     */
    @PostMapping(path = "/projects/{projectId}/files/typing-data", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public UploadTypingDataOutputModel uploadTypingData(
            @PathVariable String projectId,
            @RequestPart MultipartFile file,
            User user
    ) {
        UploadTypingDataOutput uploadTypingDataOutput = typingDataService.uploadTypingData(projectId, file, user.getId());

        return new UploadTypingDataOutputModel(uploadTypingDataOutput);
    }

    /**
     * Deletes a typing data file.
     *
     * @param projectId    the id of the project to which the typing data file belongs
     * @param typingDataId the id of the typing data file to be deleted
     * @param user         the user that is deleting the typing data file
     * @return information about the deleted typing data file
     */
    @DeleteMapping("/projects/{projectId}/files/typing-data/{typingDataId}")
    public DeleteTypingDataOutputModel deleteTypingData(
            @PathVariable String projectId,
            @PathVariable String typingDataId,
            User user
    ) {
        typingDataService.deleteTypingData(projectId, typingDataId, user.getId());

        return new DeleteTypingDataOutputModel(projectId, typingDataId);
    }
}
