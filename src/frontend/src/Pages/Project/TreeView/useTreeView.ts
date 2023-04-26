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
    CascadingInfoTreeView, DistanceMatrix,
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

/**
 * Hook for the TreeView page.
 */
export function useTreeView() {
    const {projectId, datasetId, treeViewId} = useParams<{ projectId: string, datasetId: string, treeViewId: string }>()
    const {project} = useProjectContext()
    const [linkSpring, setLinkSpring] = useState(1)
    const [linkDistance, setLinkDistance] = useState(10)
    const canvasRef = useRef<HTMLCanvasElement>(null)
    const graphRef = useRef<TreeViewGraph<VizNode, VizLink>>()

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

            const config: GraphConfigInterface<VizNode, VizLink> = {
                backgroundColor: "#FFFFFF",
                nodeSize: 4,
                nodeColor: "#4B5BBF",
                nodeGreyoutOpacity: 0.1,
                linkWidth: 0.1,
                linkColor: "#5F74C2",
                linkArrows: false,
                linkGreyoutOpacity: 0,
                simulation: {
                    decay: Infinity,
                    gravity: 0.01,
                    repulsionQuadtreeLevels: 0.3,
                    repulsionTheta: 0.5,
                    linkDistance: 10,
                    linkSpring: 1,
                    friction: 0.85,
                    repulsion: 0.2,
                }
            }

            const graph = new TreeViewGraph<VizNode, VizLink>(canvasRef.current!, config)

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

    const distanceMatrix = tree.sourceType === "algorithmDistanceMatrix" ? project?.datasets
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
        treeView: cascadingInfoTreeView,
        pauseAnimation: () => graphRef.current?.pause(),
        restartAnimation: () => graphRef.current?.restart(),
        linkSpring,
        updateLinkSpring: (value: number) => {
            setLinkSpring(value)
            graphRef.current?.setConfig({simulation: {linkSpring: value}})
        },
        linkDistance,
        updateLinkDistance: (value: number) => {
            setLinkDistance(value)
            graphRef.current?.setConfig({simulation: {linkDistance: value}})
        },
        canvasRef,
        toPrintRef,
        handlePrint
    }
}