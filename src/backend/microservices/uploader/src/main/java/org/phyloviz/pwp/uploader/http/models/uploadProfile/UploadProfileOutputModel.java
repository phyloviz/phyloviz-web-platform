package org.phyloviz.pwp.uploader.http.models.uploadProfile;

import org.phyloviz.pwp.uploader.service.dtos.uploadeProfile.UploadProfileOutputDTO;

public class UploadProfileOutputModel {
    private final String profileId;

    public UploadProfileOutputModel(String profileId) {
        this.profileId = profileId;
    }

    public UploadProfileOutputModel(UploadProfileOutputDTO uploadProfileOutputDTO) {
        this.profileId = uploadProfileOutputDTO.getProfileId();
    }

    public String getProfileId() {
        return profileId;
    }
}
