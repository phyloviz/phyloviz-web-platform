package org.phyloviz.pwp.shared.service.project.dataset.distance_matrix;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSourceAlgorithmDistanceMatrix;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSourceType;
import org.phyloviz.pwp.shared.service.dtos.distance_matrix.DistanceMatrixInfo;
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
    public DistanceMatrixInfo getDistanceMatrixInfo(String distanceMatrixId) {
        return new DistanceMatrixInfo(distanceMatrixAccessService.getDistanceMatrixMetadata(distanceMatrixId));
    }

    @Override
    public void deleteDistanceMatrix(String projectId, String datasetId, String distanceMatrixId, String userId) {
        distanceMatrixAccessService.assertExists(projectId, datasetId, distanceMatrixId, userId);

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
