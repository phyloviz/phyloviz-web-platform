package org.phyloviz.pwp.visualization.http.controllers.models.shared;

import org.phyloviz.pwp.visualization.service.dtos.shared.NodeDTO;

/**
 * Model for a tree node.
 */
public class NodeModel {
    private final int st;
    private final int[] coordinates;
    private final String[] alleles;
    private final IsolateDataModel isolateData;

    public NodeModel(int st, int[] coordinates, String[] alleles, IsolateDataModel isolateData) {
        this.st = st;
        this.coordinates = coordinates;
        this.alleles = alleles;
        this.isolateData = isolateData;
    }

    public NodeModel(NodeDTO nodeDTO) {
        this.st = nodeDTO.getSt();
        this.coordinates = nodeDTO.getCoordinates();
        this.alleles = nodeDTO.getAlleles();
        this.isolateData = new IsolateDataModel(nodeDTO.getIsolateData());
    }
}
