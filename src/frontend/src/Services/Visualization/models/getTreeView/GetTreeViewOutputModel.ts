export interface GetTreeViewOutputModel {
    nodes: Node[]
    edges: Edge[]
}

export interface Edge {
    from: string
    to: string
    weight: number
}

export interface Node {
    st: string
    coordinates: number[]
    profile: string[]
    auxiliaryData: AuxiliaryData
}

export interface AuxiliaryData {
    [key: string]: string
}