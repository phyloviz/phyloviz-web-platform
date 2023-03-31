export interface Source {

}

export interface FileSource extends Source {
    fileType: string;
    fileName: string;
}

export interface TreeViewSource extends Source {
    treeId: string;
    typingDataId: string | null;
    isolateDataId: string | null;
}

export interface AlgorithmTypingDataSource extends Source {
    algorithm: string;
    parameters: string;
}

export interface AlgorithmDistanceMatrixSource extends Source {
    algorithm: string;
    distanceMatrixId: string;
    parameters: string;
}

export interface FunctionDistanceMatrixSource extends Source {
    function: string;
}

export interface TreeView {
    treeViewId: string;
    name: string;
    layout: string;
    source: TreeViewSource;
}

export interface Tree {
    treeId: string;
    name: string;
    sourceType: string;
    source: Source;
}

export interface DistanceMatrix {
    distanceMatrixId: string;
    name: string;
    sourceType: string;
    source: Source;
}

export interface Dataset {
    datasetId: string;
    typingDataId: string;
    isolateDataId: string;
    distanceMatrices: DistanceMatrix[];
    trees: Tree[];
    treeViews: TreeView[];
}

interface TypingDataFile {
    typingDataId: string;
    name: string;
}

interface IsolateDataFile {
    isolateDataId: string;
    name: string;
}

export interface ProjectFiles {
    typingData: TypingDataFile[];
    isolateData: IsolateDataFile[];
}

export interface GetProjectOutputModel {
    projectId: string;
    name: string;
    description: string;
    owner: string;
    datasets: Dataset[];
    files: ProjectFiles
}