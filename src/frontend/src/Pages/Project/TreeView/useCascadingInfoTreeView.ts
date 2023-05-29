import {
    AlgorithmDistanceMatrixTreeSource,
    CascadingInfoAlgorithmDistanceMatrixTreeSource,
    CascadingInfoAlgorithmTypingDataTreeSource,
    CascadingInfoFileTreeSource,
    CascadingInfoTree,
    CascadingInfoTreeView,
    Dataset,
    DistanceMatrix,
    Tree,
    TreeView
} from "../../../Services/Administration/models/projects/getProject/GetProjectOutputModel";

export function useCascadingInfoTreeView(dataset: Dataset | undefined, treeViewId: string) {
    const treeView: TreeView = dataset?.treeViews
        .find(treeView => treeView.treeViewId === treeViewId) as TreeView

    const tree: Tree = dataset?.trees
        .find(tree => tree.treeId === treeView.source.treeId) as Tree

    const distanceMatrix = tree.sourceType === "algorithm_distance_matrix"
        ? dataset?.distanceMatrices
            .find(distanceMatrix =>
                distanceMatrix.distanceMatrixId === (tree.source as AlgorithmDistanceMatrixTreeSource).distanceMatrixId) as DistanceMatrix
        : undefined

    const cascadingInfoTree: CascadingInfoTree = {
        treeId: tree.treeId,
        name: tree.name,
        sourceType: tree.sourceType,
        source:
            tree.sourceType === "algorithm_typing_data"
                ? tree.source as CascadingInfoAlgorithmTypingDataTreeSource
                : tree.sourceType === "algorithm_distance_matrix"
                    ? {
                        algorithm: (tree.source as AlgorithmDistanceMatrixTreeSource).algorithm,
                        distanceMatrix: distanceMatrix,
                        parameters: (tree.source as AlgorithmDistanceMatrixTreeSource).parameters
                    } as CascadingInfoAlgorithmDistanceMatrixTreeSource
                    : tree.source as CascadingInfoFileTreeSource
    }

    const cascadingInfoTreeView: CascadingInfoTreeView = {
        treeViewId: treeView.treeViewId,
        name: treeView.name,
        layout: treeView.layout,
        source: {
            tree: cascadingInfoTree
        }
    } as CascadingInfoTreeView

    return {
        cascadingInfoTreeView
    }
}