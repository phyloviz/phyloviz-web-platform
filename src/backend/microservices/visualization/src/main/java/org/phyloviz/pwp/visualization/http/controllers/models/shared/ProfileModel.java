package org.phyloviz.pwp.visualization.http.controllers.models.shared;

import org.phyloviz.pwp.visualization.service.dtos.shared.ProfileDTO;

/**
 * Model for a profile.
 */
public class ProfileModel {
    private final int st;
    private final String[] alleles;

    public ProfileModel(int st, String[] alleles) {
        this.st = st;
        this.alleles = alleles;
    }

    public ProfileModel(ProfileDTO profileDTO) {
        this.st = profileDTO.getSt();
        this.alleles = profileDTO.getAlleles();
    }
}
