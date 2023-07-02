import {Edge, Graph, Node} from "../../../../../../Services/Visualization/models/getTreeView/GetTreeViewOutputModel";


/**
 * Finds the biggest group of nodes in a graph.
 *
 * @param nodes The nodes in the graph.
 * @param edges The edges in the graph.
 * @returns The biggest group of nodes in the graph.
 */
export function findClusters(nodes: Node[], edges: Edge[]): Graph[] {
    const visited = new Set<string>()
    let clusters: Graph[] = []

    for (const node of nodes) {
        if (!visited.has(node.st)) {
            const cluster = dfs(node, nodes, edges, visited)

            clusters.push(cluster)
        }
    }

    clusters = clusters.sort((a, b) => b.nodes.length - a.nodes.length)

    return clusters
}

/**
 * Performs a depth-first traversal on a graph.
 *
 * @param node The node to start the search from.
 * @param nodes The nodes in the graph.
 * @param edges The edges in the graph.
 * @param visited The nodes that have already been visited.
 * @returns The nodes that were visited.
 */
export function dfs(node: Node, nodes: Node[], edges: Edge[], visited: Set<string>): Graph {
    const clusterNodes: Node[] = []
    const clusterEdges : Edge[] = []
    const stack: Node[] = [node]

    while (stack.length > 0) {
        const current = stack.pop()!
        visited.add(current.st)
        clusterNodes.push(current)

        for (const edge of edges) { //TODO: add hashmap for edges
            if (edge.from === current.st && !visited.has(edge.to)) {
                const neighbor = nodes.find(node => node.st === edge.to)!
                stack.push(neighbor)
                visited.add(neighbor.st)
                clusterEdges.push(edge)
            }
        }
    }

    return {
        nodes: clusterNodes,
        edges: clusterEdges
    }
}

