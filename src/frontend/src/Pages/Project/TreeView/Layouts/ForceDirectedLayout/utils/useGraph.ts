import {useEffect, useRef, useState} from "react";
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
    const [loadingGraph, setLoadingGraph] = useState<boolean>(true)

    const {findBiggestGroup} = useClusterCalculation()

    const [autosave, setAutosave] = useState<boolean>(false)
    const [saveFlag, setSaveFlag] = useState<boolean>(false)
    const [savingGraph, setSavingGraph] = useState<boolean>(false)

    // Load graph
    useEffect(() => {
        setLoadingGraph(true)

        async function init() {
            const data: GetTreeViewOutputModel = await VisualizationService.getTreeView(projectId, datasetId, treeViewId)

            const nodes = findBiggestGroup(data.nodes, data.edges).map(node => {
                return {
                    id: node.st,
                    x: node.coordinates[0],
                    y: node.coordinates[1],
                }
            })

            const links = data.edges.map(edge => {
                return {
                    source: edge.from,
                    target: edge.to,
                    weight: edge.weight
                }
            })

            if (!canvasRef.current) return // TODO sus canvasRef.current is null, service running twice -> async await promise problems?

            const graph = new TreeViewGraph<VizNode, VizLink>(canvasRef.current, {
                ...defaultConfig,
                nodeSize: data.transformations.nodeSize,
                linkWidth: data.transformations.linkWidth,
                simulation: {
                    linkSpring: data.transformations.linkSpring,
                    linkDistance: data.transformations.linkDistance,
                    gravity: data.transformations.gravity,
                    repulsion: data.transformations.repulsion,
                    friction: data.transformations.friction,
                    repulsionTheta: data.transformations.repulsionTheta,
                    decay: data.transformations.decay
                }
            })
            await graph.setData(nodes, links)

            graphRef.current = graph

            console.log("finished loading graph")
            setLoadingGraph(false)
        }

        init()

        return () => {
            graphRef.current?.destroy()
            graphRef.current = undefined
        }
    }, [])

    // Save graph
    useEffect(() => {
            if (!graphRef.current) return

            setSavingGraph(true)
            console.log("saving graph")
            VisualizationService.saveTreeView(projectId, datasetId, treeViewId, {
                nodes: Object.entries(graphRef.current?.getNodePositions()).map(([st, coordinates]) => {
                    return {
                        st,
                        coordinates: Object.values(coordinates)
                    }
                }),
                transformations: {
                    linkSpring: graphRef.current.config.simulation.linkSpring!,
                    linkDistance: graphRef.current.config.simulation.linkDistance!,
                    gravity: graphRef.current.config.simulation.gravity!,
                    repulsion: graphRef.current.config.simulation.repulsion!,
                    friction: graphRef.current.config.simulation.friction!,
                    repulsionTheta: graphRef.current.config.simulation.repulsionTheta!,
                    decay: graphRef.current.config.simulation.decay!,
                    nodeSize: graphRef.current.config.nodeSize,
                    nodeLabel: true, // TODO
                    nodeLabelSize: 1, // TODO
                    linkWidth: graphRef.current.config.linkWidth,
                    linkLabel: false, // TODO
                    linkLabelSize: 1, // TODO
                    linkLabelType: "test" // TODO
                }
            }).finally(() => setSavingGraph(false))

        }, autosave ? [graphRef.current?.config, graphRef.current?.getNodePositions(), saveFlag] : [saveFlag]
        // If autosave is enabled, save the graph every time the config or the node positions change, or if the user forces a save
        // If autosave is disabled, only save the graph if the user forces a save
    )

    return {
        graphRef,
        canvasRef,
        loadingGraph,

        autosave,
        switchAutosave: () => setAutosave((prev) => !prev),
        forceSave: () => setSaveFlag((prev) => !prev),
        savingGraph
    }
}