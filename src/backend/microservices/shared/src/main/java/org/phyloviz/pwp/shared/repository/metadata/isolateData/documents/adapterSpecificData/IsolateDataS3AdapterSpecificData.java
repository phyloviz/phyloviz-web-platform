package org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.adapterSpecificData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeAlias("isolateDataS3AdapterSpecificData")
public class IsolateDataS3AdapterSpecificData implements IsolateDataAdapterSpecificData {
    private String originalFilename;
}
