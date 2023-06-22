package org.phyloviz.pwp.shared_phylodb.repository.data.phylodb.tree;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.tree.repository.TreeDataRepository;
import org.phyloviz.pwp.shared.repository.data.tree.repository.specific_data.TreeDataRepositorySpecificData;
import org.springframework.stereotype.Repository;
import pt.ist.meic.phylodb.analysis.inference.InferenceService;

@Repository
@RequiredArgsConstructor
public class TreePhyloDBDataRepository implements TreeDataRepository {
    private final InferenceService inferenceService;

    @Override
    public String getTree(TreeDataRepositorySpecificData treeDataRepositorySpecificData) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void deleteTree(TreeDataRepositorySpecificData treeDataRepositorySpecificData) {
//        throw new UnsupportedOperationException("Not implemented yet."); TODO: implement
    }
}
