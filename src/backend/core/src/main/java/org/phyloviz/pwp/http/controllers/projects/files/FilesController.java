package org.phyloviz.pwp.http.controllers.projects.files;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.http.models.files.isolate_data.delete_isolate_data.DeleteIsolateDataOutputModel;
import org.phyloviz.pwp.http.models.files.isolate_data.update_isolate_data.UpdateIsolateDataInputModel;
import org.phyloviz.pwp.http.models.files.isolate_data.update_isolate_data.UpdateIsolateDataOutputModel;
import org.phyloviz.pwp.http.models.files.typing_data.delete_typing_data.DeleteTypingDataOutputModel;
import org.phyloviz.pwp.http.models.files.typing_data.update_typing_data.UpdateTypingDataInputModel;
import org.phyloviz.pwp.http.models.files.typing_data.update_typing_data.UpdateTypingDataOutputModel;
import org.phyloviz.pwp.service.dtos.files.isolate_data.UpdateIsolateDataOutput;
import org.phyloviz.pwp.service.dtos.files.typing_data.UpdateTypingDataOutput;
import org.phyloviz.pwp.service.project.file.IsolateDataService;
import org.phyloviz.pwp.service.project.file.TypingDataService;
import org.phyloviz.pwp.domain.User;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Updates a typing data file.
     *
     * @param projectId    the id of the project to which the typing data file belongs
     * @param typingDataId the id of the typing data file to be updated
     * @param user         the user that is updating the typing data file
     * @return information about the updated typing data file
     */
    @PatchMapping("/projects/{projectId}/files/typing-data/{typingDataId}")
    public UpdateTypingDataOutputModel updateTypingData(
            @PathVariable String projectId,
            @PathVariable String typingDataId,
            @RequestBody UpdateTypingDataInputModel updateTypingDataInputModel,
            User user
    ) {
        UpdateTypingDataOutput updateTypingDataOutput = typingDataService.updateTypingData(
                updateTypingDataInputModel.getName(), projectId, typingDataId, user.getId()
        );

        return new UpdateTypingDataOutputModel(updateTypingDataOutput);
    }

    /**
     * Updates a isolate data file.
     *
     * @param projectId     the id of the project to which the isolate data file belongs
     * @param isolateDataId the id of the isolate data file to be updated
     * @param user          the user that is updating the isolate data file
     * @return information about the updated isolate data file
     */
    @PatchMapping("/projects/{projectId}/files/isolate-data/{isolateDataId}")
    public UpdateIsolateDataOutputModel updateIsolateData(
            @PathVariable String projectId,
            @PathVariable String isolateDataId,
            @RequestBody UpdateIsolateDataInputModel updateIsolateDataInputModel,
            User user
    ) {
        UpdateIsolateDataOutput updateIsolateDataOutput = isolateDataService.updateIsolateData(
                updateIsolateDataInputModel.getName(), projectId, isolateDataId, user.getId()
        );

        return new UpdateIsolateDataOutputModel(updateIsolateDataOutput);
    }
}
