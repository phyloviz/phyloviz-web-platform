import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import { Container } from "@mui/material"
import { useRef } from "react"
import { VisualizationService } from "../../../Services/visualization/VisualizationService"
import { useProjectContext } from "../useProject"
import { GetTreeViewOutputModel } from "../../../Services/visualization/models/getTreeView/GetTreeViewOutputModel"
import { useParams } from "react-router-dom"
import { GraphConfigInterface, TreeViewGraph } from "./cosmos/TreeViewGraph"


export type Node = {
    id: string;
    // x: number;
    // y: number;
};

export type Link = {
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

    const graphRef = useRef<TreeViewGraph<Node, Link>>()

    React.useEffect(() => {
        async function init() {
            const data: GetTreeViewOutputModel = await VisualizationService.getTreeView(projectId!, datasetId!, treeViewId!)

            const nodes = data.nodes.map(node => {
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

            const config: GraphConfigInterface<Node, Link> = {
                backgroundColor: "#FFFFFF",
                nodeSize: 4,
                nodeColor: "#4B5BBF",
                nodeGreyoutOpacity: 0.1,
                linkWidth: 0.1,
                linkColor: "#5F74C2",
                linkArrows: false,
                linkGreyoutOpacity: 0,
                simulation: {
                    linkDistance: 1,
                    linkSpring: 2,
                    repulsion: 0.2,
                    gravity: 0.1,
                    decay: 100000
                }
            }

            const graph = new TreeViewGraph<Node, Link>(canvasRef.current!, config)

            graph.setData(nodes, links)

            graphRef.current = graph
        }

        init()
    }, [])


    return (
        <Container>
            <Paper sx={{
                p: 4,
                display: "flex",
                flexDirection: "column",
                mt: 4,
                alignItems: "center"
            }}>
                <Typography component="h1" variant="h4">
                    Tree View
                </Typography>
                <canvas ref={canvasRef}>

                </canvas>
            </Paper>
        </Container>
    )
}
