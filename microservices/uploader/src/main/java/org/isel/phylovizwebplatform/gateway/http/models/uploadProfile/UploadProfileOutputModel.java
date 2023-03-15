package org.isel.phylovizwebplatform.gateway.http.models.uploadProfile;

import org.isel.phylovizwebplatform.gateway.service.dtos.UploadProfileOutputDTO;

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
