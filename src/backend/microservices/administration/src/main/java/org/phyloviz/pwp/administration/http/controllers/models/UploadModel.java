package org.phyloviz.pwp.administration.http.controllers.models;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadModel {
    private String projectName;
    private String type;
    private MultipartFile file;

}
