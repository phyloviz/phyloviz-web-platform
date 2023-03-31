package org.phyloviz.pwp.administration.http.models.files;

import lombok.Data;
import org.phyloviz.pwp.administration.http.models.files.isolateData.IsolateDataModel;
import org.phyloviz.pwp.administration.http.models.files.typingData.TypingDataModel;
import org.phyloviz.pwp.administration.service.dtos.files.FilesDTO;

import java.util.List;

@Data
public class FilesModel {
    private List<TypingDataModel> typingData;
    private List<IsolateDataModel> isolateData;

    public FilesModel(FilesDTO filesDTO) {
        this.typingData = filesDTO.getTypingData().stream().map(TypingDataModel::new).toList();
        this.isolateData = filesDTO.getIsolateData().stream().map(IsolateDataModel::new).toList();
    }
}
