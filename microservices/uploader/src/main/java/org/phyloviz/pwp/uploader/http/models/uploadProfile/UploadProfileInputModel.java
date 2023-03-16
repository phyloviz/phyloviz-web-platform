package org.phyloviz.pwp.uploader.http.models.uploadProfile;

public class UploadProfileInputModel {
    private final String projectId;

    public UploadProfileInputModel(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
    }
}
