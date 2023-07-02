import {useEffect, useRef, useState} from "react";
import {TreeViewGraph} from "../cosmos/TreeViewGraph";
import {
    GetTreeViewOutputModel,
    Graph
} from "../../../../../../Services/Visualization/models/getTreeView/GetTreeViewOutputModel";
import VisualizationService from "../../../../../../Services/Visualization/VisualizationService";
import {findClusters} from "./useClusterCalculation";
import {defaultConfig, VizLink, VizNode} from "../useForceDirectedLayout";
import {
    DEFAULT_LINK_LABEL_SIZE,
    DEFAULT_LINK_LABEL_TYPE,
    DEFAULT_NODE_LABEL_SIZE
} from "./useGraphTransformationsConfig";
import {
    Row
} from "../../../../../../Services/Visualization/models/getIsolateDataProfiles/GetIsolateDataRowsOutputModel";

/**
 * This hook is responsible for fetching the data from the server and initializing the graph.
 *
 * @param projectId The id of the project.
 * @param datasetId The id of the dataset.
 * @param treeViewId The id of the tree view.
 */
export function useGraph(projectId: string, datasetId: string, treeViewId: string, isolateDataRows: Row[] | undefined) {
    const canvasRef = useRef<HTMLCanvasElement>(null)
    const graphRef = useRef<TreeViewGraph<VizNode, VizLink>>()
    const [loadingGraph, setLoadingGraph] = useState<boolean>(true)

    const [selectedCluster, setSelectedCluster] = useState<number>(0)

    const [autosave, setAutosave] = useState<boolean>(false)
    const [saveFlag, setSaveFlag] = useState<boolean>(false)
    const [savingGraph, setSavingGraph] = useState<boolean>(false)
    const [clusters, setClusters] = useState<Graph[]>([])
    const dataRef = useRef<GetTreeViewOutputModel>()
    // Load graph
    useEffect(() => {
        setLoadingGraph(true)

        async function init() {
            const data: GetTreeViewOutputModel = await VisualizationService.getTreeView(projectId, datasetId, treeViewId)

            dataRef.current = data
            const clusters = findClusters(data.nodes, data.edges)


            setClusters(clusters)

            if (!canvasRef.current) return // TODO sus canvasRef.current is null, service running twice -> async await promise problems?


            await loadCluster(clusters[0])
        }

        init()

        return () => {
            graphRef.current?.destroy()
            graphRef.current = undefined
        }
    }, [])

    async function loadCluster(graph: Graph) {
        setLoadingGraph(true)

        const data = dataRef.current!

        // const isolateDataIdMap = new Map<string, number>()
        //
        // if (isolateDataRows) {
        //     for (const row of isolateDataRows) {
        //         const count = isolateDataIdMap.get(row.id) ?? 0
        //         isolateDataIdMap.set(row.id, count + 1)
        //     }
        // }

        const config = data.transformations != null ? {
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
            },
        } : defaultConfig
        //
        // config.nodeSize = (node) => {
        //     const numIsolates = isolateDataIdMap.get(node.id) ?? 0
        //     console.log("numIsolates", numIsolates)
        //     const defaultNodeSize = data?.transformations?.nodeSize ?? defaultConfig.nodeSize
        //     return defaultNodeSize + Math.log(numIsolates)
        // }

        const treeViewGraph = new TreeViewGraph<VizNode, VizLink>(canvasRef.current!, config)

        if (graphRef.current) {
            graphRef.current.destroy()
        }

        graphRef.current = treeViewGraph

        const links = graph.edges.map(edge => {
            return {
                source: edge.from,
                target: edge.to,
                weight: edge.weight
            }
        })

        const nodes = graph.nodes.map(node => {
            return {
                id: node.st,
                x: node.coordinates[0],
                y: node.coordinates[1],
            }
        })

        await graphRef.current!.setData(nodes, links)

        console.log("finished loading graph")
        setLoadingGraph(false)
    }

    // Save graph
    useEffect(() => {
            if (loadingGraph || !graphRef.current) return

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
                    nodeLabel: graphRef.current?.nodeLabelsRendered(),
                    nodeLabelSize: DEFAULT_NODE_LABEL_SIZE, // TODO
                    linkWidth: graphRef.current.config.linkWidth,
                    linkLabel: graphRef.current?.linkLabelsRendered(),
                    linkLabelSize: DEFAULT_LINK_LABEL_SIZE, // TODO
                    linkLabelType: DEFAULT_LINK_LABEL_TYPE // TODO
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
        selectedCluster,
        focusRandom: () => graphRef.current?.zoomToNodeById(clusters[selectedCluster].nodes[Math.floor(Math.random() * clusters[selectedCluster].nodes.length)].st),
        setSelectedCluster: (i: number) => {

            const cluster = clusters[i]
            if (cluster.nodes.length === 0) {
                throw new Error("Cluster has no nodes")
            }

            if (cluster.edges.length === 0) {
                cluster.edges = [{
                    from: cluster.nodes[0].st,
                    to: cluster.nodes[0].st,
                    weight: 1
                }]
            }

            loadCluster(cluster)

            setSelectedCluster(i)
        },
        numClusters: clusters.length,
        autosave,
        switchAutosave: () => setAutosave((prev) => !prev),
        forceSave: () => setSaveFlag((prev) => !prev),
        savingGraph
    }
}