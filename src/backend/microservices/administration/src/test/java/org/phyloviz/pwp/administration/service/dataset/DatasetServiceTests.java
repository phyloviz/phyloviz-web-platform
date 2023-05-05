package org.phyloviz.pwp.administration.service.dataset;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.phyloviz.pwp.administration.service.dtos.dataset.CreateDatasetOutput;
import org.phyloviz.pwp.administration.service.project.dataset.DatasetService;
import org.phyloviz.pwp.administration.service.project.dataset.distance_matrix.DistanceMatrixService;
import org.phyloviz.pwp.administration.service.project.dataset.tree.TreeService;
import org.phyloviz.pwp.administration.service.project.dataset.tree_view.TreeViewService;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.IsolateDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.TypingDataMetadataRepository;
import org.phyloviz.pwp.shared.service.exceptions.InvalidArgumentException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
    void createDatasetIsSuccessfull() {
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
    void createDatasetThrowsWhenProjectDoesNotExist() {
        String datasetName = "datasetName";
        String datasetDescription = "datasetDescription";
        String typingDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String isolateDataId = "isolateDataId";
        String isolateDataKey = "isolateDataKey";
        String projectId = "projectId";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(false);

        assertThrows(ProjectNotFoundException.class, () -> {
            datasetService.createDataset(
                    datasetName,
                    datasetDescription,
                    typingDataId,
                    isolateDataId,
                    isolateDataKey,
                    projectId,
                    userId
            );
        });
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

        assertThrows(InvalidArgumentException.class, () -> {
            datasetService.createDataset(
                    datasetName,
                    datasetDescription,
                    typingDataId,
                    isolateDataId,
                    isolateDataKey,
                    projectId,
                    userId
            );
        });
    }

    // getFullDatasetInfo

    // getFullDatasetInfos

    // getFullDatasetInfos

    // deleteDataset

    // deleteAllByProjectId

    // updateDataset
}
