package org.phyloviz.pwp.shared.service.project.dataset.distanceMatrix;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.DistanceMatrixMetadata;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSourceAlgorithmDistanceMatrix;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSourceType;
import org.phyloviz.pwp.shared.service.dtos.distanceMatrix.DistanceMatrixMetadataDTO;
import org.phyloviz.pwp.shared.service.exceptions.DeniedResourceDeletionException;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetAccessService;
import org.phyloviz.pwp.shared.service.project.dataset.tree.TreeAccessService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DistanceMatrixServiceImpl implements DistanceMatrixService {

    private final DatasetAccessService datasetAccessService;
    private final DistanceMatrixAccessService distanceMatrixAccessService;
    private final TreeAccessService treeAccessService;

    @Override
    public DistanceMatrixMetadata getDistanceMatrixMetadata(String projectId, String datasetId, String distanceMatrixId, String userId) {
        return distanceMatrixAccessService.getDistanceMatrixMetadata(projectId, datasetId, distanceMatrixId, userId);
    }

    @Override
    public DistanceMatrixMetadata getDistanceMatrixMetadata(String distanceMatrixId) {
        return distanceMatrixAccessService.getDistanceMatrixMetadata(distanceMatrixId);
    }

    @Override
    public DistanceMatrixMetadata getDistanceMatrixMetadataOrNull(String distanceMatrixId) {
        return distanceMatrixAccessService.getDistanceMatrixMetadataOrNull(distanceMatrixId);
    }

    @Override
    public DistanceMatrixMetadataDTO getDistanceMatrixMetadataDTO(String distanceMatrixId) {
        return new DistanceMatrixMetadataDTO(getDistanceMatrixMetadata(distanceMatrixId));
    }

    @Override
    public void assertExists(String projectId, String datasetId, String distanceMatrixId, String userId) {
        distanceMatrixAccessService.assertExists(projectId, datasetId, distanceMatrixId, userId);
    }

    @Override
    public void deleteDistanceMatrix(String projectId, String datasetId, String distanceMatrixId, String userId) {
        assertExists(projectId, datasetId, distanceMatrixId, userId);

        Dataset dataset = datasetAccessService.getDataset(projectId, datasetId, userId);

        dataset.getTreeIds().forEach(treeId -> {
            TreeMetadata treeMetadata = treeAccessService.getTreeMetadataOrNull(treeId);

            if (treeMetadata != null && treeMetadata.getSourceType().equals(TreeSourceType.ALGORITHM_DISTANCE_MATRIX) &&
                    (((TreeSourceAlgorithmDistanceMatrix) treeMetadata.getSource())
                            .getDistanceMatrixId().equals(distanceMatrixId))) {
                throw new DeniedResourceDeletionException(
                        "Cannot delete distance matrix. It is a dependency of a tree (treeId = " + treeId + "). " +
                                "Delete the tree first."
                );
            }
        });

        deleteDistanceMatrix(distanceMatrixId);

        dataset.getDistanceMatrixIds().remove(distanceMatrixId);
        datasetAccessService.saveDataset(dataset);
    }

    @Override
    public void deleteDistanceMatrix(String distanceMatrixId) {
        distanceMatrixAccessService.deleteDistanceMatrix(distanceMatrixId);
    }

    @Override
    public String getDistanceMatrix(String projectId, String datasetId, String distanceMatrixId, String userId) {
        return distanceMatrixAccessService.getDistanceMatrix(projectId, datasetId, distanceMatrixId, userId);
    }
}
