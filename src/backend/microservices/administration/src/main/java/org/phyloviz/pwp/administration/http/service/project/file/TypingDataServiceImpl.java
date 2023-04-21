package org.phyloviz.pwp.administration.http.service.project.file;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.typing_data.TypingDataAdapterFactory;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.TypingDataInfo;
import org.phyloviz.pwp.shared.service.exceptions.DeniedFileDeletionException;
import org.phyloviz.pwp.shared.service.project.ProjectMetadataService;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetMetadataService;
import org.phyloviz.pwp.shared.service.project.file.typing_data.TypingDataMetadataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypingDataServiceImpl implements TypingDataService {

    private final ProjectMetadataService projectMetadataService;
    private final TypingDataMetadataService typingDataMetadataService;
    private final DatasetMetadataService datasetMetadataService;

    private final TypingDataAdapterFactory typingDataAdapterFactory;

    @Override
    public List<TypingDataInfo> getTypingDataInfos(String projectId) {
        return typingDataMetadataService.getAllTypingDataMetadataByProjectId(projectId).stream()
                .map(TypingDataInfo::new).toList();
    }

    @Override
    public void deleteTypingData(String projectId, String typingDataId, String userId) {
        typingDataMetadataService.assertExists(projectId, typingDataId, userId);

        Project project = projectMetadataService.getProject(projectId, userId);

        project.getDatasetIds().forEach(datasetId -> {
            Dataset dataset = datasetMetadataService.getDatasetOrNull(datasetId);

            if (dataset != null && dataset.getTypingDataId().equals(typingDataId))
                throw new DeniedFileDeletionException(
                        "Cannot delete file. File is still being used in one or more datasets. " +
                                "Delete the datasets first."
                );
        });

        deleteTypingData(typingDataId);

        project.getFileIds().getTypingDataIds().remove(typingDataId);
        projectMetadataService.saveProject(project);
    }

    @Override
    public void deleteTypingData(String typingDataId) {
        typingDataMetadataService.getAllTypingDataMetadata(typingDataId)
                .forEach(typingDataMetadata -> {
                    typingDataAdapterFactory.getTypingDataAdapter(typingDataMetadata.getAdapterId())
                            .deleteTypingData(typingDataMetadata.getAdapterSpecificData());

                    typingDataMetadataService.deleteTypingData(typingDataMetadata);
                });
    }
}
