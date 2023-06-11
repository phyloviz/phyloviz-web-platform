package org.phyloviz.pwp.administration.service.dataset;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.phyloviz.pwp.administration.service.dtos.dataset.CreateDatasetOutput;
import org.phyloviz.pwp.administration.service.dtos.dataset.FullDatasetInfo;
import org.phyloviz.pwp.administration.service.dtos.dataset.UpdateDatasetOutput;
import org.phyloviz.pwp.administration.service.project.dataset.DatasetService;
import org.phyloviz.pwp.administration.service.project.dataset.distance_matrix.DistanceMatrixService;
import org.phyloviz.pwp.administration.service.project.dataset.tree.TreeService;
import org.phyloviz.pwp.administration.service.project.dataset.tree_view.TreeViewService;
import org.phyloviz.pwp.shared.repository.data.isolate_data.IsolateDataDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.specific_data.IsolateDataS3DataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.IsolateDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.IsolateDataMetadata;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.TypingDataMetadataRepository;
import org.phyloviz.pwp.shared.service.exceptions.DatasetNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.InvalidArgumentException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class DatasetServiceTests {

    @MockBean
    private ProjectRepository projectRepository;

    @MockBean
    private DatasetRepository datasetRepository;

    @MockBean
    private TypingDataMetadataRepository typingDataMetadataRepository;

    @MockBean
    private IsolateDataMetadataRepository isolateDataMetadataRepository;

    @MockBean
    private DistanceMatrixService distanceMatrixService;

    @MockBean
    private TreeService treeService;

    @MockBean
    private TreeViewService treeViewService;

    @Autowired
    private DatasetService datasetService;


    // createDataset
    @Test
    void createDatasetWithoutIsolateDataIsSuccessfull() {
        String datasetName = "datasetName";
        String datasetDescription = "datasetDescription";
        String typingDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String isolateDataId = null;
        String isolateDataKey = null;
        String projectId = "projectId";
        String userId = "userId";
        String datasetId = "datasetId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);

        when(typingDataMetadataRepository.existsByProjectIdAndTypingDataId(any(String.class), any(String.class)))
                .thenReturn(true);

        when(datasetRepository.save(any()))
                .thenReturn(new Dataset(
                        datasetId,
                        projectId,
                        datasetName,
                        datasetDescription,
                        typingDataId,
                        isolateDataId,
                        isolateDataKey
                ));

        CreateDatasetOutput createDatasetOutput = datasetService.createDataset(
                datasetName,
                datasetDescription,
                typingDataId,
                isolateDataId,
                isolateDataKey,
                projectId,
                userId
        );

        assertEquals(datasetId, createDatasetOutput.getDatasetId());
        assertEquals(projectId, createDatasetOutput.getProjectId());
    }

    @Test
    void createDatasetWithIsolateDataIsSuccessfull() {
        String datasetName = "datasetName";
        String datasetDescription = "datasetDescription";
        String typingDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String isolateDataId = "dfb3b1a0-7b9a-4b7e-9b9a-5b8b5b5b5b5b";
        String isolateDataName = "isolateDataName";
        String isolateDataKey = "isolateDataKey";
        String projectId = "projectId";
        String userId = "userId";
        String datasetId = "datasetId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);

        when(typingDataMetadataRepository.existsByProjectIdAndTypingDataId(any(String.class), any(String.class)))
                .thenReturn(true);

        when(isolateDataMetadataRepository.findByProjectIdAndIsolateDataId(any(String.class), any(String.class)))
                .thenReturn(Optional.of(new IsolateDataMetadata(
                        projectId,
                        isolateDataId,
                        List.of(isolateDataKey),
                        isolateDataName,
                        Map.of(IsolateDataDataRepositoryId.S3, new IsolateDataS3DataRepositorySpecificData())
                )));

        when(datasetRepository.save(any()))
                .thenReturn(new Dataset(
                        datasetId,
                        projectId,
                        datasetName,
                        datasetDescription,
                        typingDataId,
                        isolateDataId,
                        isolateDataKey
                ));

        CreateDatasetOutput createDatasetOutput = datasetService.createDataset(
                datasetName,
                datasetDescription,
                typingDataId,
                isolateDataId,
                isolateDataKey,
                projectId,
                userId
        );

        assertEquals(datasetId, createDatasetOutput.getDatasetId());
        assertEquals(projectId, createDatasetOutput.getProjectId());
    }

    @Test
    void createDatasetThrowsWhenIsolateDataKeyDoesNotExistInIsolateData() {
        String datasetName = "datasetName";
        String datasetDescription = "datasetDescription";
        String typingDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String isolateDataId = "dfb3b1a0-7b9a-4b7e-9b9a-5b8b5b5b5b5b";
        String isolateDataName = "isolateDataName";
        String isolateDataKey = "isolateDataKey";
        String projectId = "projectId";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);

        when(typingDataMetadataRepository.existsByProjectIdAndTypingDataId(any(String.class), any(String.class)))
                .thenReturn(true);

        when(isolateDataMetadataRepository.findByProjectIdAndIsolateDataId(any(String.class), any(String.class)))
                .thenReturn(Optional.of(new IsolateDataMetadata(
                        projectId,
                        isolateDataId,
                        List.of(isolateDataKey),
                        isolateDataName,
                        Map.of(IsolateDataDataRepositoryId.S3, new IsolateDataS3DataRepositorySpecificData())
                )));

        assertThrows(
                InvalidArgumentException.class,
                () -> datasetService.createDataset(
                        datasetName,
                        datasetDescription,
                        typingDataId,
                        isolateDataId,
                        isolateDataKey,
                        projectId,
                        userId
                )
        );
    }

    @Test
    void createDatasetThrowsWhenIsolateDataKeyIsNull() {
        String datasetName = "datasetName";
        String datasetDescription = "datasetDescription";
        String typingDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String isolateDataId = "dfb3b1a0-7b9a-4b7e-9b9a-5b8b5b5b5b5b";
        String isolateDataName = "isolateDataName";
        String isolateDataKey = null;
        String projectId = "projectId";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);

        when(typingDataMetadataRepository.existsByProjectIdAndTypingDataId(any(String.class), any(String.class)))
                .thenReturn(true);

        when(isolateDataMetadataRepository.findByProjectIdAndIsolateDataId(any(String.class), any(String.class)))
                .thenReturn(Optional.of(new IsolateDataMetadata(
                        projectId,
                        isolateDataId,
                        List.of(isolateDataKey),
                        isolateDataName,
                        Map.of(IsolateDataDataRepositoryId.S3, new IsolateDataS3DataRepositorySpecificData())
                )));

        assertThrows(
                InvalidArgumentException.class,
                () -> datasetService.createDataset(
                        datasetName,
                        datasetDescription,
                        typingDataId,
                        isolateDataId,
                        isolateDataKey,
                        projectId,
                        userId
                )
        );
    }

    @Test
    void createDatasetThrowsWhenProjectDoesNotExist() {
        String datasetName = "datasetName";
        String datasetDescription = "datasetDescription";
        String typingDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String isolateDataId = null;
        String isolateDataKey = null;
        String projectId = "projectId";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(false);

        when(typingDataMetadataRepository.existsByProjectIdAndTypingDataId(any(String.class), any(String.class)))
                .thenReturn(true);

        assertThrows(ProjectNotFoundException.class, () ->
                datasetService.createDataset(
                        datasetName,
                        datasetDescription,
                        typingDataId,
                        isolateDataId,
                        isolateDataKey,
                        projectId,
                        userId
                ));
    }

    @ParameterizedTest
    @CsvSource({
            ", ec7bae63-3238-4044-8d03-e2d9911f50f8, dh9bae45-3238-4044-8d03-e2d9911f50f8",
            "'', ec7bae63-3238-4044-8d03-e2d9911f50f8, dh9bae45-3238-4044-8d03-e2d9911f50f8",
            "name,, dh9bae45-3238-4044-8d03-e2d9911f50f8",
            "name,'', dh9bae45-3238-4044-8d03-e2d9911f50f8",
            "name,invalidUUID, dh9bae45-3238-4044-8d03-e2d9911f50f8",
            "name,ec7bae63-3238-4044-8d03-e2d9911f50f8,''",
            "name,ec7bae63-3238-4044-8d03-e2d9911f50f8,invalidUUID",
    })
    void createDatasetThrowsWhenInvalidArguments(String datasetName, String typingDataId, String isolateDataId) {
        String datasetDescription = "datasetDescription";
        String isolateDataKey = "isolateDataKey";
        String projectId = "projectId";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);

        assertThrows(InvalidArgumentException.class, () ->
                datasetService.createDataset(
                        datasetName,
                        datasetDescription,
                        typingDataId,
                        isolateDataId,
                        isolateDataKey,
                        projectId,
                        userId
                ));
    }

    // getFullDatasetInfo
    @Test
    void getFullDatasetInfoIsSuccessful() {
        String datasetName = "datasetName";
        String datasetDescription = "datasetDescription";
        String typingDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String isolateDataId = null;
        String isolateDataKey = null;
        String projectId = "projectId";
        String datasetId = "datasetId";
        String userId = "userId";

        when(datasetRepository.findByProjectIdAndId(any(String.class), any(String.class)))
                .thenReturn(
                        Optional.of(new Dataset(
                                datasetId,
                                projectId,
                                datasetName,
                                datasetDescription,
                                typingDataId,
                                isolateDataId,
                                isolateDataKey
                        ))
                );

        when(distanceMatrixService.getDistanceMatrixInfos(any(String.class))).thenReturn(List.of());
        when(treeService.getTreeInfos(any(String.class))).thenReturn(List.of());
        when(treeViewService.getTreeViewInfos(any(String.class))).thenReturn(List.of());

        FullDatasetInfo fullDatasetInfo = datasetService.getFullDatasetInfo(projectId, datasetId, userId);

        assertEquals(datasetId, fullDatasetInfo.getDatasetId());
        assertEquals(datasetName, fullDatasetInfo.getName());
        assertEquals(datasetDescription, fullDatasetInfo.getDescription());
        assertEquals(typingDataId, fullDatasetInfo.getTypingDataId());
        assertEquals(isolateDataId, fullDatasetInfo.getIsolateDataId());
        assertTrue(fullDatasetInfo.getDistanceMatrices().isEmpty());
        assertTrue(fullDatasetInfo.getTrees().isEmpty());
        assertTrue(fullDatasetInfo.getTreeViews().isEmpty());
    }

    @Test
    void getFullDatasetInfoThrowsWhenDatasetDoesNotExist() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String userId = "userId";

        when(datasetRepository.findByProjectIdAndId(any(String.class), any(String.class)))
                .thenReturn(Optional.empty());

        assertThrows(DatasetNotFoundException.class, () ->
                datasetService.getFullDatasetInfo(projectId, datasetId, userId)
        );
    }

    // getFullDatasetInfos
    @Test
    void getFullDatasetInfosIsSuccessful() {
        String datasetName = "datasetName";
        String datasetDescription = "datasetDescription";
        String typingDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String isolateDataId = null;
        String isolateDataKey = null;
        String projectId = "projectId";
        String datasetId = "datasetId";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);

        when(datasetRepository.findAllByProjectId(any(String.class)))
                .thenReturn(
                        List.of(new Dataset(
                                datasetId,
                                projectId,
                                datasetName,
                                datasetDescription,
                                typingDataId,
                                isolateDataId,
                                isolateDataKey
                        ))
                );

        when(distanceMatrixService.getDistanceMatrixInfos(any(String.class))).thenReturn(List.of());
        when(treeService.getTreeInfos(any(String.class))).thenReturn(List.of());
        when(treeViewService.getTreeViewInfos(any(String.class))).thenReturn(List.of());

        List<FullDatasetInfo> fullDatasetInfos = datasetService.getFullDatasetInfos(projectId, userId);

        assertEquals(1, fullDatasetInfos.size());
        assertEquals(datasetId, fullDatasetInfos.get(0).getDatasetId());
        assertEquals(datasetName, fullDatasetInfos.get(0).getName());
        assertEquals(datasetDescription, fullDatasetInfos.get(0).getDescription());
        assertEquals(typingDataId, fullDatasetInfos.get(0).getTypingDataId());
        assertEquals(isolateDataId, fullDatasetInfos.get(0).getIsolateDataId());
        assertTrue(fullDatasetInfos.get(0).getDistanceMatrices().isEmpty());
        assertTrue(fullDatasetInfos.get(0).getTrees().isEmpty());
        assertTrue(fullDatasetInfos.get(0).getTreeViews().isEmpty());
    }

    @Test
    void getFullDatasetInfosThrowsWhenProjectDoesNotExist() {
        String projectId = "projectId";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(false);

        assertThrows(ProjectNotFoundException.class, () ->
                datasetService.getFullDatasetInfos(projectId, userId)
        );
    }

    // getFullDatasetInfos
    @Test
    void getFullDatasetInfosWithProjectIdIsSuccessful() {
        String datasetName = "datasetName";
        String datasetDescription = "datasetDescription";
        String typingDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String isolateDataId = null;
        String isolateDataKey = null;
        String projectId = "projectId";
        String datasetId = "datasetId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);

        when(datasetRepository.findAllByProjectId(any(String.class)))
                .thenReturn(
                        List.of(new Dataset(
                                datasetId,
                                projectId,
                                datasetName,
                                datasetDescription,
                                typingDataId,
                                isolateDataId,
                                isolateDataKey
                        ))
                );

        when(distanceMatrixService.getDistanceMatrixInfos(any(String.class))).thenReturn(List.of());
        when(treeService.getTreeInfos(any(String.class))).thenReturn(List.of());
        when(treeViewService.getTreeViewInfos(any(String.class))).thenReturn(List.of());

        List<FullDatasetInfo> fullDatasetInfos = datasetService.getFullDatasetInfos(projectId);

        assertEquals(1, fullDatasetInfos.size());
        assertEquals(datasetId, fullDatasetInfos.get(0).getDatasetId());
        assertEquals(datasetName, fullDatasetInfos.get(0).getName());
        assertEquals(datasetDescription, fullDatasetInfos.get(0).getDescription());
        assertEquals(typingDataId, fullDatasetInfos.get(0).getTypingDataId());
        assertEquals(isolateDataId, fullDatasetInfos.get(0).getIsolateDataId());
        assertTrue(fullDatasetInfos.get(0).getDistanceMatrices().isEmpty());
        assertTrue(fullDatasetInfos.get(0).getTrees().isEmpty());
        assertTrue(fullDatasetInfos.get(0).getTreeViews().isEmpty());
    }

    // deleteDataset
    // void deleteDataset(String projectId, String datasetId, String userId)
    @Test
    void deleteDatasetIsSuccessful() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);

        when(datasetRepository.findByProjectIdAndId(any(String.class), any(String.class)))
                .thenReturn(Optional.of(new Dataset()));

        datasetService.deleteDataset(projectId, datasetId, userId);

        verify(datasetRepository, times(1)).delete(any(Dataset.class));
    }

    // deleteAllByProjectId
    @Test
    void deleteAllByProjectIdIsSuccessful() {
        String projectId = "projectId";

        when(datasetRepository.findAllByProjectId(any(String.class)))
                .thenReturn(List.of(new Dataset()));

        datasetService.deleteAllByProjectId(projectId);

        verify(datasetRepository, times(1)).delete(any(Dataset.class));
    }

    // updateDataset
    @Test
    void updateDatasetIsSuccessful() {
        String datasetName = "datasetName";
        String datasetDescription = "datasetDescription";
        String newDatasetName = "newDatasetName";
        String newDatasetDescription = "newDatasetDescription";
        String projectId = "projectId";
        String datasetId = "datasetId";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);

        when(datasetRepository.findByProjectIdAndId(any(String.class), any(String.class)))
                .thenReturn(Optional.of(new Dataset(
                        datasetId,
                        projectId,
                        datasetName,
                        datasetDescription,
                        null,
                        null,
                        null
                )));

        UpdateDatasetOutput updateDatasetOutput = datasetService.updateDataset(
                newDatasetName,
                newDatasetDescription,
                projectId,
                datasetId,
                userId
        );

        assertEquals(datasetName, updateDatasetOutput.getPreviousName());
        assertEquals(datasetDescription, updateDatasetOutput.getPreviousDescription());
        assertEquals(newDatasetName, updateDatasetOutput.getNewName());
        assertEquals(newDatasetDescription, updateDatasetOutput.getNewDescription());
    }

    @Test
    void updateDatasetThrowsWhenProjectDoesNotExist() {
        String newDatasetName = "newDatasetName";
        String newDatasetDescription = "newDatasetDescription";
        String projectId = "projectId";
        String datasetId = "datasetId";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(false);

        assertThrows(ProjectNotFoundException.class, () ->
                datasetService.updateDataset(
                        newDatasetName,
                        newDatasetDescription,
                        projectId,
                        datasetId,
                        userId
                ));
    }

    @Test
    void updateDatasetThrowsWhenDatasetDoesNotExist() {
        String newDatasetName = "newDatasetName";
        String newDatasetDescription = "newDatasetDescription";
        String projectId = "projectId";
        String datasetId = "datasetId";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);

        when(datasetRepository.findByProjectIdAndId(any(String.class), any(String.class)))
                .thenReturn(Optional.empty());

        assertThrows(DatasetNotFoundException.class, () ->
                datasetService.updateDataset(
                        newDatasetName,
                        newDatasetDescription,
                        projectId,
                        datasetId,
                        userId
                ));
    }

    @ParameterizedTest
    @CsvSource({
            ",",
            "'', description",
            "name,''",
    })
    void updateDatasetThrowsWithInvalidFields(String name, String description) {
        String datasetName = "datasetName";
        String datasetDescription = "datasetDescription";
        String projectId = "projectId";
        String datasetId = "datasetId";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);

        when(datasetRepository.findByProjectIdAndId(any(String.class), any(String.class)))
                .thenReturn(Optional.of(new Dataset(
                        datasetId,
                        projectId,
                        datasetName,
                        datasetDescription,
                        null,
                        null,
                        null
                )));

        assertThrows(InvalidArgumentException.class, () ->
                datasetService.updateDataset(
                        name,
                        description,
                        projectId,
                        datasetId,
                        userId
                ));
    }
}
