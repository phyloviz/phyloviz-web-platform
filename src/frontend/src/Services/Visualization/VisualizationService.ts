import {get} from "../utils/apiFetch"
import {WebApiUris} from "../WebApiUris"
import {GetTypingDataSchemaOutputModel} from "./models/getTypingDataSchema/GetTypingDataSchemaOutputModel"
import {GetTypingDataProfilesOutputModel} from "./models/getTypingDataProfiles/GetTypingDataProfilesOutputModel"
import {GetIsolateDataKeysOutputModel} from "./models/getIsolateDataSchema/GetIsolateDataKeysOutputModel"
import {GetIsolateDataRowsOutputModel} from "./models/getIsolateDataProfiles/GetIsolateDataRowsOutputModel"
import {GetDistanceMatrixOutputModel} from "./models/getDistanceMatrix/GetDistanceMatrixOutputModel"
import {GetTreeOutputModel} from "./models/getTree/GetTreeOutputModel"
import {GetTreeViewOutputModel} from "./models/getTreeView/GetTreeViewOutputModel"
import {MockVisualizationService} from "./MockVisualizationService"

namespace VisualizationService {

    /**
     * Get the typing data schema.
     *
     * @param projectId The project id.
     * @param typingDataId The typing data id.
     * @returns The typing data schema.
     */
    export async function getTypingDataSchema(
        projectId: string,
        typingDataId: string
    ): Promise<GetTypingDataSchemaOutputModel> {
        return await get<GetTypingDataSchemaOutputModel>(WebApiUris.getTypingDataSchema(projectId, typingDataId))
    }

    /**
     * Get the typing data profiles.
     *
     * @param projectId The project id.
     * @param typingDataId The typing data id.
     * @param limit The limit.
     * @param offset The offset.
     * @returns The typing data profiles.
     */
    export async function getTypingDataProfiles(
        projectId: string,
        typingDataId: string,
        limit: number,
        offset: number
    ): Promise<GetTypingDataProfilesOutputModel> {
        return await get<GetTypingDataProfilesOutputModel>(WebApiUris.getTypingDataProfiles(projectId, typingDataId, limit, offset))
    }

    /**
     * Gets the keys of an isolate data.
     *
     * @param projectId The project id.
     * @param isolateDataId The isolate data id.
     * @returns The isolate data keys.
     */
    export async function getIsolateDataKeys(
        projectId: string,
        isolateDataId: string
    ): Promise<GetIsolateDataKeysOutputModel> {
        return await get<GetIsolateDataKeysOutputModel>(WebApiUris.getIsolateDataKeys(projectId, isolateDataId))
    }

    /**
     * Get the isolate data rows.
     *
     * @param projectId The project id.
     * @param isolateDataId The isolate data id.
     * @param limit The limit.
     * @param offset The offset.
     * @returns The isolate data rows.
     */
    export async function getIsolateDataRows(
        projectId: string,
        isolateDataId: string,
        limit: number,
        offset: number
    ): Promise<GetIsolateDataRowsOutputModel> {
        return await get<GetIsolateDataRowsOutputModel>(WebApiUris.getIsolateDataRows(projectId, isolateDataId, limit, offset))
    }

    /**
     * Get the distance matrix.
     *
     * @param projectId The project id.
     * @param datasetId The dataset id.
     * @param distanceMatrixId The distance matrix id.
     * @returns The distance matrix.
     */
    export async function getDistanceMatrix(
        projectId: string,
        datasetId: string,
        distanceMatrixId: string
    ): Promise<GetDistanceMatrixOutputModel> {
        return await get<any>(WebApiUris.getDistanceMatrix(projectId, datasetId, distanceMatrixId))
    }

    /**
     * Get the tree.
     *
     * @param projectId The project id.
     * @param datasetId The dataset id.
     * @param treeId The tree id.
     * @returns The tree.
     */
    export async function getTree(
        projectId: string,
        datasetId: string,
        treeId: string
    ): Promise<GetTreeOutputModel> {
        return await get<any>(WebApiUris.getTree(projectId, datasetId, treeId))
    }

    /**
     * Get the tree view.
     *
     * @param projectId The project id.
     * @param datasetId The dataset id.
     * @param treeViewId The tree view id.
     * @returns The tree view.
     */
    export async function getTreeView(
        projectId: string,
        datasetId: string,
        treeViewId: string
    ): Promise<GetTreeViewOutputModel> {
        return await get<any>(WebApiUris.getTreeView(projectId, datasetId, treeViewId))
    }
}

const env = process.env.MOCK_ENV

export default env === "true" ? MockVisualizationService : VisualizationService
