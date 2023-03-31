package org.phyloviz.pwp.visualization.service.dtos.getTypingDataDetails;

/**
 * Output DTO for the getTypingDataDetails service.
 */
public class GetTypingDataDetailsOutputDTO {
    private final String[] loci;
    private final int totalCount;

    public GetTypingDataDetailsOutputDTO(String[] loci, int totalCount) {
        this.loci = loci;
        this.totalCount = totalCount;
    }

    public String[] getLoci() {
        return loci;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
