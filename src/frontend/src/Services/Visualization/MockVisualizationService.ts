import {GetTypingDataSchemaOutputModel} from "./models/getTypingDataSchema/GetTypingDataSchemaOutputModel"
import {GetTypingDataProfilesOutputModel} from "./models/getTypingDataProfiles/GetTypingDataProfilesOutputModel"
import {GetTypingDataFileOutputModel} from "./models/getTypingDataFile/GetTypingDataFileOutputModel"
import {GetIsolateDataKeysOutputModel} from "./models/getIsolateDataSchema/GetIsolateDataKeysOutputModel"
import {GetIsolateDataRowsOutputModel} from "./models/getIsolateDataProfiles/GetIsolateDataRowsOutputModel"
import {GetIsolateDataFileOutputModel} from "./models/getIsolateDataFile/GetIsolateDataFileOutputModel"
import {GetDistanceMatrixOutputModel} from "./models/getDistanceMatrix/GetDistanceMatrixOutputModel"
import {GetTreeOutputModel} from "./models/getTree/GetTreeOutputModel"
import {GetTreeViewOutputModel} from './models/getTreeView/GetTreeViewOutputModel';

export namespace MockVisualizationService {

    const DELAY = 1000
    const MOCK_DIR = "/mock"

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
        return await fetch(`${MOCK_DIR}/typing-data-schema.json`).then(response => response.json())
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
        return await fetch(`${MOCK_DIR}/typing-data-profiles.json`).then(response => response.json())
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
        return {}
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
        return await fetch(`${MOCK_DIR}/isolate-data-keys.json`).then(response => response.json())
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
        return await fetch(`${MOCK_DIR}/isolate-data-rows.json`).then(response => response.json())
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
        return {}
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
        return {
            distances: {},
            totalCount: 0
        }
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
        return {}
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
        return await fetch(`${MOCK_DIR}/tree-view.json`).then(response => response.json())
    }
}