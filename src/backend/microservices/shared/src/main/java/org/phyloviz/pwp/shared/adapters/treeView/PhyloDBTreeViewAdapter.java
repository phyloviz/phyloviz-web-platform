package org.phyloviz.pwp.shared.adapters.treeView;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.adapterSpecificData.TreeViewAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.treeView.documents.adapterSpecificData.TreeViewPhyloDBAdapterSpecificData;
import org.phyloviz.pwp.shared.service.dtos.Edge;
import org.phyloviz.pwp.shared.service.dtos.Node;
import org.springframework.stereotype.Component;
import pt.ist.meic.phylodb.analysis.inference.InferenceService;
import pt.ist.meic.phylodb.analysis.inference.model.Inference;
import pt.ist.meic.phylodb.analysis.visualization.VisualizationService;
import pt.ist.meic.phylodb.analysis.visualization.model.Visualization;
import pt.ist.meic.phylodb.typing.profile.ProfileRepository;
import pt.ist.meic.phylodb.typing.profile.model.Profile;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PhyloDBTreeViewAdapter implements TreeViewAdapter {

    private final VisualizationService visualizationService;
    private final InferenceService inferenceService;
    private final ProfileRepository profileRepository;

    @Override
    public AdapterGetTreeViewDTO getTreeView(TreeViewAdapterSpecificData treeViewAdapterSpecificData) {
        TreeViewPhyloDBAdapterSpecificData treeViewPhyloDBAdapterSpecificData =
                (TreeViewPhyloDBAdapterSpecificData) treeViewAdapterSpecificData;

        Inference inference = inferenceService.getInference(
                treeViewPhyloDBAdapterSpecificData.getProjectId(),
                treeViewPhyloDBAdapterSpecificData.getDatasetId(),
                treeViewPhyloDBAdapterSpecificData.getInferenceId()
        ).orElseThrow(() -> new RuntimeException("Inference not found in PhyloDB"));

        Visualization visualization = visualizationService.getVisualization(
                treeViewPhyloDBAdapterSpecificData.getProjectId(),
                treeViewPhyloDBAdapterSpecificData.getDatasetId(),
                treeViewPhyloDBAdapterSpecificData.getInferenceId(),
                treeViewPhyloDBAdapterSpecificData.getVisualizationId()
        ).orElseThrow(() -> new RuntimeException("Visualization not found in PhyloDB"));

        List<Edge> edges = inference.getEdges().stream().map(edge -> new Edge(
                edge.getFrom().getPrimaryKey().getId(),
                edge.getTo().getPrimaryKey().getId()
        )).toList();

        List<Profile> profiles = profileRepository.findAll(
                0,
                Integer.MAX_VALUE,
                treeViewPhyloDBAdapterSpecificData.getProjectId(),
                treeViewPhyloDBAdapterSpecificData.getDatasetId()
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

        return new AdapterGetTreeViewDTO(
                nodes,
                edges
        );
    }
}
