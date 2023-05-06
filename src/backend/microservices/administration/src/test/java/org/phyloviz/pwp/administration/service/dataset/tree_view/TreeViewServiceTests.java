package org.phyloviz.pwp.administration.service.dataset.tree_view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.runner.RunWith;
import org.phyloviz.pwp.administration.service.dtos.tree_view.TreeViewInfo;
import org.phyloviz.pwp.administration.service.dtos.tree_view.UpdateTreeViewOutput;
import org.phyloviz.pwp.administration.service.project.dataset.tree_view.TreeViewService;
import org.phyloviz.pwp.shared.repository.data.registry.tree_view.TreeViewDataRepositoryFactory;
import org.phyloviz.pwp.shared.repository.data.tree_view.TreeViewDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.specific_data.TreeViewDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.specific_data.TreeViewS3DataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.TreeViewMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.TreeViewMetadata;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.source.TreeViewSource;
import org.phyloviz.pwp.shared.service.exceptions.DatasetNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.InvalidArgumentException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.TreeViewNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class TreeViewServiceTests {

    @MockBean
    private ProjectRepository projectRepository;

    @MockBean
    private DatasetRepository datasetRepository;

    @MockBean
    private TreeViewMetadataRepository treeViewMetadataRepository;

    @MockBean
    private TreeViewDataRepositoryFactory treeViewDataRepositoryFactory;

    @Autowired
    private TreeViewService treeViewService;


    // getTreeViewInfos
    @Test
    void getTreeViewInfosIsSuccessful() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String treeViewId = "treeViewId";
        String name = "name";
        String layout = "layout";
        TreeViewSource source = new TreeViewSource("treeId", null, null);
        TreeViewDataRepositoryId repositoryId = TreeViewDataRepositoryId.S3;
        TreeViewDataRepositorySpecificData repositorySpecificData = new TreeViewS3DataRepositorySpecificData("url");

        when(treeViewMetadataRepository.findAllByDatasetId(any(String.class)))
                .thenReturn(List.of(
                        new TreeViewMetadata(
                                projectId,
                                datasetId,
                                treeViewId,
                                name,
                                layout,
                                source,
                                repositoryId,
                                repositorySpecificData
                        )
                ));

        List<TreeViewInfo> treeViewInfos = treeViewService.getTreeViewInfos(datasetId);

        assertEquals(1, treeViewInfos.size());
        assertEquals(name, treeViewInfos.get(0).getName());
        assertEquals(layout, treeViewInfos.get(0).getLayout());
    }

    // deleteTreeView
    @Test
    void deleteTreeViewIsSuccessful() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String treeViewId = "treeViewId";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(datasetRepository.existsByProjectIdAndId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(treeViewMetadataRepository.existsByProjectIdAndDatasetIdAndTreeViewId(any(String.class), any(String.class), any(String.class)))
                .thenReturn(true);
        when(treeViewMetadataRepository.findAllByTreeViewId(any(String.class)))
                .thenReturn(List.of());

        treeViewService.deleteTreeView(projectId, datasetId, treeViewId, userId);

        verify(treeViewMetadataRepository, times(0)).delete(any(TreeViewMetadata.class));
    }

    @Test
    void deleteTreeViewThrowsWhenProjectDoesNotExist() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String treeViewId = "treeViewId";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(false);

        assertThrows(ProjectNotFoundException.class, () ->
                treeViewService.deleteTreeView(projectId, datasetId, treeViewId, userId)
        );
    }

    @Test
    void deleteTreeViewThrowsWhenDatasetDoesNotExist() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String treeViewId = "treeViewId";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(datasetRepository.existsByProjectIdAndId(any(String.class), any(String.class)))
                .thenReturn(false);

        assertThrows(DatasetNotFoundException.class, () ->
                treeViewService.deleteTreeView(projectId, datasetId, treeViewId, userId)
        );
    }

    @Test
    void deleteTreeViewThrowsWhenTreeViewDoesNotExist() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String treeViewId = "treeViewId";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(datasetRepository.existsByProjectIdAndId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(treeViewMetadataRepository.existsByProjectIdAndDatasetIdAndTreeViewId(any(String.class), any(String.class), any(String.class)))
                .thenReturn(false);

        assertThrows(TreeViewNotFoundException.class, () ->
                treeViewService.deleteTreeView(projectId, datasetId, treeViewId, userId)
        );
    }

    // deleteAllByProjectIdAndDatasetId
    @Test
    void deleteAllByProjectIdAndDatasetIdIsSuccessful() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        when(treeViewMetadataRepository.findAllByProjectIdAndDatasetId(any(String.class), any(String.class)))
                .thenReturn(List.of());

        treeViewService.deleteAllByProjectIdAndDatasetId(projectId, datasetId);

        verify(treeViewMetadataRepository, times(0)).delete(any(TreeViewMetadata.class));
    }

    // deleteTreeView
    @Test
    void deleteTreeViewByIdIsSuccessful() {
        String treeViewId = "treeViewId";

        when(treeViewMetadataRepository.findAllByTreeViewId(any(String.class)))
                .thenReturn(List.of());

        treeViewService.deleteTreeView(treeViewId);

        verify(treeViewMetadataRepository, times(0)).delete(any(TreeViewMetadata.class));
    }

    // updateTreeView
    @Test
    void updateTreeViewIsSuccessful() {
        String newName = "newName";
        String projectId = "projectId";
        String userId = "userId";
        String datasetId = "datasetId";
        String treeViewId = "treeViewId";
        String name = "name";
        String layout = "layout";
        TreeViewSource source = new TreeViewSource("treeId", null, null);
        TreeViewDataRepositoryId repositoryId = TreeViewDataRepositoryId.S3;
        TreeViewDataRepositorySpecificData repositorySpecificData = new TreeViewS3DataRepositorySpecificData("url");

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(treeViewMetadataRepository.existsByProjectIdAndDatasetIdAndTreeViewId(any(String.class), any(String.class), any(String.class)))
                .thenReturn(true);
        when(treeViewMetadataRepository.findAnyByProjectIdAndDatasetIdAndTreeViewId(any(String.class), any(String.class), any(String.class)))
                .thenReturn(Optional.of(
                        new TreeViewMetadata(
                                projectId,
                                datasetId,
                                treeViewId,
                                name,
                                layout,
                                source,
                                repositoryId,
                                repositorySpecificData
                        )
                ));

        UpdateTreeViewOutput updateTreeViewOutput = treeViewService.updateTreeView(newName, projectId, datasetId, treeViewId, userId);

        assertEquals(newName, updateTreeViewOutput.getNewName());
        assertEquals(name, updateTreeViewOutput.getPreviousName());
    }

    @Test
    void updateTreeViewThrowsWhenProjectDoesNotExist() {
        String name = "name";
        String projectId = "projectId";
        String datasetId = "datasetId";
        String treeViewId = "treeViewId";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(false);

        assertThrows(ProjectNotFoundException.class, () ->
                treeViewService.updateTreeView(name, projectId, datasetId, treeViewId, userId)
        );
    }

    @Test
    void updateTreeViewThrowsWhenTreeViewDoesNotExist() {
        String name = "name";
        String projectId = "projectId";
        String datasetId = "datasetId";
        String treeViewId = "treeViewId";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(treeViewMetadataRepository.existsByProjectIdAndDatasetIdAndTreeViewId(any(String.class), any(String.class), any(String.class)))
                .thenReturn(false);

        assertThrows(TreeViewNotFoundException.class, () ->
                treeViewService.updateTreeView(name, projectId, datasetId, treeViewId, userId)
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void updateTreeViewThrowsWhenNameIsNullOfEmpty(String name) {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String treeViewId = "treeViewId";
        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(treeViewMetadataRepository.existsByProjectIdAndDatasetIdAndTreeViewId(any(String.class), any(String.class), any(String.class)))
                .thenReturn(true);
        when(treeViewMetadataRepository.findAnyByProjectIdAndDatasetIdAndTreeViewId(projectId, datasetId, treeViewId))
                .thenReturn(Optional.of(new TreeViewMetadata()));

        assertThrows(InvalidArgumentException.class, () ->
                treeViewService.updateTreeView(name, projectId, datasetId, treeViewId, userId)
        );
    }
}
