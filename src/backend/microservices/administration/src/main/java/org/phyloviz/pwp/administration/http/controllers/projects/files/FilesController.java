package org.phyloviz.pwp.administration.http.controllers.projects.files;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.http.models.files.isolate_data.delete_isolate_data.DeleteIsolateDataOutputModel;
import org.phyloviz.pwp.administration.http.models.files.typing_data.delete_typing_data.DeleteTypingDataOutputModel;
import org.phyloviz.pwp.shared.domain.User;
import org.phyloviz.pwp.shared.service.project.file.isolate_data.IsolateDataService;
import org.phyloviz.pwp.shared.service.project.file.typing_data.TypingDataService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that handles requests related to files.
 */
@RestController
@RequiredArgsConstructor
public class FilesController {

    private final TypingDataService typingDataService;
    private final IsolateDataService isolateDataService;

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

    /**
     * Deletes an isolate data file.
     *
     * @param projectId     the id of the project to which the isolate data file belongs
     * @param isolateDataId the id of the isolate data file to be deleted
     * @param user          the user that is deleting the isolate data file
     * @return information about the deleted isolate data file
     */
    @DeleteMapping("/projects/{projectId}/files/isolate-data/{isolateDataId}")
    public DeleteIsolateDataOutputModel deleteIsolateData(
            @PathVariable String projectId,
            @PathVariable String isolateDataId,
            User user
    ) {
        isolateDataService.deleteIsolateData(projectId, isolateDataId, user.getId());

        return new DeleteIsolateDataOutputModel(projectId, isolateDataId);
    }
}
