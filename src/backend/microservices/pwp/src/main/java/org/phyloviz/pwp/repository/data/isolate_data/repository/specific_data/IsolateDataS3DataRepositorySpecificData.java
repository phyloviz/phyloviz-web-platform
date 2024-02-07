package org.phyloviz.pwp.repository.data.isolate_data.repository.specific_data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IsolateDataS3DataRepositorySpecificData implements IsolateDataDataRepositorySpecificData {
    private String url;
    private String originalFilename;
}
