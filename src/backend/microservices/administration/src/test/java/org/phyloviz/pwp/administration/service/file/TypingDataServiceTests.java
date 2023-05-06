package org.phyloviz.pwp.administration.service.file;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.runner.RunWith;
import org.phyloviz.pwp.administration.service.dtos.files.typing_data.TypingDataInfo;
import org.phyloviz.pwp.administration.service.dtos.files.typing_data.UpdateTypingDataOutput;
import org.phyloviz.pwp.administration.service.exceptions.DeniedFileDeletionException;
import org.phyloviz.pwp.administration.service.project.file.TypingDataService;
import org.phyloviz.pwp.shared.repository.data.registry.typing_data.TypingDataDataRepositoryFactory;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.TypingDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.documents.TypingDataMetadata;
import org.phyloviz.pwp.shared.service.exceptions.InvalidArgumentException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.TypingDataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class TypingDataServiceTests {

    @MockBean
    private ProjectRepository projectRepository;

    @MockBean
    private DatasetRepository datasetRepository;

    @MockBean
    private TypingDataMetadataRepository typingDataMetadataRepository;

    @MockBean
    private TypingDataDataRepositoryFactory typingDataDataRepositoryFactory;

    @Autowired
    private TypingDataService typingDataService;

    // getTypingDataInfos
    @Test
    void getTypingDataInfosIsSuccessful() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";

        when(typingDataMetadataRepository.findAllByProjectId(any(String.class)))
                .thenReturn(List.of());

        List<TypingDataInfo> typingDataInfos = typingDataService.getTypingDataInfos(projectId);
        assertTrue(typingDataInfos.isEmpty());
    }

    // deleteTypingData
    @Test
    void deleteTypingDataIsSuccessful() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String typingDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String userId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(typingDataMetadataRepository.existsByProjectIdAndTypingDataId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(datasetRepository.existsByProjectIdAndTypingDataId(any(String.class), any(String.class)))
                .thenReturn(false);
        when(typingDataMetadataRepository.findAllByTypingDataId(any(String.class)))
                .thenReturn(List.of());

        typingDataService.deleteTypingData(projectId, typingDataId, userId);

        verify(typingDataMetadataRepository, times(0)).delete(any(TypingDataMetadata.class));
    }

    @Test
    void deleteTypingDataThrowsExceptionWhenProjectDoesNotExist() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String typingDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String userId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(false);

        assertThrows(ProjectNotFoundException.class, () ->
                typingDataService.deleteTypingData(projectId, typingDataId, userId)
        );
    }

    @Test
    void deleteTypingDataThrowsExceptionWhenTypingDataDoesNotExist() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String typingDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String userId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(typingDataMetadataRepository.existsByProjectIdAndTypingDataId(any(String.class), any(String.class)))
                .thenReturn(false);

        assertThrows(TypingDataNotFoundException.class, () ->
                typingDataService.deleteTypingData(projectId, typingDataId, userId)
        );
    }

    @Test
    void deleteTypingDataThrowsExceptionWhenTypingDataIsUsedByDataset() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String typingDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String userId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(typingDataMetadataRepository.existsByProjectIdAndTypingDataId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(datasetRepository.existsByProjectIdAndTypingDataId(any(String.class), any(String.class)))
                .thenReturn(true);

        assertThrows(DeniedFileDeletionException.class, () ->
                typingDataService.deleteTypingData(projectId, typingDataId, userId)
        );
    }


    // deleteAllByProjectId
    @Test
    void deleteAllByProjectIdIsSuccessful() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";

        when(typingDataMetadataRepository.findAllByProjectId(any(String.class)))
                .thenReturn(List.of());

        typingDataService.deleteAllByProjectId(projectId);

        verify(typingDataMetadataRepository, times(0)).delete(any(TypingDataMetadata.class));
    }


    // deleteTypingData
    @Test
    void deleteTypingDataWithIdIsSuccessful() {
        String typingDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";

        when(typingDataMetadataRepository.findAllByTypingDataId(any(String.class)))
                .thenReturn(List.of());

        typingDataService.deleteTypingData(typingDataId);

        verify(typingDataMetadataRepository, times(0)).delete(any(TypingDataMetadata.class));
    }


    // updateTypingData
    @Test
    void updateTypingDataIsSuccessful() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String typingDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String userId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String newName = "newName";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(typingDataMetadataRepository.existsByProjectIdAndTypingDataId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(typingDataMetadataRepository.findAnyByProjectIdAndTypingDataId(any(String.class), any(String.class)))
                .thenReturn(Optional.of(new TypingDataMetadata()));
        when(typingDataMetadataRepository.findAllByProjectIdAndTypingDataId(any(String.class), any(String.class)))
                .thenReturn(List.of());
        when(typingDataMetadataRepository.save(any(TypingDataMetadata.class)))
                .thenReturn(new TypingDataMetadata());
        when(typingDataMetadataRepository.save(any(TypingDataMetadata.class)))
                .thenReturn(new TypingDataMetadata());

        UpdateTypingDataOutput updateTypingDataOutput = typingDataService.updateTypingData(newName, projectId, typingDataId, userId);
        assertEquals(newName, updateTypingDataOutput.getNewName());
        assertNull(updateTypingDataOutput.getPreviousName());
    }

    @Test
    void updateTypingDataThrowsWhenProjectDoesNotExist() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String typingDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String userId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String newName = "newName";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(false);

        assertThrows(ProjectNotFoundException.class, () ->
                typingDataService.updateTypingData(newName, projectId, typingDataId, userId)
        );
    }

    @Test
    void updateTypingDataThrowsWhenTypingDataDoesNotExist() {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String typingDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String userId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String newName = "newName";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(typingDataMetadataRepository.existsByProjectIdAndTypingDataId(any(String.class), any(String.class)))
                .thenReturn(false);

        assertThrows(TypingDataNotFoundException.class, () ->
                typingDataService.updateTypingData(newName, projectId, typingDataId, userId)
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void updateTypingDataThrowsWhenNewNameIsInvalid(String newName) {
        String projectId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String typingDataId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";
        String userId = "ec7bae63-3238-4044-8d03-e2d9911f50f8";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(typingDataMetadataRepository.existsByProjectIdAndTypingDataId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(typingDataMetadataRepository.findAnyByProjectIdAndTypingDataId(any(String.class), any(String.class)))
                .thenReturn(Optional.of(new TypingDataMetadata()));

        assertThrows(InvalidArgumentException.class, () ->
                typingDataService.updateTypingData(newName, projectId, typingDataId, userId)
        );
    }
}
