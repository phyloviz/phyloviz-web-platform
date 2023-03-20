package org.phyloviz.pwp.shared.repository.metadata.inference_tree.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.inference_tree.InferenceTreeMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.inference_tree.documents.InferenceTreeMetadata;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InferenceTreeMetadataRepositoryMongo implements InferenceTreeMetadataRepository {

    private final InferenceTreeMetadataMongoRepository inferenceTreeMetadataMongoRepository;

    @Override
    public void deleteInferenceTree(InferenceTreeMetadata inferenceTreeMetadata) {
        inferenceTreeMetadataMongoRepository.delete(inferenceTreeMetadata);
    }

    @Override
    public InferenceTreeMetadata findByResourceId(String resourceId) {
        return inferenceTreeMetadataMongoRepository.findByResourceId(resourceId);
    }
}
