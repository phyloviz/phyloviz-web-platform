package org.phyloviz.pwp.shared.adapters.tree_view.adapter;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.tree_view.adapter.specific_data.TreeViewAdapterSpecificData;
import org.phyloviz.pwp.shared.service.dtos.tree_view.GetTreeViewOutput;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class PhyloDBTreeViewAdapter implements TreeViewAdapter {

    // private final VisualizationService visualizationService;
    // private final ProfileService profileService;

    @Override
    public GetTreeViewOutput getTreeView(TreeViewAdapterSpecificData treeViewAdapterSpecificData) {
        /*TreeViewPhyloDBAdapterSpecificData treeViewPhyloDBAdapterSpecificData =
                (TreeViewPhyloDBAdapterSpecificData) treeViewAdapterSpecificData;

        Visualization visualization = visualizationService.getVisualization(
                treeViewPhyloDBAdapterSpecificData.getProjectId(),
                treeViewPhyloDBAdapterSpecificData.getDatasetId(),
                treeViewPhyloDBAdapterSpecificData.getInferenceId(),
                treeViewPhyloDBAdapterSpecificData.getVisualizationId()
        ).orElseThrow(() -> new RuntimeException("Visualization not found in PhyloDB"));

        List<NodeDTO> nodes = visualization.getCoordinates().stream().map(coordinate -> {
            Profile.PrimaryKey profileKey = coordinate.getProfile();

            Profile profile = profileService.getProfile(
                    profileKey.getProjectId(),
                    profileKey.getDatasetId(),
                    profileKey.getId(),
                    1).orElseThrow(() -> new RuntimeException("Profile not found in PhyloDB"));

            return new NodeDTO(
                    Integer.parseInt(profileKey.getId()),
                    new double[]{coordinate.getX(), coordinate.getY()},
                    profile.getAllelesReferences().stream().map(alleleReference ->
                            alleleReference.getPrimaryKey().getLocusId()
                    ).toList(),
                    new Object() // TODO add ancillary data
            );
        }).toList();

        return new GetTreeViewOutput(
                nodes,
                nodes.size()
        );*/
        return new GetTreeViewOutput(Collections.emptyList(), 0);
    }

    @Override
    public void deleteTreeView(TreeViewAdapterSpecificData treeViewAdapterSpecificData) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
