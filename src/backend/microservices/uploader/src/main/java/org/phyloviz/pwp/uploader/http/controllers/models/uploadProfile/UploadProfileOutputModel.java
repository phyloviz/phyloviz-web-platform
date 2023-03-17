package org.phyloviz.pwp.uploader.http.controllers.models.uploadProfile;

import lombok.Data;
import org.phyloviz.pwp.uploader.service.dtos.uploadeProfile.UploadProfileOutputDTO;

@Data
public class UploadProfileOutputModel {
    private final String profileId;

    public UploadProfileOutputModel(UploadProfileOutputDTO uploadProfileOutputDTO) {
        this.profileId = uploadProfileOutputDTO.getProfileId();
    }
}
