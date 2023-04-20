export interface GetTreeViewOutputModel {
    nodes: Node[];
    edges: Edge[];
}

export interface Node {
    st: string;
    coordinates: number[];
    profile: string[];
    auxiliaryData: AuxiliaryData;
}

export interface Edge {
    from: string;
    to: string;
}

export interface AuxiliaryData {
    [key: string]: string;
}