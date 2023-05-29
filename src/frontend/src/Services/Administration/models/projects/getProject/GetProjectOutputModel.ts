export interface TreeViewSource {
    treeId: string
}

export type TreeSourceType = "algorithm_typing_data" | "algorithm_distance_matrix" | "file"

export interface TreeSource {

}

export type DistanceMatrixSourceType = "function"

export interface DistanceMatrixSource {

}

export interface AlgorithmTypingDataTreeSource extends TreeSource {
    algorithm: string
    parameters: string
}

export interface AlgorithmDistanceMatrixTreeSource extends TreeSource {
    algorithm: string
    distanceMatrixId: string
    parameters: string
}

export interface FileTreeSource extends TreeSource {
    fileType: string
    fileName: string
}

export interface FunctionDistanceMatrixSource extends DistanceMatrixSource {
    function: string
}

export interface TreeView {
    treeViewId: string
    name: string
    layout: string
    source: TreeViewSource
}

export interface Tree {
    treeId: string
    name: string
    sourceType: TreeSourceType
    source: TreeSource
}

export interface DistanceMatrix {
    distanceMatrixId: string
    name: string
    sourceType: DistanceMatrixSourceType
    source: DistanceMatrixSource
}

export interface Dataset {
    datasetId: string
    name: string
    description: string
    typingDataId: string
    isolateDataId: string | null
    isolateDataKey: string | null
    distanceMatrices: DistanceMatrix[]
    trees: Tree[]
    treeViews: TreeView[]
}

export interface TypingDataFile {
    typingDataId: string
    name: string
}

export interface IsolateDataFile {
    isolateDataId: string
    name: string
    keys: string[]
}

export interface ProjectFiles {
    typingData: TypingDataFile[]
    isolateData: IsolateDataFile[]
}

export interface CascadingInfoSource {

}

export interface CascadingInfoTreeSource {

}

export interface CascadingInfoTreeView {
    treeViewId: string
    name: string
    layout: string
    source: CascadingInfoTreeViewSource
}

export interface CascadingInfoTreeViewSource extends CascadingInfoSource {
    tree: CascadingInfoTree
}

export interface CascadingInfoTree {
    treeId: string
    name: string
    sourceType: TreeSourceType
    source: CascadingInfoTreeSource
}

export interface CascadingInfoAlgorithmTypingDataTreeSource extends CascadingInfoTreeSource {
    algorithm: string
    parameters: string
}

export interface CascadingInfoAlgorithmDistanceMatrixTreeSource extends CascadingInfoTreeSource {
    algorithm: string
    distanceMatrix: DistanceMatrix
    parameters: string
}

export interface CascadingInfoFileTreeSource extends CascadingInfoTreeSource {
    fileType: string
    fileName: string
}

export interface GetProjectOutputModel {
    projectId: string
    name: string
    description: string
    owner: string
    datasets: Dataset[]
    files: ProjectFiles
}

export type Project = GetProjectOutputModel