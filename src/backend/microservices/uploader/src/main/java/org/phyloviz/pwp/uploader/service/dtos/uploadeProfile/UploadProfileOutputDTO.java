package org.phyloviz.pwp.uploader.service.dtos.uploadeProfile;

public class UploadProfileOutputDTO {
    private final String profileId;

    public UploadProfileOutputDTO(String profileId) {
        this.profileId = profileId;
    }

    public String getProfileId() {
        return profileId;
    }
}
