package org.phyloviz.pwp.shared.repository.data.tree.repository;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.S3FileRepository;
import org.phyloviz.pwp.shared.repository.data.tree.repository.specific_data.TreeDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.tree.repository.specific_data.TreeS3DataRepositorySpecificData;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TreeS3DataRepository implements TreeDataRepository {

    private final S3FileRepository s3FileRepository;

    @Override
    public String getTree(TreeDataRepositorySpecificData treeDataRepositorySpecificData) {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public boolean isFileRepository() {
        return true;
    }

    @Override
    public void deleteTree(TreeDataRepositorySpecificData treeDataRepositorySpecificData) {
        TreeS3DataRepositorySpecificData repositorySpecificData =
                (TreeS3DataRepositorySpecificData) treeDataRepositorySpecificData;

        s3FileRepository.delete(repositorySpecificData.getUrl());
    }

}
