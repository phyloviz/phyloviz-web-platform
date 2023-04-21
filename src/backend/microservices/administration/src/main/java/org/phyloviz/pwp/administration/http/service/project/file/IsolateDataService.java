package org.phyloviz.pwp.administration.http.service.project.file;

import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.IsolateDataInfo;

import java.util.List;

public interface IsolateDataService {

    List<IsolateDataInfo> getIsolateDataInfos(String projectId);

    void deleteIsolateData(String projectId, String isolateDataId, String userId);

    void deleteIsolateData(String isolateDataId);
}
