package org.phyloviz.pwp.visualization.http.controllers.models.getTypingDataDetails;

/**
 * Output model for the get typing data details endpoint.
 */
public class GetTypingDataDetailsOutputModel {
    private final String[] loci;
    private final int totalCount;

    public GetTypingDataDetailsOutputModel(String[] loci, int totalCount) {
        this.loci = loci;
        this.totalCount = totalCount;
    }

    public GetTypingDataDetailsOutputModel(org.phyloviz.pwp.visualization.service.dtos.getTypingDataDetails.GetTypingDataDetailsOutputDTO getTypingDataDetailsOutputDTO) {
        this.loci = getTypingDataDetailsOutputDTO.getLoci();
        this.totalCount = getTypingDataDetailsOutputDTO.getTotalCount();
    }
}
