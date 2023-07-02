export interface GetTreeViewOutputModel {
    nodes: Node[]
    nodesTotalCount: number
    edges: Edge[]
    edgesTotalCount: number
    transformations: Transformations
}

export interface Edge {
    from: string
    to: string
    weight: number
}

export interface Node {
    st: string
    coordinates: number[]
}

export interface Graph {
    nodes: Node[]
    edges: Edge[]
}

export interface Transformations {
    linkSpring: number
    linkDistance: number
    gravity: number
    repulsion: number
    friction: number
    repulsionTheta: number
    decay: number
    nodeSize: number
    nodeLabel: boolean
    nodeLabelSize: number
    linkWidth: number
    linkLabel: boolean
    linkLabelSize: number
    linkLabelType: string
}