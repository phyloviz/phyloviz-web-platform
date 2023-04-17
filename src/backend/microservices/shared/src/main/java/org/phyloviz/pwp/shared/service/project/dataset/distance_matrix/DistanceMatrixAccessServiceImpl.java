package org.phyloviz.pwp.shared.service.project.dataset.distance_matrix;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.distance_matrix.DistanceMatrixAdapterFactory;
import org.phyloviz.pwp.shared.adapters.distance_matrix.DistanceMatrixAdapterId;
import org.phyloviz.pwp.shared.adapters.distance_matrix.adapter.DistanceMatrixAdapter;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.DistanceMatrixMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.DistanceMatrixMetadata;
import org.phyloviz.pwp.shared.service.exceptions.DistanceMatrixNotFoundException;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetAccessService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistanceMatrixAccessServiceImpl implements DistanceMatrixAccessService {

    private final DatasetAccessService datasetAccessService;

    private final DistanceMatrixMetadataRepository distanceMatrixMetadataRepository;
    private final DistanceMatrixAdapterFactory distanceMatrixAdapterFactory;

    @Value("${adapters.get-distance-matrix-adapter-priority}")
    private List<DistanceMatrixAdapterId> getDistanceMatrixAdapterPriority;

    @Override
    public DistanceMatrixMetadata getDistanceMatrixMetadata(String projectId, String datasetId, String distanceMatrixId, String userId) {
        Dataset dataset = datasetAccessService.getDataset(projectId, datasetId, userId);

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
    public void deleteDistanceMatrix(String distanceMatrixId) {
        distanceMatrixMetadataRepository.findAllByDistanceMatrixId(distanceMatrixId)
                .forEach(distanceMatrixMetadata -> {
                    distanceMatrixAdapterFactory.getDistanceMatrixAdapter(distanceMatrixMetadata.getAdapterId())
                            .deleteDistanceMatrix(distanceMatrixMetadata.getAdapterSpecificData());

                    distanceMatrixMetadataRepository.delete(distanceMatrixMetadata);
                });
    }

    @Override
    public void assertExists(String projectId, String datasetId, String distanceMatrixId, String userId) {
        getDistanceMatrixMetadata(projectId, datasetId, distanceMatrixId, userId);
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
