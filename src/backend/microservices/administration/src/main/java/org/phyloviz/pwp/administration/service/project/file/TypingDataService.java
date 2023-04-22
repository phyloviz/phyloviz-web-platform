package org.phyloviz.pwp.administration.service.project.file;

import org.phyloviz.pwp.shared.service.dtos.files.typing_data.TypingDataInfo;

import java.util.List;

public interface TypingDataService {

    List<TypingDataInfo> getTypingDataInfos(String projectId);

    void deleteTypingData(String projectId, String typingDataId, String userId);

    void deleteAllByProjectId(String projectId);

    void deleteTypingData(String typingDataId);
}
