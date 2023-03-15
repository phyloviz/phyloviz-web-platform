package org.isel.phylovizwebplatform.gateway.service.dtos;

public class UploadProfileOutputDTO {
    private final String profileId;

    public UploadProfileOutputDTO(String profileId) {
        this.profileId = profileId;
    }

    public String getProfileId() {
        return profileId;
    }
}
