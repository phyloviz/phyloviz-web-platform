import {useEffect, useRef} from "react";
import {TreeViewGraph} from "../cosmos/TreeViewGraph";
import {
    GetTreeViewOutputModel
} from "../../../../../../Services/Visualization/models/getTreeView/GetTreeViewOutputModel";
import VisualizationService from "../../../../../../Services/Visualization/VisualizationService";
import {useClusterCalculation} from "./useClusterCalculation";
import {defaultConfig, VizLink, VizNode} from "../useForceDirectedLayout";

/**
 * This hook is responsible for fetching the data from the server and initializing the graph.
 *
 * @param projectId The id of the project.
 * @param datasetId The id of the dataset.
 * @param treeViewId The id of the tree view.
 */
export function useGraph(projectId: string, datasetId: string, treeViewId: string) {
    const canvasRef = useRef<HTMLCanvasElement>(null)
    const graphRef = useRef<TreeViewGraph<VizNode, VizLink>>()

    const {findBiggestGroup} = useClusterCalculation()

    useEffect(() => {
        async function init() {
            const data: GetTreeViewOutputModel = await VisualizationService.getTreeView(projectId, datasetId, treeViewId)

            const nodes = findBiggestGroup(data.nodes, data.edges).map(node => {
                return {
                    id: node.st,
                    // x: node.coordinates[0],
                    // y: node.coordinates[1],
                }
            })

            const links = data.edges.map(edge => {
                return {
                    source: edge.from,
                    target: edge.to,
                    weight: edge.weight
                }
            })

            if(!canvasRef.current) return // TODO sus canvasRef.current is null, service running twice -> async await promise problems?

            const graph = new TreeViewGraph<VizNode, VizLink>(canvasRef.current!, defaultConfig)
            await graph.setData(nodes, links)
            graphRef.current = graph
        }

        init()

        return () => {
            graphRef.current?.destroy()
            graphRef.current = undefined
        }
    }, [])

    return {
        graphRef,
        canvasRef
    }
}