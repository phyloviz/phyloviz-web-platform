import {Edge, Node} from "../../../Services/Visualization/models/getTreeView/GetTreeViewOutputModel";

export function useClusterCalculation() {
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

    return {
        findBiggestGroup
    }
}