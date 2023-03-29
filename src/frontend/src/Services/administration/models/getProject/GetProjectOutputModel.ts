export interface TreeView {
    treeViewId: string;
    name: string;
    layout: string;
}

export interface Tree {
    treeId: string;
    name: string;
    algorithm: string;
    treeViews: TreeView[];
}

export interface DistanceMatrix {
    distanceMatrixId: string;
    name: string;
    algorithm: string;
    trees: Tree[]
}

export interface Dataset {
    datasetId: string;
    typingDataId: string;
    isolateDataId: string;
    distanceMatrices: DistanceMatrix[];
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