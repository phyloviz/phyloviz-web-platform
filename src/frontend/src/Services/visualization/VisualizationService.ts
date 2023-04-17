import {get} from "../utils/apiFetch"
import {WebApiUris} from "../WebApiUris"
import {GetTypingDataSchemaOutputModel} from "./models/getTypingDataSchema/GetTypingDataSchemaOutputModel"
import {GetTypingDataProfilesOutputModel} from "./models/getTypingDataProfiles/GetTypingDataProfilesOutputModel"
import {GetTypingDataFileOutputModel} from "./models/getTypingDataFile/GetTypingDataFileOutputModel"
import {GetIsolateDataSchemaOutputModel} from "./models/getIsolateDataSchema/GetIsolateDataSchemaOutputModel"
import {GetIsolateDataRowsOutputModel} from "./models/getIsolateDataProfiles/GetIsolateDataRowsOutputModel"
import {GetIsolateDataFileOutputModel} from "./models/getIsolateDataFile/GetIsolateDataFileOutputModel"
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
     * @returns The typing data profiles.
     */
    export async function getTypingDataProfiles(
        projectId: string,
        typingDataId: string
    ): Promise<GetTypingDataProfilesOutputModel> {
        return await get<GetTypingDataProfilesOutputModel>(WebApiUris.getTypingDataProfiles(projectId, typingDataId))
    }

    /**
     * Get the typing data file.
     *
     * @param projectId The project id.
     * @param typingDataId The typing data id.
     * @returns The typing data file.
     */
    export async function getTypingDataFile(
        projectId: string,
        typingDataId: string
    ): Promise<GetTypingDataFileOutputModel> {
        return await get<GetTypingDataFileOutputModel>(WebApiUris.getTypingDataFile(projectId, typingDataId))
    }

    /**
     * Get the isolate data schema.
     *
     * @param projectId The project id.
     * @param isolateDataId The isolate data id.
     * @returns The isolate data schema.
     */
    export async function getIsolateDataSchema(
        projectId: string,
        isolateDataId: string
    ): Promise<GetIsolateDataSchemaOutputModel> {
        return await get<GetIsolateDataSchemaOutputModel>(WebApiUris.getIsolateDataSchema(projectId, isolateDataId))
    }

    /**
     * Get the isolate data rows.
     *
     * @param projectId The project id.
     * @param isolateDataId The isolate data id.
     * @returns The isolate data rows.
     */
    export async function getIsolateDataRows(
        projectId: string,
        isolateDataId: string
    ): Promise<GetIsolateDataRowsOutputModel> {
        return await get<GetIsolateDataRowsOutputModel>(WebApiUris.getIsolateDataRows(projectId, isolateDataId))
    }

    /**
     * Get the isolate data file.
     *
     * @param projectId The project id.
     * @param isolateDataId The isolate data id.
     * @returns The isolate data file.
     */
    export async function getIsolateDataFile(
        projectId: string,
        isolateDataId: string
    ): Promise<GetIsolateDataFileOutputModel> {
        return await get<GetIsolateDataFileOutputModel>(WebApiUris.getIsolateDataFile(projectId, isolateDataId))
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

const env = process.env.NODE_ENV

export default env === "development" ? MockVisualizationService : VisualizationService
