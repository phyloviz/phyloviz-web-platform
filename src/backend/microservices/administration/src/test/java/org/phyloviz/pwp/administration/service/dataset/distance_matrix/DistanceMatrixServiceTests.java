package org.phyloviz.pwp.administration.service.dataset.distance_matrix;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.phyloviz.pwp.administration.service.dtos.distance_matrix.DistanceMatrixInfo;
import org.phyloviz.pwp.administration.service.dtos.distance_matrix.UpdateDistanceMatrixOutput;
import org.phyloviz.pwp.administration.service.exceptions.DeniedResourceDeletionException;
import org.phyloviz.pwp.administration.service.project.dataset.distance_matrix.DistanceMatrixServiceImpl;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.DistanceMatrixDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.repository.DistanceMatrixDataRepository;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.repository.DistanceMatrixS3DataRepository;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.repository.specific_data.DistanceMatrixDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.repository.specific_data.DistanceMatrixS3DataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.registry.distance_matrix.DistanceMatrixDataRepositoryFactory;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.DistanceMatrixMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.DistanceMatrixMetadata;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.source.DistanceMatrixSource;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.source.DistanceMatrixSourceFunction;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.source.DistanceMatrixSourceType;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree.TreeMetadataRepository;
import org.phyloviz.pwp.shared.service.exceptions.DatasetNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.DistanceMatrixNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.InvalidArgumentException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DistanceMatrixServiceTests {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private DatasetRepository datasetRepository;

    @Mock
    private DistanceMatrixMetadataRepository distanceMatrixMetadataRepository;

    @Mock
    private TreeMetadataRepository treeMetadataRepository;

    @Mock
    private DistanceMatrixDataRepositoryFactory distanceMatrixDataRepositoryFactory;

    @InjectMocks
    private DistanceMatrixServiceImpl distanceMatrixService;


    // getDistanceMatrixInfos
    @Test
    void getDistanceMatrixInfosIsSuccessful() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String distanceMatrixId = "distanceMatrixId";
        String name = "name";
        DistanceMatrixSourceType sourceType = DistanceMatrixSourceType.FUNCTION;
        DistanceMatrixSource source = new DistanceMatrixSourceFunction("functionName");
        DistanceMatrixDataRepositoryId repositoryId = DistanceMatrixDataRepositoryId.S3;
        DistanceMatrixDataRepositorySpecificData repositorySpecificData = new DistanceMatrixS3DataRepositorySpecificData("url");

        when(distanceMatrixMetadataRepository.findAllByDatasetId(any(String.class)))
                .thenReturn(List.of(
                        new DistanceMatrixMetadata(
                                projectId,
                                datasetId,
                                distanceMatrixId,
                                name,
                                sourceType,
                                source,
                                Map.of(repositoryId, repositorySpecificData)
                        )));

        List<DistanceMatrixInfo> distanceMatrixInfos = distanceMatrixService.getDistanceMatrixInfos(datasetId);

        assertEquals(1, distanceMatrixInfos.size());
        assertEquals(name, distanceMatrixInfos.get(0).getName());
        assertEquals(sourceType, distanceMatrixInfos.get(0).getSourceType());
    }

    // deleteDistanceMatrix
    @Test
    void deleteDistanceMatrixIsSuccessful() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String distanceMatrixId = "distanceMatrixId";
        String name = "name";
        DistanceMatrixSourceType sourceType = DistanceMatrixSourceType.FUNCTION;
        DistanceMatrixSource source = new DistanceMatrixSourceFunction("functionName");
        DistanceMatrixDataRepositoryId repositoryId = DistanceMatrixDataRepositoryId.S3;
        DistanceMatrixDataRepositorySpecificData repositorySpecificData = new DistanceMatrixS3DataRepositorySpecificData("url");

        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(projectId, userId)).thenReturn(true);
        when(datasetRepository.existsByProjectIdAndId(projectId, datasetId)).thenReturn(true);
        when(distanceMatrixMetadataRepository.findByProjectIdAndDatasetIdAndDistanceMatrixId(projectId, datasetId, distanceMatrixId))
                .thenReturn(
                        Optional.of(new DistanceMatrixMetadata(
                                projectId,
                                datasetId,
                                distanceMatrixId,
                                name,
                                sourceType,
                                source,
                                Map.of(repositoryId, repositorySpecificData)
                        )));
        when(treeMetadataRepository.existsByDatasetIdAndDistanceMatrixIdSource(datasetId, distanceMatrixId)).thenReturn(false);

        DistanceMatrixDataRepository rep = mock(DistanceMatrixS3DataRepository.class);

        when(distanceMatrixDataRepositoryFactory.getRepository(DistanceMatrixDataRepositoryId.S3))
                .thenReturn(rep);


        distanceMatrixService.deleteDistanceMatrix(projectId, datasetId, distanceMatrixId, userId);

        verify(distanceMatrixMetadataRepository, times(1)).delete(any(DistanceMatrixMetadata.class));
    }

    @Test
    void deleteDistanceMatrixThrowsWhenProjectDoesNotExist() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String distanceMatrixId = "distanceMatrixId";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(projectId, userId)).thenReturn(false);

        assertThrows(ProjectNotFoundException.class, () ->
                distanceMatrixService.deleteDistanceMatrix(projectId, datasetId, distanceMatrixId, userId)
        );
    }

    @Test
    void deleteDistanceMatrixThrowsWhenDatasetDoesNotExist() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String distanceMatrixId = "distanceMatrixId";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(projectId, userId)).thenReturn(true);
        when(datasetRepository.existsByProjectIdAndId(projectId, datasetId)).thenReturn(false);

        assertThrows(DatasetNotFoundException.class, () ->
                distanceMatrixService.deleteDistanceMatrix(projectId, datasetId, distanceMatrixId, userId)
        );
    }

    @Test
    void deleteDistanceMatrixThrowsWhenDistanceMatrixDoesNotExist() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String distanceMatrixId = "distanceMatrixId";
        String name = "name";
        DistanceMatrixSourceType sourceType = DistanceMatrixSourceType.FUNCTION;
        DistanceMatrixSource source = new DistanceMatrixSourceFunction("functionName");
        DistanceMatrixDataRepositoryId repositoryId = DistanceMatrixDataRepositoryId.S3;
        DistanceMatrixDataRepositorySpecificData repositorySpecificData = new DistanceMatrixS3DataRepositorySpecificData("url");

        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(projectId, userId)).thenReturn(true);
        when(datasetRepository.existsByProjectIdAndId(projectId, datasetId)).thenReturn(true);
        when(distanceMatrixMetadataRepository.findByProjectIdAndDatasetIdAndDistanceMatrixId(projectId, datasetId, distanceMatrixId))
                .thenReturn(
                        Optional.empty());
        assertThrows(DistanceMatrixNotFoundException.class, () ->
                distanceMatrixService.deleteDistanceMatrix(projectId, datasetId, distanceMatrixId, userId)
        );
    }

    @Test
    void deleteDistanceMatrixThrowsWhenDistanceMatrixIsDependencyOfTree() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String distanceMatrixId = "distanceMatrixId";
        String name = "name";
        DistanceMatrixSourceType sourceType = DistanceMatrixSourceType.FUNCTION;
        DistanceMatrixSource source = new DistanceMatrixSourceFunction("functionName");
        DistanceMatrixDataRepositoryId repositoryId = DistanceMatrixDataRepositoryId.S3;
        DistanceMatrixDataRepositorySpecificData repositorySpecificData = new DistanceMatrixS3DataRepositorySpecificData("url");

        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(projectId, userId)).thenReturn(true);
        when(datasetRepository.existsByProjectIdAndId(projectId, datasetId)).thenReturn(true);
        when(distanceMatrixMetadataRepository.findByProjectIdAndDatasetIdAndDistanceMatrixId(projectId, datasetId, distanceMatrixId))
                .thenReturn(Optional.of(new DistanceMatrixMetadata(
                        projectId,
                        datasetId,
                        distanceMatrixId,
                        name,
                        sourceType,
                        source,
                        Map.of(repositoryId, repositorySpecificData)
                )));
        when(treeMetadataRepository.existsByDatasetIdAndDistanceMatrixIdSource(datasetId, distanceMatrixId)).thenReturn(true);

        assertThrows(DeniedResourceDeletionException.class, () ->
                distanceMatrixService.deleteDistanceMatrix(projectId, datasetId, distanceMatrixId, userId)
        );
    }

    // deleteAllByProjectIdAndDatasetId
    @Test
    void deleteAllByProjectIdAndDatasetIdIsSuccessful() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        when(distanceMatrixMetadataRepository.findAllByProjectIdAndDatasetId(any(String.class), any(String.class)))
                .thenReturn(List.of());

        distanceMatrixService.deleteAllByProjectIdAndDatasetId(projectId, datasetId);

        verify(distanceMatrixMetadataRepository, times(0)).delete(any(DistanceMatrixMetadata.class));
    }

    // updateDistanceMatrix
    @Test
    void updateDistanceMatrixIsSuccessful() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String distanceMatrixId = "distanceMatrixId";
        String name = "name";
        DistanceMatrixSourceType sourceType = DistanceMatrixSourceType.FUNCTION;
        DistanceMatrixSource source = new DistanceMatrixSourceFunction("functionName");
        DistanceMatrixDataRepositoryId repositoryId = DistanceMatrixDataRepositoryId.S3;
        DistanceMatrixDataRepositorySpecificData repositorySpecificData = new DistanceMatrixS3DataRepositorySpecificData("url");

        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(projectId, userId)).thenReturn(true);
        when(datasetRepository.existsByProjectIdAndId(projectId, datasetId)).thenReturn(true);
        when(distanceMatrixMetadataRepository.findByProjectIdAndDatasetIdAndDistanceMatrixId(projectId, datasetId, distanceMatrixId))
                .thenReturn(Optional.of(new DistanceMatrixMetadata(
                        projectId,
                        datasetId,
                        distanceMatrixId,
                        name,
                        sourceType,
                        source,
                        Map.of(repositoryId, repositorySpecificData)
                )));

        UpdateDistanceMatrixOutput output = distanceMatrixService.updateDistanceMatrix(name, projectId, datasetId, distanceMatrixId, userId);

        assertEquals(name, output.getPreviousName());
        assertEquals(name, output.getNewName());
    }

    @Test
    void updateDistanceMatrixThrowsWhenProjectDoesNotExist() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String distanceMatrixId = "distanceMatrixId";
        String name = "name";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(projectId, userId)).thenReturn(false);

        assertThrows(ProjectNotFoundException.class, () ->
                distanceMatrixService.updateDistanceMatrix(name, projectId, datasetId, distanceMatrixId, userId)
        );
    }

    @Test
    void updateDistanceMatrixThrowsWhenDistanceMatrixDoesNotExist() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String distanceMatrixId = "distanceMatrixId";
        String name = "name";
        DistanceMatrixSourceType sourceType = DistanceMatrixSourceType.FUNCTION;
        DistanceMatrixSource source = new DistanceMatrixSourceFunction("functionName");
        DistanceMatrixDataRepositoryId repositoryId = DistanceMatrixDataRepositoryId.S3;
        DistanceMatrixDataRepositorySpecificData repositorySpecificData = new DistanceMatrixS3DataRepositorySpecificData("url");

        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(projectId, userId)).thenReturn(true);
        when(datasetRepository.existsByProjectIdAndId(projectId, datasetId)).thenReturn(true);
        when(distanceMatrixMetadataRepository.findByProjectIdAndDatasetIdAndDistanceMatrixId(projectId, datasetId, distanceMatrixId))
                .thenReturn(Optional.empty());

        assertThrows(DistanceMatrixNotFoundException.class, () ->
                distanceMatrixService.updateDistanceMatrix(name, projectId, datasetId, distanceMatrixId, userId)
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void updateDistanceMatrixThrowsWhenNameIsNullOrEmpty(String name) {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String distanceMatrixId = "distanceMatrixId";
        DistanceMatrixSourceType sourceType = DistanceMatrixSourceType.FUNCTION;
        DistanceMatrixSource source = new DistanceMatrixSourceFunction("functionName");
        DistanceMatrixDataRepositoryId repositoryId = DistanceMatrixDataRepositoryId.S3;
        DistanceMatrixDataRepositorySpecificData repositorySpecificData = new DistanceMatrixS3DataRepositorySpecificData("url");

        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(projectId, userId)).thenReturn(true);
        when(datasetRepository.existsByProjectIdAndId(projectId, datasetId)).thenReturn(true);
        when(distanceMatrixMetadataRepository.findByProjectIdAndDatasetIdAndDistanceMatrixId(projectId, datasetId, distanceMatrixId))
                .thenReturn(Optional.of(new DistanceMatrixMetadata(
                        projectId,
                        datasetId,
                        distanceMatrixId,
                        name,
                        sourceType,
                        source,
                        Map.of(repositoryId, repositorySpecificData)
                )));

        assertThrows(InvalidArgumentException.class, () ->
                distanceMatrixService.updateDistanceMatrix(name, projectId, datasetId, distanceMatrixId, userId)
        );
    }
}
