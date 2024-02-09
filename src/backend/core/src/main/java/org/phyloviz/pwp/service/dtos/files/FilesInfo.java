package org.phyloviz.pwp.service.dtos.files;

import lombok.Data;
import org.phyloviz.pwp.service.dtos.files.isolate_data.IsolateDataInfo;
import org.phyloviz.pwp.service.dtos.files.typing_data.TypingDataInfo;

import java.util.List;

@Data
public class FilesInfo {
    private final List<TypingDataInfo> typingData;
    private final List<IsolateDataInfo> isolateData;
}
