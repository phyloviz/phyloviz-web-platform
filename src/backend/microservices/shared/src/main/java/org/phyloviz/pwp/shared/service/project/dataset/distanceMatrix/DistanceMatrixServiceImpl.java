package org.phyloviz.pwp.shared.service.project.dataset.distanceMatrix;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.DistanceMatrixMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.DistanceMatrixMetadata;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.adapterSpecificData.DistanceMatrixAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSourceAlgorithmDistanceMatrix;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSourceType;
import org.phyloviz.pwp.shared.service.adapters.distanceMatrix.DistanceMatrixAdapter;
import org.phyloviz.pwp.shared.service.adapters.distanceMatrix.DistanceMatrixAdapterFactory;
import org.phyloviz.pwp.shared.service.dtos.distanceMatrix.DistanceMatrixMetadataDTO;
import org.phyloviz.pwp.shared.service.exceptions.DeniedResourceDeletionException;
import org.phyloviz.pwp.shared.service.exceptions.DistanceMatrixNotFoundException;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetService;
import org.phyloviz.pwp.shared.service.project.dataset.tree.TreeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistanceMatrixServiceImpl implements DistanceMatrixService {

    private final DistanceMatrixMetadataRepository distanceMatrixMetadataRepository;

    private final DatasetService datasetService;
    private final TreeService treeService;

    private final DistanceMatrixAdapterFactory distanceMatrixAdapterFactory;

    @Value("${adapters.get-distance-matrix-adapter-priority}")
    private final List<DistanceMatrixAdapterId> getDistanceMatrixAdapterPriority;

    @Override
    public DistanceMatrixMetadata getDistanceMatrixMetadata(String projectId, String datasetId, String distanceMatrixId, String userId) {
        Dataset dataset = datasetService.getDataset(projectId, datasetId, userId);

        if (!dataset.getDistanceMatrixIds().contains(distanceMatrixId)) {
            throw new DistanceMatrixNotFoundException();
        }

        return distanceMatrixMetadataRepository.findByDistanceMatrixId(distanceMatrixId).orElseThrow(DistanceMatrixNotFoundException::new);
    }

    @Override
    public DistanceMatrixMetadata getDistanceMatrixMetadata(String distanceMatrixId) {
        return distanceMatrixMetadataRepository.findByDistanceMatrixId(distanceMatrixId).orElseThrow(DistanceMatrixNotFoundException::new);
    }

    @Override
    public DistanceMatrixMetadata getDistanceMatrixMetadataOrNull(String distanceMatrixId) {
        return distanceMatrixMetadataRepository.findByDistanceMatrixId(distanceMatrixId).orElse(null);
    }

    @Override
    public DistanceMatrixMetadataDTO getDistanceMatrixMetadataDTO(String distanceMatrixId) {
        return new DistanceMatrixMetadataDTO(getDistanceMatrixMetadata(distanceMatrixId));
    }

    @Override
    public void assertExists(String projectId, String datasetId, String distanceMatrixId, String userId) {
        getDistanceMatrixMetadata(projectId, datasetId, distanceMatrixId, userId);
    }

    @Override
    public void deleteDistanceMatrix(String projectId, String datasetId, String distanceMatrixId, String userId) {
        assertExists(projectId, datasetId, distanceMatrixId, userId);

        Dataset dataset = datasetService.getDataset(projectId, datasetId, userId);

        dataset.getTreeIds().forEach(treeId -> {
            TreeMetadata treeMetadata = treeService.getTreeMetadataOrNull(treeId);

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
        datasetService.saveDataset(dataset);
    }

    @Override
    public void deleteDistanceMatrix(String distanceMatrixId) {
        distanceMatrixMetadataRepository.findAllByDistanceMatrixId(distanceMatrixId)
                .forEach(distanceMatrixMetadata -> {
                    distanceMatrixAdapterFactory.getDistanceMatrixAdapter(distanceMatrixMetadata.getAdapterId())
                            .deleteDistanceMatrix(distanceMatrixMetadata.getAdapterSpecificData());

                    distanceMatrixMetadataRepository.delete(distanceMatrixMetadata);
                });
    }

    @Override
    public String getDistanceMatrix(String projectId, String datasetId, String distanceMatrixId, String userId) {
        assertExists(projectId, datasetId, distanceMatrixId, userId);

        List<DistanceMatrixMetadata> distanceMatrixMetadataList =
                distanceMatrixMetadataRepository.findAllByDistanceMatrixId(distanceMatrixId);
        if (distanceMatrixMetadataList.isEmpty())
            throw new DistanceMatrixNotFoundException();

        sortByAdapterPriority(distanceMatrixMetadataList, getDistanceMatrixAdapterPriority);

        DistanceMatrixMetadata distanceMatrix = distanceMatrixMetadataList.get(0);

        DistanceMatrixAdapter distanceMatrixAdapter = distanceMatrixAdapterFactory
                .getDistanceMatrixAdapter(distanceMatrix.getAdapterId());

        return distanceMatrixAdapter.getDistanceMatrix(distanceMatrix.getAdapterSpecificData());
    }

    private void sortByAdapterPriority(List<DistanceMatrixMetadata> metadataList, List<DistanceMatrixAdapterId> adapterPriority) {
        metadataList.sort((o1, o2) -> {
            int i1 = adapterPriority.indexOf(o1.getAdapterId());
            if (i1 == -1)
                i1 = Integer.MAX_VALUE;

            int i2 = adapterPriority.indexOf(o2.getAdapterId());
            if (i2 == -1)
                i2 = Integer.MAX_VALUE;

            return Integer.compare(i1, i2);
        });
    }
}
