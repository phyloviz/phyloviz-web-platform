package org.phyloviz.pwp.visualization.service;

import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.GetIsolateDataRowsOutput;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.GetIsolateDataSchemaOutput;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.GetTypingDataProfilesOutput;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.GetTypingDataSchemaOutput;
import org.phyloviz.pwp.shared.service.dtos.tree_view.GetTreeViewOutput;

public interface VisualizationService {

    /**
     * Gets a distance matrix.
     *
     * @param projectId        the id of the project
     * @param datasetId        the id of the dataset
     * @param distanceMatrixId the id of the distance matrix
     * @param userId           the id of the user
     * @return the distance matrix in string
     */
    String getDistanceMatrix(String projectId, String datasetId, String distanceMatrixId, String userId);

    /**
     * Gets a tree.
     *
     * @param projectId the id of the project
     * @param datasetId the id of the dataset
     * @param treeId    the id of the tree
     * @param userId    the id of the user
     * @return the tree in newick string format
     */
    String getTree(String projectId, String datasetId, String treeId, String userId);

    /**
     * Gets a tree view.
     *
     * @param projectId  the id of the project
     * @param datasetId  the id of the dataset
     * @param treeViewId the id of the tree view
     * @param userId     the id of the user
     * @return the tree view
     */
    GetTreeViewOutput getTreeView(String projectId, String datasetId, String treeViewId, String userId);

    /**
     * Gets a typing data's schema.
     *
     * @param projectId    the id of the project
     * @param typingDataId the id of the typing data
     * @param userId       the id of the user
     * @return the typing data schema
     */
    GetTypingDataSchemaOutput getTypingDataSchema(String projectId, String typingDataId, String userId);

    /**
     * Gets a typing data's profiles, with pagination.
     *
     * @param projectId    the id of the project
     * @param typingDataId the id of the typing data
     * @param limit        the limit of the profiles
     * @param offset       the offset of the profiles
     * @param userId       the id of the user
     * @return the typing data profiles
     */
    GetTypingDataProfilesOutput getTypingDataProfiles(String projectId, String typingDataId, int limit, int offset,
                                                      String userId);

    /**
     * Gets an isolate data's schema.
     *
     * @param projectId     the id of the project
     * @param isolateDataId the id of the isolate data
     * @param userId        the id of the user
     * @return the isolate data schema
     */
    GetIsolateDataSchemaOutput getIsolateDataSchema(String projectId, String isolateDataId, String userId);

    /**
     * Gets an isolate data's rows, with pagination.
     *
     * @param projectId     the id of the project
     * @param isolateDataId the id of the isolate data
     * @param limit         the limit of the rows
     * @param offset        the offset of the rows
     * @param userId        the id of the user
     * @return the isolate data rows
     */
    GetIsolateDataRowsOutput getIsolateDataRows(String projectId, String isolateDataId, int limit, int offset,
                                                String userId);
}