package org.phyloviz.pwp.service.project.file;

import org.phyloviz.pwp.service.dtos.files.typing_data.TypingDataInfo;
import org.phyloviz.pwp.service.dtos.files.typing_data.UpdateTypingDataOutput;

import java.util.List;

public interface TypingDataService {

    List<TypingDataInfo> getTypingDataInfos(String projectId);

    void deleteTypingData(String projectId, String typingDataId, String userId);

    void deleteAllByProjectId(String projectId);

    UpdateTypingDataOutput updateTypingData(String name, String projectId, String typingDataId, String userId);
}
