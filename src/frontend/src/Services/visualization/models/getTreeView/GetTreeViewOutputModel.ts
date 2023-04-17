export interface GetTreeViewOutputModel {
    nodes: Node[]
    totalCount: number
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