package org.phyloviz.pwp.administration.service.project.file;

import org.phyloviz.pwp.administration.service.dtos.files.isolate_data.IsolateDataInfo;
import org.phyloviz.pwp.administration.service.dtos.files.isolate_data.UpdateIsolateDataOutput;

import java.util.List;

public interface IsolateDataService {

    List<IsolateDataInfo> getIsolateDataInfos(String projectId);

    void deleteIsolateData(String projectId, String isolateDataId, String userId);

    void deleteAllByProjectId(String projectId);

    UpdateIsolateDataOutput updateIsolateData(String name, String projectId, String isolateDataId, String userId);
}
