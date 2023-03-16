package org.phyloviz.pwp.uploader.http.controllers.models;

import org.springframework.web.multipart.MultipartFile;

public class UploadModel {
    private final String projectName;
    private final String type;
    private final MultipartFile file;

    public UploadModel(String projectName, String type, MultipartFile file) {
        this.projectName = projectName;
        this.type = type;
        this.file = file;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getType() {
        return type;
    }

    public MultipartFile getFile() {
        return file;
    }
}
