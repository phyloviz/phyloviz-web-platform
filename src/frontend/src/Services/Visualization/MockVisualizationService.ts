import {GetTypingDataSchemaOutputModel} from "./models/getTypingDataSchema/GetTypingDataSchemaOutputModel"
import {GetTypingDataProfilesOutputModel} from "./models/getTypingDataProfiles/GetTypingDataProfilesOutputModel"
import {GetTypingDataFileOutputModel} from "./models/getTypingDataFile/GetTypingDataFileOutputModel"
import {GetIsolateDataSchemaOutputModel} from "./models/getIsolateDataSchema/GetIsolateDataSchemaOutputModel"
import {GetIsolateDataRowsOutputModel} from "./models/getIsolateDataProfiles/GetIsolateDataRowsOutputModel"
import {GetIsolateDataFileOutputModel} from "./models/getIsolateDataFile/GetIsolateDataFileOutputModel"
import {GetDistanceMatrixOutputModel} from "./models/getDistanceMatrix/GetDistanceMatrixOutputModel"
import {GetTreeOutputModel} from "./models/getTree/GetTreeOutputModel"
import {GetTreeViewOutputModel} from './models/getTreeView/GetTreeViewOutputModel';

export namespace MockVisualizationService {

    const DELAY = 1000

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
        return {
            type: "mlst7",
            loci: [
                "aspA",
                "glnA",
                "gltA",
                "glyA",
                "pgm",
                "tkt",
                "uncA"
            ],
            totalCount: 7
        }
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
        return new Promise(resolve => setTimeout(resolve, DELAY))
            .then(() => ({
                    profiles: [
                        {id: "1", profile: ["1", "2", "1", "54", "3", "4", "1", "5"]},
                        {id: "2", profile: ["2", "4", "7", "51", "4", "1", "7", "1"]},
                        {id: "3", profile: ["3", "3", "2", "5", "10", "11", "11", "6"]},
                        {id: "4", profile: ["4", "10", "11", "16", "7", "10", "5", "7"]},
                        {id: "5", profile: ["5", "7", "2", "5", "2", "10", "3", "6"]},
                        {id: "6", profile: ["6", "63", "34", "27", "33", "45", "5", "7"]},
                        {id: "7", profile: ["7", "8", "10", "2", "2", "14", "12", "6"]},
                        {id: "8", profile: ["8", "2", "1", "1", "3", "2", "1", "6"]},
                        {id: "9", profile: ["9", "1", "6", "22", "24", "12", "7", "1"]},
                        {id: "10", profile: ["10", "2", "59", "4", "38", "17", "12", "5"]}
                    ],
                    totalCount: 10
                })
            )
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
        return {
            type: "mlst7",
            headers: ["id", "isolate", "aliases", "country", "continent", "region", "town_or_city", "year", "month", "isolation_date"],
            totalCount: 10
        }
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
        return new Promise(resolve => setTimeout(resolve, DELAY))
            .then(() => (
                {
                    rows: [
                        {id: "1", row: ["1", "P09", "", "Unknown", "", "", "", "", "", ""]},
                        {id: "2", row: ["2", "P12", "", "Unknown", "", "", "", "", "", ""]},
                        {id: "3", row: ["3", "P18", "ATCC43439", "Canada", "North America", "", "", "", "", ""]},
                        {id: "4", row: ["4", "P22", "ATCC43448", "Canada", "North America", "", "", "", "", ""]},
                        {id: "5", row: ["5", "P26", "ATCC43477", "Unknown", "", "", "", "", "", ""]},
                        {id: "6", row: ["6", "P27", "", "Unknown", "", "", "", "", "", ""]},
                        {id: "7", row: ["7", "P31", "", "Unknown", "", "", "", "", "", ""]},
                        {id: "8", row: ["8", "P33", "", "Unknown", "", "", "", "", "", ""]},
                        {id: "9", row: ["9", "P43", "", "Canada", "North America", "Vancouver", "", "", "", ""]},
                        {id: "10", row: ["10", "P55", "", "Unknown", "", "", "", "", "", ""]}
                    ],
                    totalCount: 10
                }
            ))
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
        // // TODO: Don't forget to remove
        // const edges: Edge[] = [];
        // const nodes: Node[] = [];
        // const n = 10;
        // const m = 10;
        // for (let node = 0; node < n * m; node += 1) {
        //     nodes.push({ st: node.toString(), coordinates: [0, 0], profile: Array.from({ length: 8 }, () => Math.floor(Math.random() * 100).toString()), auxiliaryData: {} });
        //     const nextNode = node + 1;
        //     const bottomNode = node + n;
        //     const nodeLine = Math.floor(node / n);
        //     const nextNodeLine = Math.floor(nextNode / n);
        //     const bottomNodeLine = Math.floor(bottomNode / n);

        //     if (nodeLine === nextNodeLine)
        //         edges.push({ from: `${node}`, to: `${nextNode}` });
        //     if (bottomNodeLine < m)
        //         edges.push({ from: `${node}`, to: `${bottomNode}` });
        // }
        // return { nodes, edges }
        return await fetch('/mock/tree-view.json').then(response => response.json())
    }
}