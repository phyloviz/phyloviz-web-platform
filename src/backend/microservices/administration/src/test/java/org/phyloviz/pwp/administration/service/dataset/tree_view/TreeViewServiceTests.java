package org.phyloviz.pwp.administration.service.dataset.tree_view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.phyloviz.pwp.administration.service.dtos.tree_view.TreeViewInfo;
import org.phyloviz.pwp.administration.service.dtos.tree_view.UpdateTreeViewOutput;
import org.phyloviz.pwp.administration.service.project.dataset.tree_view.TreeViewService;
import org.phyloviz.pwp.administration.service.project.dataset.tree_view.TreeViewServiceImpl;
import org.phyloviz.pwp.shared.repository.data.registry.tree_view.TreeViewDataRepositoryFactory;
import org.phyloviz.pwp.shared.repository.data.tree_view.TreeViewDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.TreeViewDataRepository;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.TreeViewS3DataRepository;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.specific_data.TreeViewDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.specific_data.TreeViewS3DataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.typing_data.TypingDataDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.typing_data.repository.TypingDataDataRepository;
import org.phyloviz.pwp.shared.repository.data.typing_data.repository.TypingDataS3DataRepository;
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
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TreeViewServiceTests {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private DatasetRepository datasetRepository;

    @Mock
    private TreeViewMetadataRepository treeViewMetadataRepository;

    @Mock
    private TreeViewDataRepositoryFactory treeViewDataRepositoryFactory;

    @InjectMocks
    private TreeViewServiceImpl treeViewService;


    // getTreeViewInfos
    @Test
    void getTreeViewInfosIsSuccessful() {
        String projectId = "projectId";
        String datasetId = "datasetId";
        String treeViewId = "treeViewId";
        String name = "name";
        String layout = "layout";
        TreeViewSource source = new TreeViewSource("treeId");
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
                                Map.of(repositoryId, repositorySpecificData)
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
        String name = "name";
        String layout = "layout";
        TreeViewSource source = new TreeViewSource("treeId");
        TreeViewDataRepositoryId repositoryId = TreeViewDataRepositoryId.S3;
        TreeViewDataRepositorySpecificData repositorySpecificData = new TreeViewS3DataRepositorySpecificData("url");

        String userId = "userId";

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(datasetRepository.existsByProjectIdAndId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(treeViewMetadataRepository.findByProjectIdAndDatasetIdAndTreeViewId(any(String.class), any(String.class), any(String.class)))
                .thenReturn(Optional.of(new TreeViewMetadata(
                        projectId,
                        datasetId,
                        treeViewId,
                        name,
                        layout,
                        source,
                        Map.of(repositoryId, repositorySpecificData)
                )));

        TreeViewDataRepository rep =  mock(TreeViewS3DataRepository.class);

        when(treeViewDataRepositoryFactory.getRepository(repositoryId))
                .thenReturn(rep);

        treeViewService.deleteTreeView(projectId, datasetId, treeViewId, userId);

        verify(treeViewMetadataRepository, times(1)).delete(any(TreeViewMetadata.class));
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
        when(treeViewMetadataRepository.findByProjectIdAndDatasetIdAndTreeViewId(any(String.class), any(String.class), any(String.class)))
                .thenReturn(Optional.empty());

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
        TreeViewSource source = new TreeViewSource("treeId");
        TreeViewDataRepositoryId repositoryId = TreeViewDataRepositoryId.S3;
        TreeViewDataRepositorySpecificData repositorySpecificData = new TreeViewS3DataRepositorySpecificData("url");

        when(projectRepository.existsByIdAndOwnerId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(datasetRepository.existsByProjectIdAndId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(treeViewMetadataRepository.findByProjectIdAndDatasetIdAndTreeViewId(any(String.class), any(String.class), any(String.class)))
                .thenReturn(Optional.of(
                        new TreeViewMetadata(
                                projectId,
                                datasetId,
                                treeViewId,
                                name,
                                layout,
                                source,
                                Map.of(repositoryId, repositorySpecificData)
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
        when(datasetRepository.existsByProjectIdAndId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(treeViewMetadataRepository.findByProjectIdAndDatasetIdAndTreeViewId(any(String.class), any(String.class), any(String.class)))
                .thenReturn(Optional.empty());

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
        when(datasetRepository.existsByProjectIdAndId(any(String.class), any(String.class)))
                .thenReturn(true);
        when(treeViewMetadataRepository.findByProjectIdAndDatasetIdAndTreeViewId(projectId, datasetId, treeViewId))
                .thenReturn(Optional.of(new TreeViewMetadata()));

        assertThrows(InvalidArgumentException.class, () ->
                treeViewService.updateTreeView(name, projectId, datasetId, treeViewId, userId)
        );
    }
}
