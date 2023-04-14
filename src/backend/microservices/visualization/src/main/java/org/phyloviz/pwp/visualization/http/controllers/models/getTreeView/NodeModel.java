package org.phyloviz.pwp.visualization.http.controllers.models.getTreeView;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.NodeDTO;

import java.util.List;

/**
 * Model for a tree node.
 */
@Data
public class NodeModel {
    private int st;
    private double[] coordinates;
    private List<String> profile;
    private Object auxiliaryData;

    public NodeModel(NodeDTO nodeDTO) {
        this.st = nodeDTO.getSt();
        this.coordinates = nodeDTO.getCoordinates();
        this.profile = nodeDTO.getProfile();
        this.auxiliaryData = nodeDTO.getAuxiliaryData();
    }
}
