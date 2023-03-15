package org.isel.phylovizwebplatform.gateway.http.models.uploadProfile;

public class UploadProfileInputModel {
    private final String projectId;

    public UploadProfileInputModel(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
    }
}
