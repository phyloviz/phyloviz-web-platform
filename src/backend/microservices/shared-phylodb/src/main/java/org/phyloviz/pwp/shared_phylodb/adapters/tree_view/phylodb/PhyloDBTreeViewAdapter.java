package org.phyloviz.pwp.shared_phylodb.adapters.tree_view.phylodb;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.tree_view.adapter.TreeViewAdapter;
import org.phyloviz.pwp.shared.adapters.tree_view.adapter.specific_data.TreeViewAdapterSpecificData;
import org.phyloviz.pwp.shared.service.dtos.tree_view.GetTreeViewOutput;
import org.phyloviz.pwp.shared.service.dtos.tree_view.Node;
import org.springframework.stereotype.Service;
import pt.ist.meic.phylodb.analysis.visualization.VisualizationService;
import pt.ist.meic.phylodb.analysis.visualization.model.Visualization;
import pt.ist.meic.phylodb.typing.profile.ProfileService;
import pt.ist.meic.phylodb.typing.profile.model.Profile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhyloDBTreeViewAdapter implements TreeViewAdapter {

    private final VisualizationService visualizationService;
    private final ProfileService profileService;

    @Override
    public GetTreeViewOutput getTreeView(TreeViewAdapterSpecificData treeViewAdapterSpecificData) {
        TreeViewPhyloDBAdapterSpecificData treeViewPhyloDBAdapterSpecificData =
                (TreeViewPhyloDBAdapterSpecificData) treeViewAdapterSpecificData;

        Visualization visualization = visualizationService.getVisualization(
                treeViewPhyloDBAdapterSpecificData.getProjectId(),
                treeViewPhyloDBAdapterSpecificData.getDatasetId(),
                treeViewPhyloDBAdapterSpecificData.getInferenceId(),
                treeViewPhyloDBAdapterSpecificData.getVisualizationId()
        ).orElseThrow(() -> new RuntimeException("Visualization not found in PhyloDB"));

        List<Node> nodes = visualization.getCoordinates().stream().map(coordinate -> {
            Profile.PrimaryKey profileKey = coordinate.getProfile();

            Profile profile = profileService.getProfile(
                    profileKey.getProjectId(),
                    profileKey.getDatasetId(),
                    profileKey.getId(),
                    1).orElseThrow(() -> new RuntimeException("Profile not found in PhyloDB"));

            return new Node(
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
        );
    }

    @Override
    public void deleteTreeView(TreeViewAdapterSpecificData treeViewAdapterSpecificData) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
