package org.phyloviz.pwp.shared_phylodb.repository.data.phylodb.tree_view;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.TreeViewDataRepository;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.specific_data.TreeViewDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.specific_data.TreeViewPhyloDBDataRepositorySpecificData;
import org.phyloviz.pwp.shared.service.dtos.tree_view.Edge;
import org.phyloviz.pwp.shared.service.dtos.tree_view.GetTreeViewOutput;
import org.phyloviz.pwp.shared.service.dtos.tree_view.Node;
import org.springframework.stereotype.Repository;
import pt.ist.meic.phylodb.analysis.inference.InferenceService;
import pt.ist.meic.phylodb.analysis.inference.model.Inference;
import pt.ist.meic.phylodb.analysis.visualization.VisualizationService;
import pt.ist.meic.phylodb.analysis.visualization.model.Visualization;
import pt.ist.meic.phylodb.typing.profile.ProfileRepository;
import pt.ist.meic.phylodb.typing.profile.model.Profile;

import java.util.HashMap;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TreeViewPhyloDBDataRepository implements TreeViewDataRepository {

    private final VisualizationService visualizationService;
    private final InferenceService inferenceService;
    private final ProfileRepository profileRepository;

    @Override
    public GetTreeViewOutput getTreeView(TreeViewDataRepositorySpecificData treeViewDataRepositorySpecificData) {
        TreeViewPhyloDBDataRepositorySpecificData repositorySpecificData =
                (TreeViewPhyloDBDataRepositorySpecificData) treeViewDataRepositorySpecificData;

        Inference inference = inferenceService.getInference(
                repositorySpecificData.getProjectId(),
                repositorySpecificData.getDatasetId(),
                repositorySpecificData.getInferenceId()
        ).orElseThrow(() -> new RuntimeException("Inference not found in PhyloDB"));

        Visualization visualization = visualizationService.getVisualization(
                repositorySpecificData.getProjectId(),
                repositorySpecificData.getDatasetId(),
                repositorySpecificData.getInferenceId(),
                repositorySpecificData.getVisualizationId()
        ).orElseThrow(() -> new RuntimeException("Visualization not found in PhyloDB"));

        List<Edge> edges = inference.getEdges().stream().map(edge -> new Edge(
                edge.getFrom().getPrimaryKey().getId(),
                edge.getTo().getPrimaryKey().getId(),
                edge.getWeight()
        )).toList();

        List<Profile> profiles = profileRepository.findAll(
                0,
                Integer.MAX_VALUE,
                repositorySpecificData.getProjectId(),
                repositorySpecificData.getDatasetId()
        ).orElseThrow(() -> new RuntimeException("Profiles not found in PhyloDB"));

        HashMap<String, Profile> profilesMap = new HashMap<>();

        profiles.forEach(profile -> profilesMap.put(profile.getPrimaryKey().getId(), profile));

        List<Node> nodes = visualization.getCoordinates().stream().map(coordinate -> {
            Profile.PrimaryKey profileKey = coordinate.getProfile();

            Profile profile = profilesMap.get(profileKey.getId());

            return new Node(
                    profileKey.getId(),
                    new double[]{coordinate.getX(), coordinate.getY()},
                    profile.getAllelesReferences().stream().map(alleleReference ->
                            alleleReference.getPrimaryKey().getId()
                    ).toList() // TODO add ancillary data
            );
        }).toList();

        return new GetTreeViewOutput(
                nodes,
                nodes.size(),
                edges,
                edges.size()
        );
    }

    @Override
    public void deleteTreeView(TreeViewDataRepositorySpecificData treeViewDataRepositorySpecificData) {
//        throw new UnsupportedOperationException("Not implemented yet."); // TODO: Implement
    }
}
