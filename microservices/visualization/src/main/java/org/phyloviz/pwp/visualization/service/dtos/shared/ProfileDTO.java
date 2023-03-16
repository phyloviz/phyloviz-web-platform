package org.phyloviz.pwp.visualization.service.dtos.shared;

/**
 * DTO for a profile.
 */
public class ProfileDTO {
    private final int st;
    private final String[] alleles;

    public ProfileDTO(int st, String[] alleles) {
        this.st = st;
        this.alleles = alleles;
    }

    public int getSt() {
        return st;
    }

    public String[] getAlleles() {
        return alleles;
    }
}
