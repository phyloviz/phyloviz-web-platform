package org.phyloviz.pwp.shared.adapters.tree.adapter;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.tree.adapter.specific_data.TreeAdapterSpecificData;
import org.phyloviz.pwp.shared.adapters.tree.adapter.specific_data.TreeS3AdapterSpecificData;
import org.phyloviz.pwp.shared.repository.data.S3FileRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class S3TreeAdapter implements TreeAdapter {

    private final S3FileRepository s3FileRepository;

    @Override
    public String getTree(TreeAdapterSpecificData treeAdapterSpecificData) {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public boolean isFileAdapter() {
        return true;
    }

    @Override
    public void deleteTree(TreeAdapterSpecificData treeAdapterSpecificData) {
        TreeS3AdapterSpecificData treeS3AdapterSpecificData =
                (TreeS3AdapterSpecificData) treeAdapterSpecificData;

        s3FileRepository.delete(treeS3AdapterSpecificData.getUrl());
    }

}
