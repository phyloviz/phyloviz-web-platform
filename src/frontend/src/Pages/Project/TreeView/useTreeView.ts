import {useParams} from "react-router-dom"
import * as React from 'react';
import {useEffect, useRef, useState} from 'react';
import {TreeViewGraph} from "./cosmos/TreeViewGraph"
import {
    Edge,
    GetTreeViewOutputModel,
    Node
} from "../../../Services/Visualization/models/getTreeView/GetTreeViewOutputModel"
import VisualizationService from "../../../Services/Visualization/VisualizationService"
import {
    AlgorithmDistanceMatrixTreeSource,
    CascadingInfoAlgorithmDistanceMatrixTreeSource,
    CascadingInfoAlgorithmTypingDataTreeSource,
    CascadingInfoFileTreeSource,
    CascadingInfoTree,
    CascadingInfoTreeView,
    DistanceMatrix,
    Tree,
    TreeView
} from "../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {useProjectContext} from "../useProject"
import {GraphConfigInterface} from "./cosmos/config"
import {useReactToPrint} from "react-to-print";

export type VizNode = {
    id: string
    // x: number
    // y: number
}

export type VizLink = {
    source: string
    target: string
}

const defaultConfig: GraphConfigInterface<VizNode, VizLink> = {
    backgroundColor: "#FFFFFF",
    nodeSize: 4,
    nodeColor: "#4B5BBF",
    nodeGreyoutOpacity: 0.1,
    linkWidth: 0.1,
    linkColor: "#5F74C2",
    linkArrows: false,
    linkGreyoutOpacity: 0,
    simulation: {
        decay: 100000,
        gravity: 0.01,
        repulsionTheta: 0.5,
        linkDistance: 10,
        linkSpring: 1,
        friction: 0.85,
        repulsion: 0.2,
    }
}

const ZOOM_IN_SCALE = 1.1
const ZOOM_OUT_SCALE = 0.9
const ZOOM_DURATION = 100

/**
 * Hook for the TreeView page.
 */
export function useTreeView() {
    const {projectId, datasetId, treeViewId} = useParams<{ projectId: string, datasetId: string, treeViewId: string }>()
    const {project} = useProjectContext()
    const canvasRef = useRef<HTMLCanvasElement>(null)
    const graphRef = useRef<TreeViewGraph<VizNode, VizLink>>()
    const [linkSpring, setLinkSpring] = useState(defaultConfig.simulation!.linkSpring!)
    const [linkDistance, setLinkDistance] = useState(defaultConfig.simulation!.linkDistance!)
    const [gravity, setGravity] = useState(defaultConfig.simulation!.gravity!)
    const [repulsion, setRepulsion] = useState(defaultConfig.simulation!.repulsion!)
    const [friction, setFriction] = useState(defaultConfig.simulation!.friction!)
    const [repulsionTheta, setRepulsionTheta] = useState(defaultConfig.simulation!.repulsionTheta!)
    const [decay, setDecay] = useState(defaultConfig.simulation!.decay!)

    const toPrintRef = useRef(null);
    const reactToPrintContent = React.useCallback(() => {
        return toPrintRef.current;
    }, [toPrintRef.current]);
    const handlePrint = useReactToPrint({
        content: reactToPrintContent,
        documentTitle: "Tree Visualization"
    });

    useEffect(() => {
        async function init() {
            const data: GetTreeViewOutputModel = await VisualizationService.getTreeView(projectId!, datasetId!, treeViewId!)

            function findBiggestGroup(nodes: Node[], edges: Edge[]): Node[] {
                const visited = new Set<string>()
                let maxGroup: Node[] = []

                for (const node of nodes) {
                    if (!visited.has(node.st)) {
                        const group = dfs(node, nodes, edges, visited)
                        if (group.length > maxGroup.length)
                            maxGroup = group
                    }
                }

                return maxGroup
            }

            function dfs(node: Node, nodes: Node[], edges: Edge[], visited: Set<string>): Node[] {
                const group: Node[] = []
                const stack: Node[] = [node]

                while (stack.length > 0) {
                    const current = stack.pop()!
                    visited.add(current.st)
                    group.push(current)

                    for (const edge of edges) {
                        if (edge.from === current.st && !visited.has(edge.to)) {
                            const neighbor = nodes.find(node => node.st === edge.to)!
                            stack.push(neighbor)
                            visited.add(neighbor.st)
                        }
                    }
                }

                return group
            }

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
                }
            })

            const graph = new TreeViewGraph<VizNode, VizLink>(canvasRef.current!, defaultConfig)
            graph.setData(nodes, links)
            graphRef.current = graph
        }

        init()
    }, [])

    const treeView: TreeView = project?.datasets
        .find(dataset => dataset.datasetId === datasetId)?.treeViews
        .find(treeView => treeView.treeViewId === treeViewId) as TreeView

    const tree: Tree = project?.datasets
        .find(dataset => dataset.datasetId === datasetId)?.trees
        .find(tree => tree.treeId === treeView.source.treeId) as Tree

    const distanceMatrix = tree.sourceType === "algorithmDistanceMatrix"
        ? project?.datasets
            .find(dataset => dataset.datasetId === datasetId)?.distanceMatrices
            .find(distanceMatrix =>
                distanceMatrix.distanceMatrixId === (tree.source as AlgorithmDistanceMatrixTreeSource).distanceMatrixId) as DistanceMatrix
        : undefined

    const cascadingInfoTree: CascadingInfoTree = {
        treeId: tree.treeId,
        name: tree.name,
        sourceType: tree.sourceType,
        source:
            tree.sourceType === "algorithmTypingData"
                ? tree.source as CascadingInfoAlgorithmTypingDataTreeSource
                : tree.sourceType === "algorithmDistanceMatrix"
                    ? {
                        algorithm: (tree.source as AlgorithmDistanceMatrixTreeSource).algorithm,
                        distanceMatrix: distanceMatrix,
                        parameters: (tree.source as AlgorithmDistanceMatrixTreeSource).parameters
                    } as CascadingInfoAlgorithmDistanceMatrixTreeSource
                    : tree.source as CascadingInfoFileTreeSource
    }

    const cascadingInfoTreeView: CascadingInfoTreeView = {
        treeViewId: treeView.treeViewId,
        name: treeView.name,
        layout: treeView.layout,
        source: {
            tree: cascadingInfoTree,
            typingDataId: treeView.source.typingDataId,
            isolateDataId: treeView.source.isolateDataId
        }
    } as CascadingInfoTreeView

    return {
        canvasRef,
        treeView: cascadingInfoTreeView,
        pauseAnimation: () => graphRef.current?.pause(),
        restartAnimation: () => graphRef.current?.restart(),
        resetSimulationConfig: () => {
            setLinkSpring(defaultConfig.simulation!.linkSpring!)
            setLinkDistance(defaultConfig.simulation!.linkDistance!)
            setGravity(defaultConfig.simulation!.gravity!)
            setRepulsion(defaultConfig.simulation!.repulsion!)
            setFriction(defaultConfig.simulation!.friction!)
            setRepulsionTheta(defaultConfig.simulation!.repulsionTheta!)
            setDecay(defaultConfig.simulation!.decay!)

            graphRef.current?.setConfig(defaultConfig)
        },

        resetSimulationFilters: () => {
            // TODO: reset filters
        },

        linkSpring,
        linkDistance,
        gravity,
        friction,
        repulsion,
        repulsionTheta,
        decay,

        updateLinkSpring: (value: number) => {
            setLinkSpring(value)
            graphRef.current?.setConfig({simulation: {linkSpring: value}})
        },
        updateLinkDistance: (value: number) => {
            setLinkDistance(value)
            graphRef.current?.setConfig({simulation: {linkDistance: value}})
        },
        updateGravity: (value: number) => {
            setGravity(value)
            graphRef.current?.setConfig({simulation: {gravity: value}})
        },
        updateFriction: (value: number) => {
            setFriction(value)
            graphRef.current?.setConfig({simulation: {friction: value}})
        },
        updateRepulsion: (value: number) => {
            setRepulsion(value)
            graphRef.current?.setConfig({simulation: {repulsion: value}})
        },
        updateRepulsionTheta: (value: number) => {
            setRepulsionTheta(value)
            graphRef.current?.setConfig({simulation: {repulsionTheta: value}})
        },
        updateDecay: (value: number) => {
            setDecay(value)
            graphRef.current?.setConfig({simulation: {decay: value}})
        },

        handleExportOptions: () => {
            // TODO: implement export options
        },
        handleExportFilters: () => {
            // TODO: implement export filters
        },

        handleZoomIn: () => graphRef.current?.zoom(graphRef.current.getZoomLevel() * ZOOM_IN_SCALE, ZOOM_DURATION),
        handleZoomOut: () => graphRef.current?.zoom(graphRef.current.getZoomLevel() * ZOOM_OUT_SCALE, ZOOM_DURATION),

        toPrintRef,
        handlePrint
    }
}
