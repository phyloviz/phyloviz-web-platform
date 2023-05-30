import {Edge, Node} from "../../../../../../Services/Visualization/models/getTreeView/GetTreeViewOutputModel";

/**
 * Returns a function that finds the biggest group of nodes in a graph.
 */
export function useClusterCalculation() {

    /**
     * Finds the biggest group of nodes in a graph.
     *
     * @param nodes The nodes in the graph.
     * @param edges The edges in the graph.
     * @returns The biggest group of nodes in the graph.
     */
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

    /**
     * Performs a depth-first search on a graph.
     *
     * @param node The node to start the search from.
     * @param nodes The nodes in the graph.
     * @param edges The edges in the graph.
     * @param visited The nodes that have already been visited.
     * @returns The nodes that were visited.
     */
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

    return {
        findBiggestGroup
    }
}