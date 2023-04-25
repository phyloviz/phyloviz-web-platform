import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import { Box, Container } from "@mui/material"
import { useRef } from "react"
import { useProjectContext } from "../useProject"
import { Node, Edge, GetTreeViewOutputModel } from "../../../Services/visualization/models/getTreeView/GetTreeViewOutputModel"
import { useParams } from "react-router-dom"
import { TreeViewGraph } from "./cosmos/TreeViewGraph"
import VisualizationService from "../../../Services/visualization/VisualizationService";
import { GraphConfigInterface } from "./cosmos/config"

export type VizNode = {
    id: string;
    // x: number;
    // y: number;
};

export type VizLink = {
    source: string;
    target: string;
};


/**
 * TreeView page.
 */
export default function TreeView() {
    // const { } = useTreeView()
    const { projectId, datasetId, treeViewId } = useParams<{ projectId: string, datasetId: string, treeViewId: string }>()
    const canvasRef = useRef<HTMLCanvasElement>(null)

    const graphRef = useRef<TreeViewGraph<VizNode, VizLink>>()


    React.useEffect(() => {
        async function init() {
            const data: GetTreeViewOutputModel = await VisualizationService.getTreeView(projectId!, datasetId!, treeViewId!)

            function findBiggestGroup(nodes: Node[], edges: Edge[]): Node[] {
                const visited = new Set<string>();
                let maxGroup: Node[] = [];
                const groups = []

                for (const node of nodes) {
                    if (!visited.has(node.st)) {
                        const group = dfs(node, nodes, edges, visited);
                        groups.push(group);
                        if (group.length > maxGroup.length) {
                            maxGroup = group;
                        }
                    }
                }

                return maxGroup;
            }

            function dfs(node: Node, nodes: Node[], edges: Edge[], visited: Set<string>): Node[] {
                const group: Node[] = [];
                const stack: Node[] = [node];

                while (stack.length > 0) {
                    const current = stack.pop()!;
                    visited.add(current.st);
                    group.push(current);

                    for (const edge of edges) {
                        if (edge.from === current.st && !visited.has(edge.to)) {
                            const neighbor = nodes.find(node => node.st === edge.to)!;
                            stack.push(neighbor);
                            visited.add(neighbor.st);
                        }
                    }
                }

                return group;
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
                spaceSize: 8192,
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


    return (
        <Box sx={{ mx: "auto" }}>
            <Typography component="h1" variant="h4">
                Tree View
            </Typography>
            <canvas ref={canvasRef}>

            </canvas>
        </Box>
    )
}
