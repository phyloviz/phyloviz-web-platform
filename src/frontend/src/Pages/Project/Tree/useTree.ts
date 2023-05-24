import {useParams} from "react-router-dom";
import {useProjectContext} from "../useProject";
import {
    AlgorithmDistanceMatrixTreeSource,
    CascadingInfoAlgorithmDistanceMatrixTreeSource,
    CascadingInfoAlgorithmTypingDataTreeSource,
    CascadingInfoFileTreeSource,
    CascadingInfoTree,
    DistanceMatrix,
    Tree
} from "../../../Services/Administration/models/projects/getProject/GetProjectOutputModel";

/**
 * Hook for Tree page.
 */
export function useTree() {
    const {projectId, datasetId, treeId} = useParams<{ projectId: string, datasetId: string, treeId: string }>()
    const {project} = useProjectContext()

    const tree: Tree = project?.datasets
        .find(dataset => dataset.datasetId === datasetId)?.trees
        .find(tree => tree.treeId === treeId) as Tree

    const distanceMatrix = tree.sourceType === "algorithm_distance_matrix" ? project?.datasets
            .find(dataset => dataset.datasetId === datasetId)?.distanceMatrices
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

    return {
        tree: cascadingInfoTree
    }
}