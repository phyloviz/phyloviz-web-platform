import {GetTypingDataSchemaOutputModel} from "./models/getTypingDataSchema/GetTypingDataSchemaOutputModel"
import {GetTypingDataProfilesOutputModel} from "./models/getTypingDataProfiles/GetTypingDataProfilesOutputModel"
import {GetIsolateDataKeysOutputModel} from "./models/getIsolateDataSchema/GetIsolateDataKeysOutputModel"
import {GetIsolateDataRowsOutputModel} from "./models/getIsolateDataProfiles/GetIsolateDataRowsOutputModel"
import {GetDistanceMatrixOutputModel} from "./models/getDistanceMatrix/GetDistanceMatrixOutputModel"
import {GetTreeOutputModel} from "./models/getTree/GetTreeOutputModel"
import {GetTreeViewOutputModel} from './models/getTreeView/GetTreeViewOutputModel';
import {SaveTreeViewInputModel} from "./models/saveTreeView/SaveTreeViewInputModel"
import {SaveTreeViewOutputModel} from "./models/saveTreeView/SaveTreeViewOutputModel";

export namespace MockVisualizationService {

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
        return await fetch(`${MOCK_DIR}/typing-data-profiles.json`).then(response => response.json())
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
        const row = ({
            "continent": "Oceania",
            "country": "New Zealand",
            "datestamp": "2019-06-19",
            "isolate": "PH155",
            "curator": "20",
            "penner": "Untypable",
            "sender": "47",
            "species": "Campylobacter coli",
            "year": "2001",
            "date_entered": "2007-12-18",
            "source": "sheep"
        })
        // return {
        //     rows: [
        //         {
        //             "id": "1",
        //             "profileId": "3",
        //             "row":  row as any
        //         },
        //     ],
        //     totalCount: 1,
        // }
        return await fetch(`${MOCK_DIR}/isolate-data-rows.json`).then(response => response.json())
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
        return "(ant:17, (bat:31, cow:22):7, dog:22, (elk:33, fox:12):40);"
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

    /**
     * Saves a tree view coordinates and applied transformations.
     *
     * @param projectId  the id of the project
     * @param datasetId  the id of the dataset
     * @param treeViewId the id of the tree view
     * @param inputModel the input mode
     * @return the tree view
     */
    export async function saveTreeView(
        projectId: string,
        datasetId: string,
        treeViewId: string,
        inputModel: SaveTreeViewInputModel
    ): Promise<SaveTreeViewOutputModel> {
        return await new Promise(resolve => setTimeout(() => resolve({
            projectId: projectId,
            datasetId: datasetId,
            treeViewId: treeViewId
        })))
    }
}