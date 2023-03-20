package org.phyloviz.pwp.visualization.http.controllers.models.getTypingDatasetDetais;

import org.phyloviz.pwp.visualization.service.dtos.getTypingDatasetDetais.GetDatasetDetailsOutputDTO;

/**
 * Output model for the get dataset details endpoint.
 */
public class GetDatasetDetailsOutputModel {
    private final String[] loci;
    private final int totalCount;

    public GetDatasetDetailsOutputModel(String[] loci, int totalCount) {
        this.loci = loci;
        this.totalCount = totalCount;
    }

    public GetDatasetDetailsOutputModel(GetDatasetDetailsOutputDTO getDatasetDetailsOutputDTO) {
        this.loci = getDatasetDetailsOutputDTO.getLoci();
        this.totalCount = getDatasetDetailsOutputDTO.getTotalCount();
    }
}
