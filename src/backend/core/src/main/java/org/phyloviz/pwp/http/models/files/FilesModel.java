package org.phyloviz.pwp.http.models.files;

import lombok.Data;
import org.phyloviz.pwp.http.models.files.isolate_data.IsolateDataModel;
import org.phyloviz.pwp.http.models.files.typing_data.TypingDataModel;
import org.phyloviz.pwp.service.dtos.files.FilesInfo;

import java.util.List;

@Data
public class FilesModel {
    private List<TypingDataModel> typingData;
    private List<IsolateDataModel> isolateData;

    public FilesModel(FilesInfo filesInfo) {
        this.typingData = filesInfo.getTypingData().stream().map(TypingDataModel::new).toList();
        this.isolateData = filesInfo.getIsolateData().stream().map(IsolateDataModel::new).toList();
    }
}
