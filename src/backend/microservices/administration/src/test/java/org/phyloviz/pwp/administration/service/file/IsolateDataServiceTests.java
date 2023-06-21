package org.phyloviz.pwp.administration.service.file;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.phyloviz.pwp.administration.service.dtos.files.isolate_data.IsolateDataInfo;
import org.phyloviz.pwp.administration.service.dtos.files.isolate_data.UpdateIsolateDataOutput;
import org.phyloviz.pwp.administration.service.exceptions.DeniedFileDeletionException;
import org.phyloviz.pwp.administration.service.project.file.IsolateDataServiceImpl;
import org.phyloviz.pwp.shared.repository.data.isolate_data.IsolateDataDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.IsolateDataDataRepository;
import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.IsolateDataS3DataRepository;
import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.specific_data.IsolateDataS3DataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.registry.isolate_data.IsolateDataDataRepositoryFactory;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.IsolateDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.IsolateDataMetadata;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.service.exceptions.InvalidArgumentException;
import org.phyloviz.pwp.shared.service.exceptions.IsolateDataNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IsolateDataServiceTests {
    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private DatasetRepository datasetRepository;

    @Mock
    private IsolateDataMetadataRepository isolateDataMetadataRepository;

    @Mock
    private IsolateDataDataRepositoryFactory isolateDataDataRepositoryFactory;

    @InjectMocks
    private IsolateDataServiceImpl isolateDataService;

    // getIsolateDataInfos
    @Test
    void getIsolateDataInfosIsSuccessful() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";

        when(isolateDataMetadataRepository.findAllByProjectId(any(String.class)))
                .thenReturn(List.of());

        List<IsolateDataInfo> isolateDataInfos = isolateDataService.getIsolateDataInfos(projectId);
        assertTrue(isolateDataInfos.isEmpty());
    }

    // deleteIsolateData
    @Test
    void deleteIsolateDataIsSuccessful() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String isolateDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String userId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String isolateDataMetadataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(datasetRepository.existsByProjectIdAndIsolateDataId(any(String.class), any(String.class)))
                .thenReturn(false);
        when(isolateDataMetadataRepository.findByProjectIdAndIsolateDataId(any(String.class), any(String.class)))
                .thenReturn(Optional.of(
                        new IsolateDataMetadata(isolateDataMetadataId, projectId, isolateDataId, List.of(), "name", Map.of(IsolateDataDataRepositoryId.S3, new IsolateDataS3DataRepositorySpecificData())
                        )));

        IsolateDataDataRepository rep = mock(IsolateDataS3DataRepository.class);

        when(isolateDataDataRepositoryFactory.getRepository(IsolateDataDataRepositoryId.S3))
                .thenReturn(rep);

        isolateDataService.deleteIsolateData(projectId, isolateDataId, userId);

        verify(isolateDataMetadataRepository, times(1)).delete(any(IsolateDataMetadata.class));
    }

    @Test
    void deleteIsolateDataThrowsExceptionWhenProjectDoesNotExist() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String isolateDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String userId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(false);

        assertThrows(ProjectNotFoundException.class, () ->
                isolateDataService.deleteIsolateData(projectId, isolateDataId, userId)
        );
    }

    @Test
    void deleteIsolateDataThrowsExceptionWhenIsolateDataDoesNotExist() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String isolateDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String userId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);

        assertThrows(IsolateDataNotFoundException.class, () ->
                isolateDataService.deleteIsolateData(projectId, isolateDataId, userId)
        );
    }

    @Test
    void deleteIsolateDataThrowsExceptionWhenIsolateDataIsUsedByDataset() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String isolateDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String userId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(datasetRepository.existsByProjectIdAndIsolateDataId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(isolateDataMetadataRepository.findByProjectIdAndIsolateDataId(any(String.class), any(String.class)))
                .thenReturn(Optional.of(new IsolateDataMetadata()));

        assertThrows(DeniedFileDeletionException.class, () ->
                isolateDataService.deleteIsolateData(projectId, isolateDataId, userId)
        );
    }


    // deleteAllByProjectId
    @Test
    void deleteAllByProjectIdIsSuccessful() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";

        when(isolateDataMetadataRepository.findAllByProjectId(any(String.class)))
                .thenReturn(List.of());

        isolateDataService.deleteAllByProjectId(projectId);

        verify(isolateDataMetadataRepository, times(0)).delete(any(IsolateDataMetadata.class));
    }

    // updateIsolateData
    @Test
    void updateIsolateDataIsSuccessful() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String isolateDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String userId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String newName = "newName";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(isolateDataMetadataRepository.findByProjectIdAndIsolateDataId(any(String.class), any(String.class)))
                .thenReturn(Optional.of(new IsolateDataMetadata()));
        when(isolateDataMetadataRepository.save(any(IsolateDataMetadata.class)))
                .thenReturn(new IsolateDataMetadata());

        UpdateIsolateDataOutput updateIsolateDataOutput = isolateDataService.updateIsolateData(newName, projectId, isolateDataId, userId);
        assertEquals(newName, updateIsolateDataOutput.getNewName());
        assertNull(updateIsolateDataOutput.getPreviousName());
    }

    @Test
    void updateIsolateDataThrowsWhenProjectDoesNotExist() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String isolateDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String userId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String newName = "newName";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(false);

        assertThrows(ProjectNotFoundException.class, () ->
                isolateDataService.updateIsolateData(newName, projectId, isolateDataId, userId)
        );
    }

    @Test
    void updateIsolateDataThrowsWhenIsolateDataDoesNotExist() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String isolateDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String userId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String newName = "newName";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);

        assertThrows(IsolateDataNotFoundException.class, () ->
                isolateDataService.updateIsolateData(newName, projectId, isolateDataId, userId)
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void updateIsolateDataThrowsWhenNewNameIsInvalid(String newName) {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String isolateDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String userId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(isolateDataMetadataRepository.findByProjectIdAndIsolateDataId(any(String.class), any(String.class)))
                .thenReturn(Optional.of(new IsolateDataMetadata()));

        assertThrows(InvalidArgumentException.class, () ->
                isolateDataService.updateIsolateData(newName, projectId, isolateDataId, userId)
        );
    }
}
