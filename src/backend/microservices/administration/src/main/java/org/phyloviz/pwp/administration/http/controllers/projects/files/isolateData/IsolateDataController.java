package org.phyloviz.pwp.administration.http.controllers.projects.files.isolateData;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.http.models.files.isolateData.deleteIsolateData.DeleteIsolateDataOutputModel;
import org.phyloviz.pwp.administration.http.models.files.isolateData.uploadIsolateData.UploadIsolateDataOutputModel;
import org.phyloviz.pwp.administration.service.dtos.files.deleteIsolateData.DeleteIsolateDataInputDTO;
import org.phyloviz.pwp.administration.service.dtos.files.deleteIsolateData.DeleteIsolateDataOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.files.uploadIsolateData.UploadIsolateDataInputDTO;
import org.phyloviz.pwp.administration.service.dtos.files.uploadIsolateData.UploadIsolateDataOutputDTO;
import org.phyloviz.pwp.administration.service.projects.files.isolateData.IsolateDataService;
import org.phyloviz.pwp.shared.domain.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller that handles requests related to isolate data files.
 */
@RestController
@RequiredArgsConstructor
public class IsolateDataController {

    private final IsolateDataService isolateDataService;

    /**
     * Uploads an isolate data.
     *
     * @param projectId the name of the project to which the isolate data will be uploaded
     * @param file      the file to be uploaded
     * @param user      the user that is uploading the isolate data
     * @return information about the uploaded isolate data
     */
    @PostMapping(path = "/projects/{projectId}/files/isolate-data", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public UploadIsolateDataOutputModel uploadIsolateData(
            @PathVariable String projectId,
            @RequestPart MultipartFile file,
            User user
    ) {
        UploadIsolateDataOutputDTO uploadIsolateDataOutputDTO = isolateDataService.uploadIsolateData(
                new UploadIsolateDataInputDTO(projectId, file, user.toDTO())
        );

        return new UploadIsolateDataOutputModel(uploadIsolateDataOutputDTO);
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
        DeleteIsolateDataOutputDTO deleteIsolateDataOutputDTO = isolateDataService.deleteIsolateData(
                new DeleteIsolateDataInputDTO(projectId, isolateDataId, user.toDTO())
        );

        return new DeleteIsolateDataOutputModel(deleteIsolateDataOutputDTO);
    }
}
