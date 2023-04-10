package org.phyloviz.pwp.visualization.service;

import org.phyloviz.pwp.visualization.service.dtos.getDistanceMatrix.GetDistanceMatrixInputDTO;
import org.phyloviz.pwp.visualization.service.dtos.getTree.GetTreeInputDTO;
import org.phyloviz.pwp.visualization.service.dtos.getTreeView.GetTreeViewInputDTO;
import org.phyloviz.pwp.visualization.service.dtos.getTreeView.GetTreeViewOutputDTO;
import org.phyloviz.pwp.visualization.service.dtos.isolateData.getIsolateDataRows.GetIsolateDataRowsInputDTO;
import org.phyloviz.pwp.visualization.service.dtos.isolateData.getIsolateDataRows.GetIsolateDataRowsOutputDTO;
import org.phyloviz.pwp.visualization.service.dtos.isolateData.getIsolateDataSchema.GetIsolateDataSchemaInputDTO;
import org.phyloviz.pwp.visualization.service.dtos.isolateData.getIsolateDataSchema.GetIsolateDataSchemaOutputDTO;
import org.phyloviz.pwp.visualization.service.dtos.typingData.getTypingDataProfiles.GetTypingDataProfilesInputDTO;
import org.phyloviz.pwp.visualization.service.dtos.typingData.getTypingDataProfiles.GetTypingDataProfilesOutputDTO;
import org.phyloviz.pwp.visualization.service.dtos.typingData.getTypingDataSchema.GetTypingDataSchemaInputDTO;
import org.phyloviz.pwp.visualization.service.dtos.typingData.getTypingDataSchema.GetTypingDataSchemaOutputDTO;
import org.springframework.stereotype.Service;

/**
 * Service for the Visualization Microservice.
 */
@Service
public interface VisualizationService {

    /**
     * Gets a tree, given its id.
     *
     * @param getTreeInputDTO the input DTO
     * @return the tree
     */
    String getTree(GetTreeInputDTO getTreeInputDTO);

    /**
     * Gets a tree view, given its id.
     *
     * @param getTreeViewInputDTO the input DTO
     * @return the tree
     */
    GetTreeViewOutputDTO getTreeView(GetTreeViewInputDTO getTreeViewInputDTO);

    /**
     * Gets a distance matrix, given its id.
     *
     * @param getDistanceMatrixInputDTO the input DTO
     * @return the distance matrix
     */
    String getDistanceMatrix(GetDistanceMatrixInputDTO getDistanceMatrixInputDTO);

    /**
     * Gets a typing data's schema, given its id.
     *
     * @param getTypingDataSchemaInputDTO the input DTO
     * @return the typing data schema
     */
    GetTypingDataSchemaOutputDTO getTypingDataSchema(GetTypingDataSchemaInputDTO getTypingDataSchemaInputDTO);

    /**
     * Gets a typing data's profiles, given its id, with pagination.
     *
     * @param getTypingDataProfilesInputDTO the input DTO
     * @return the typing data profiles
     */
    GetTypingDataProfilesOutputDTO getTypingDataProfiles(GetTypingDataProfilesInputDTO getTypingDataProfilesInputDTO);

    /**
     * Gets an isolate data's schema, given its id.
     *
     * @param getIsolateDataSchemaInputDTO the input DTO
     * @return the isolate data schema
     */
    GetIsolateDataSchemaOutputDTO getIsolateDataSchema(GetIsolateDataSchemaInputDTO getIsolateDataSchemaInputDTO);

    /**
     * Gets an isolate data's rows, given its id, with pagination.
     *
     * @param getIsolateDataRowsInputDTO the input DTO
     * @return the isolate data rows
     */
    GetIsolateDataRowsOutputDTO getIsolateDataRows(GetIsolateDataRowsInputDTO getIsolateDataRowsInputDTO);
}
