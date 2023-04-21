package org.phyloviz.pwp.administration.http.service.project.file;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.isolate_data.IsolateDataAdapterFactory;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.IsolateDataInfo;
import org.phyloviz.pwp.shared.service.exceptions.DeniedFileDeletionException;
import org.phyloviz.pwp.shared.service.project.ProjectMetadataService;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetMetadataService;
import org.phyloviz.pwp.shared.service.project.file.isolate_data.IsolateDataMetadataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IsolateDataServiceImpl implements IsolateDataService {

    private final ProjectMetadataService projectMetadataService;
    private final IsolateDataMetadataService isolateDataMetadataService;
    private final DatasetMetadataService datasetMetadataService;

    private final IsolateDataAdapterFactory isolateDataAdapterFactory;

    @Override
    public List<IsolateDataInfo> getIsolateDataInfos(String projectId) {
        return isolateDataMetadataService.getAllIsolateDataMetadataByProjectId(projectId).stream()
                .map(IsolateDataInfo::new).toList();
    }

    @Override
    public void deleteIsolateData(String projectId, String isolateDataId, String userId) {
        isolateDataMetadataService.assertExists(projectId, isolateDataId, userId);

        Project project = projectMetadataService.getProject(projectId, userId);

        project.getDatasetIds().forEach(datasetId -> {
            Dataset dataset = datasetMetadataService.getDatasetOrNull(datasetId);

            if (dataset != null && dataset.getIsolateDataId() != null && dataset.getIsolateDataId().equals(isolateDataId))
                throw new DeniedFileDeletionException(
                        "Cannot delete file. File is still being used in one or more datasets. " +
                                "Delete the datasets first."
                );
        });

        deleteIsolateData(isolateDataId);

        project.getFileIds().getIsolateDataIds().remove(isolateDataId);
        projectMetadataService.saveProject(project);
    }

    @Override
    public void deleteIsolateData(String isolateDataId) {
        isolateDataMetadataService.getAllIsolateDataMetadata(isolateDataId)
                .forEach(isolateDataMetadata -> {
                    isolateDataAdapterFactory.getIsolateDataAdapter(isolateDataMetadata.getAdapterId())
                            .deleteIsolateData(isolateDataMetadata.getAdapterSpecificData());

                    isolateDataMetadataService.deleteIsolateData(isolateDataMetadata);
                });
    }
}
