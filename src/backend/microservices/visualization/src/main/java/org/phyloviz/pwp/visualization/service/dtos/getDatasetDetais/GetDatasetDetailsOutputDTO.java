package org.phyloviz.pwp.visualization.service.dtos.getDatasetDetais;

/**
 * Output DTO for the getDatasetDetails service.
 */
public class GetDatasetDetailsOutputDTO {
    private final String[] loci;
    private final int totalCount;

    public GetDatasetDetailsOutputDTO(String[] loci, int totalCount) {
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
