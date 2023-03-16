package org.phyloviz.pwp.visualization.service.dtos.shared;

/**
 * DTO for a tree node.
 */
public class NodeDTO {
    private final int st;
    private final int[] coordinates;
    private final String[] alleles;
    private final IsolateDataDTO isolateData;

    public NodeDTO(int st, int[] coordinates, String[] alleles, IsolateDataDTO isolateData) {
        this.st = st;
        this.coordinates = coordinates;
        this.alleles = alleles;
        this.isolateData = isolateData;
    }

    public int getSt() {
        return st;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public String[] getAlleles() {
        return alleles;
    }

    public IsolateDataDTO getIsolateData() {
        return isolateData;
    }
}
