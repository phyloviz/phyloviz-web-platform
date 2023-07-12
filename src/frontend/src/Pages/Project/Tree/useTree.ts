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
import {useState} from "react";

/**
 * Hook for Tree page.
 */
export function useTree() {
    const pathParams = useParams<{ projectId: string, datasetId: string, treeId: string }>()
    const projectId = pathParams.projectId!
    const datasetId = pathParams.datasetId!
    const treeId = pathParams.treeId!

    const {project} = useProjectContext()
    const dataset = project?.datasets.find(dataset => dataset.datasetId === datasetId)

    const tree: Tree = dataset?.trees
        .find(tree => tree.treeId === treeId) as Tree

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

    const [treeData, setTreeData] = useState<string | null>(null);

    // useEffect(() => {
    //     VisualizationService.getTree(projectId, datasetId, treeId)
    //         .then(tree => {
    //             setTreeData(tree)
    //             console.log(tree)
    //         })
    // }, [projectId, treeId])

    return {
        tree: cascadingInfoTree,
        treeData: null
    }
}