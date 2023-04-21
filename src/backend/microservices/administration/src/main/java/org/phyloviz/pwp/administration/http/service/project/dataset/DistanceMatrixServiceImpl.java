package org.phyloviz.pwp.administration.http.service.project.dataset;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.distance_matrix.DistanceMatrixAdapterFactory;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSourceAlgorithmDistanceMatrix;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSourceType;
import org.phyloviz.pwp.shared.service.dtos.distance_matrix.DistanceMatrixInfo;
import org.phyloviz.pwp.shared.service.exceptions.DeniedResourceDeletionException;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetMetadataService;
import org.phyloviz.pwp.shared.service.project.dataset.distance_matrix.DistanceMatrixMetadataService;
import org.phyloviz.pwp.shared.service.project.dataset.tree.TreeMetadataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistanceMatrixServiceImpl implements DistanceMatrixService {

    private final DatasetMetadataService datasetMetadataService;
    private final DistanceMatrixMetadataService distanceMatrixMetadataService;
    private final TreeMetadataService treeMetadataService;

    private final DistanceMatrixAdapterFactory distanceMatrixAdapterFactory;

    @Override
    public List<DistanceMatrixInfo> getDistanceMatrixInfos(String datasetId) {
        return distanceMatrixMetadataService.getAllDistanceMatrixMetadataByDatasetId(datasetId).stream()
                .map(DistanceMatrixInfo::new).toList();
    }

    @Override
    public void deleteDistanceMatrix(String projectId, String datasetId, String distanceMatrixId, String userId) {
        distanceMatrixMetadataService.assertExists(projectId, datasetId, distanceMatrixId, userId);

        Dataset dataset = datasetMetadataService.getDataset(projectId, datasetId, userId);

        dataset.getTreeIds().forEach(treeId -> {
            TreeMetadata treeMetadata = treeMetadataService.getTreeMetadataOrNull(treeId);

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
        datasetMetadataService.saveDataset(dataset);
    }

    @Override
    public void deleteDistanceMatrix(String distanceMatrixId) {
        distanceMatrixMetadataService.getAllDistanceMatrixMetadata(distanceMatrixId)
                .forEach(distanceMatrixMetadata -> {
                    distanceMatrixAdapterFactory.getDistanceMatrixAdapter(distanceMatrixMetadata.getAdapterId())
                            .deleteDistanceMatrix(distanceMatrixMetadata.getAdapterSpecificData());

                    distanceMatrixMetadataService.deleteDistanceMatrix(distanceMatrixMetadata);
                });
    }
}
