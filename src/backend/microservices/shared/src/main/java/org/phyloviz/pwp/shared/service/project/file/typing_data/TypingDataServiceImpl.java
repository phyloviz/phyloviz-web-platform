package org.phyloviz.pwp.shared.service.project.file.typing_data;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.documents.TypingDataMetadata;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.GetTypingDataProfilesOutput;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.GetTypingDataSchemaOutput;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.TypingDataInfo;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.UploadTypingDataOutput;
import org.phyloviz.pwp.shared.service.exceptions.DeniedFileDeletionException;
import org.phyloviz.pwp.shared.service.project.ProjectAccessService;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetAccessService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class TypingDataServiceImpl implements TypingDataService {

    private final ProjectAccessService projectAccessService;
    private final TypingDataAccessService typingDataAccessService;
    private final DatasetAccessService datasetAccessService;

    @Override
    public UploadTypingDataOutput uploadTypingData(String projectId, MultipartFile multipartFile, String userId) {
        return typingDataAccessService.uploadTypingData(projectId, multipartFile, userId);
    }

    @Override
    public TypingDataMetadata getTypingDataMetadata(String typingDataId) {
        return typingDataAccessService.getTypingDataMetadata(typingDataId);
    }

    @Override
    public TypingDataInfo getTypingDataInfo(String typingDataId) {
        return new TypingDataInfo(getTypingDataMetadata(typingDataId));
    }

    @Override
    public void deleteTypingData(String projectId, String typingDataId, String userId) {
        typingDataAccessService.assertExists(projectId, typingDataId, userId);

        Project project = projectAccessService.getProject(projectId, userId);

        project.getDatasetIds().forEach(datasetId -> {
            Dataset dataset = datasetAccessService.getDatasetOrNull(datasetId);

            if (dataset != null && dataset.getTypingDataId().equals(typingDataId))
                throw new DeniedFileDeletionException(
                        "Cannot delete file. File is still being used in one or more datasets. " +
                                "Delete the datasets first."
                );
        });

        deleteTypingData(typingDataId);

        project.getFileIds().getTypingDataIds().remove(typingDataId);
        projectAccessService.saveProject(project);
    }

    @Override
    public void deleteTypingData(String typingDataId) {
        typingDataAccessService.deleteTypingData(typingDataId);
    }

    @Override
    public GetTypingDataSchemaOutput getTypingDataSchema(String projectId, String typingDataId, String userId) {
        return typingDataAccessService.getTypingDataSchema(projectId, typingDataId, userId);
    }

    @Override
    public GetTypingDataProfilesOutput getTypingDataProfiles(String projectId, String typingDataId, int limit, int offset, String userId) {
        return typingDataAccessService.getTypingDataProfiles(projectId, typingDataId, limit, offset, userId);
    }
}
