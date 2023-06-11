package org.phyloviz.pwp.administration.service.dataset.tree;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.runner.RunWith;
import org.phyloviz.pwp.administration.service.dtos.tree.TreeInfo;
import org.phyloviz.pwp.administration.service.dtos.tree.UpdateTreeOutput;
import org.phyloviz.pwp.administration.service.exceptions.DeniedResourceDeletionException;
import org.phyloviz.pwp.administration.service.project.dataset.tree.TreeService;
import org.phyloviz.pwp.shared.repository.data.registry.tree.TreeDataRepositoryFactory;
import org.phyloviz.pwp.shared.repository.data.tree.TreeDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.tree.repository.specific_data.TreeDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.tree.repository.specific_data.TreeS3DataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree.TreeMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSource;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSourceFile;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSourceType;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.TreeViewMetadataRepository;
import org.phyloviz.pwp.shared.service.exceptions.DatasetNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.InvalidArgumentException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.TreeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class TreeServiceTests {

    @MockBean
    private ProjectRepository projectRepository;

    @MockBean
    private DatasetRepository datasetRepository;

    @MockBean
    private TreeMetadataRepository treeMetadataRepository;

    @MockBean
    private TreeViewMetadataRepository treeViewMetadataService;

    @MockBean
    private TreeDataRepositoryFactory treeDataRepositoryFactory;

    @Autowired
    private TreeService treeService;


    // getTreeInfos
    @Test
    void getTreeInfosIsSuccessful() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String treeId = "treeId";
        String name = "name";
        TreeSourceType sourceType = TreeSourceType.FILE;
        TreeSource source = new TreeSourceFile("fileType", "fileName");
        TreeDataRepositoryId repositoryId = TreeDataRepositoryId.S3;
        TreeDataRepositorySpecificData repositorySpecificData = new TreeS3DataRepositorySpecificData("url");

        when(treeMetadataRepository.findAllByDatasetId(any(String.class)))
                .thenReturn(List.of(
                        new TreeMetadata(
                                projectId,
                                datasetId,
                                treeId,
                                name,
                                sourceType,
                                source,
                                Map.of(repositoryId, repositorySpecificData)
                        )
                ));

        List<TreeInfo> treeInfos = treeService.getTreeInfos(datasetId);

        assertEquals(1, treeInfos.size());
        assertEquals(name, treeInfos.get(0).getName());
        assertEquals(sourceType, treeInfos.get(0).getSourceType());
    }

    // deleteTree
    @Test
    void deleteTreeIsSuccessful() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String treeId = "treeId";
        String name = "name";
        TreeSourceType sourceType = TreeSourceType.FILE;
        TreeSource source = new TreeSourceFile("fileType", "fileName");
        TreeDataRepositoryId repositoryId = TreeDataRepositoryId.S3;
        TreeDataRepositorySpecificData repositorySpecificData = new TreeS3DataRepositorySpecificData("url");

        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(datasetRepository.existsByProjectIdAndId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(treeMetadataRepository.findByProjectIdAndDatasetIdAndTreeId(any(String.class), any(String.class), any(String.class)))
                .thenReturn(Optional.of(new TreeMetadata(
                                projectId,
                                datasetId,
                                treeId,
                                name,
                                sourceType,
                                source,
                                Map.of(repositoryId, repositorySpecificData)
                        ))
                );
        when(treeViewMetadataService.existsByDatasetIdAndTreeIdSource(any(String.class), any(String.class)))
                .thenReturn(false);

        treeService.deleteTree(projectId, datasetId, treeId, userId);

        verify(treeMetadataRepository, times(0)).delete(any(TreeMetadata.class));
    }

    @Test
    void deleteTreeThrowsWhenProjectDoesNotExist() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String treeId = "treeId";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(false);

        assertThrows(ProjectNotFoundException.class, () ->
                treeService.deleteTree(projectId, datasetId, treeId, userId)
        );
    }

    @Test
    void deleteTreeThrowsWhenDatasetDoesNotExist() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String treeId = "treeId";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(datasetRepository.existsByProjectIdAndId(any(String.class), any(String.class)))
                .thenReturn(false);

        assertThrows(DatasetNotFoundException.class, () ->
                treeService.deleteTree(projectId, datasetId, treeId, userId)
        );
    }

    @Test
    void deleteTreeThrowsWhenTreeDoesNotExist() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String treeId = "treeId";
        String name = "name";
        TreeSourceType sourceType = TreeSourceType.FILE;
        TreeSource source = new TreeSourceFile("fileType", "fileName");
        TreeDataRepositoryId repositoryId = TreeDataRepositoryId.S3;
        TreeDataRepositorySpecificData repositorySpecificData = new TreeS3DataRepositorySpecificData("url");

        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(datasetRepository.existsByProjectIdAndId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(treeMetadataRepository.findByProjectIdAndDatasetIdAndTreeId(any(String.class), any(String.class), any(String.class)))
                .thenReturn(Optional.of(new TreeMetadata(
                                projectId,
                                datasetId,
                                treeId,
                                name,
                                sourceType,
                                source,
                                Map.of(repositoryId, repositorySpecificData)
                        ))
                );

        assertThrows(TreeNotFoundException.class, () ->
                treeService.deleteTree(projectId, datasetId, treeId, userId)
        );
    }

    @Test
    void deleteTreeThrowsWhenTreeViewExists() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String treeId = "treeId";
        String name = "name";
        TreeSourceType sourceType = TreeSourceType.FILE;
        TreeSource source = new TreeSourceFile("fileType", "fileName");
        TreeDataRepositoryId repositoryId = TreeDataRepositoryId.S3;
        TreeDataRepositorySpecificData repositorySpecificData = new TreeS3DataRepositorySpecificData("url");

        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(datasetRepository.existsByProjectIdAndId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(treeMetadataRepository.findByProjectIdAndDatasetIdAndTreeId(any(String.class), any(String.class), any(String.class)))
                .thenReturn(Optional.of(new TreeMetadata(
                                projectId,
                                datasetId,
                                treeId,
                                name,
                                sourceType,
                                source,
                                Map.of(repositoryId, repositorySpecificData)
                        ))
                );
        when(treeViewMetadataService.existsByDatasetIdAndTreeIdSource(any(String.class), any(String.class)))
                .thenReturn(true);

        assertThrows(DeniedResourceDeletionException.class, () ->
                treeService.deleteTree(projectId, datasetId, treeId, userId)
        );
    }

    // deleteAllByProjectIdAndDatasetId
    @Test
    void deleteAllByProjectIdAndDatasetIdIsSuccessful() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        when(treeMetadataRepository.findAllByProjectIdAndDatasetId(any(String.class), any(String.class)))
                .thenReturn(List.of());

        treeService.deleteAllByProjectIdAndDatasetId(projectId, datasetId);

        verify(treeMetadataRepository, times(0)).delete(any(TreeMetadata.class));
    }

    // updateTree
    @Test
    void updateTreeIsSuccessful() {
        String newName = "name";
        String projectId = "projectId";
        String userId = "userId";
        String datasetId = "datasetId";
        String treeId = "treeId";
        String name = "name";
        TreeSourceType sourceType = TreeSourceType.FILE;
        TreeSource source = new TreeSourceFile("fileType", "fileName");
        TreeDataRepositoryId repositoryId = TreeDataRepositoryId.S3;
        TreeDataRepositorySpecificData repositorySpecificData = new TreeS3DataRepositorySpecificData("url");

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(datasetRepository.existsByProjectIdAndId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(treeMetadataRepository.findByProjectIdAndDatasetIdAndTreeId(any(String.class), any(String.class), any(String.class)))
                .thenReturn(Optional.of(new TreeMetadata(
                        projectId,
                        datasetId,
                        treeId,
                        name,
                        sourceType,
                        source,
                        Map.of(repositoryId, repositorySpecificData)
                )));

        UpdateTreeOutput updateTreeOutput = treeService.updateTree(newName, projectId, datasetId, treeId, userId);

        assertEquals(newName, updateTreeOutput.getNewName());
        assertEquals(name, updateTreeOutput.getPreviousName());
    }

    @Test
    void updateTreeThrowsWhenProjectDoesNotExist() {
        String name = "name";
        String projectId = "projectId";
        String datasetId = "datasetId";
        String treeId = "treeId";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(false);

        assertThrows(ProjectNotFoundException.class, () ->
                treeService.updateTree(name, projectId, datasetId, treeId, userId)
        );
    }

    @Test
    void updateTreeThrowsWhenTreeDoesNotExist() {
        String name = "name";
        String projectId = "projectId";
        String datasetId = "datasetId";
        String treeId = "treeId";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(treeMetadataRepository.findByProjectIdAndDatasetIdAndTreeId(any(String.class), any(String.class), any(String.class)))
                .thenReturn(Optional.empty());

        assertThrows(TreeNotFoundException.class, () ->
                treeService.updateTree(name, projectId, datasetId, treeId, userId)
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void updateTreeThrowsWhenNameIsNullOfEmpty(String name) {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String treeId = "treeId";
        TreeSourceType sourceType = TreeSourceType.FILE;
        TreeSource source = new TreeSourceFile("fileType", "fileName");
        TreeDataRepositoryId repositoryId = TreeDataRepositoryId.S3;
        TreeDataRepositorySpecificData repositorySpecificData = new TreeS3DataRepositorySpecificData("url");

        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(datasetRepository.existsByProjectIdAndId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(treeMetadataRepository.findByProjectIdAndDatasetIdAndTreeId(any(String.class), any(String.class), any(String.class)))
                .thenReturn(Optional.of(new TreeMetadata(
                        projectId,
                        datasetId,
                        treeId,
                        name,
                        sourceType,
                        source,
                        Map.of(repositoryId, repositorySpecificData)
                )));

        assertThrows(InvalidArgumentException.class, () ->
                treeService.updateTree(name, projectId, datasetId, treeId, userId)
        );
    }
}
