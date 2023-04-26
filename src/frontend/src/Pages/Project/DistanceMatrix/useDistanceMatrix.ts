import {DistanceMatrix} from "../../../Services/Administration/models/projects/getProject/GetProjectOutputModel";
import {useParams} from "react-router-dom";
import {useProjectContext} from "../useProject";

/**
 * Hook for DistanceMatrix page.
 */
export function useDistanceMatrix() {
    const {projectId, datasetId, distanceMatrixId} = useParams<{
        projectId: string,
        datasetId: string,
        distanceMatrixId: string
    }>()
    const {project} = useProjectContext()

    const distanceMatrix = project?.datasets
        .find(dataset => dataset.datasetId === datasetId)?.distanceMatrices
        .find(distanceMatrix => distanceMatrix.distanceMatrixId === distanceMatrixId) as DistanceMatrix

    return {
        distanceMatrix: distanceMatrix,
        data: [
            [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15],
            [3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16],
            [4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17],
            [5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18],
            [6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19],
            [7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20],
            [8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21],
            [9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22],
            [10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23],
            [11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24],
            [12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25],
            [13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26],
            [14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27],
            [15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28]
        ],
        cellStyle: (background: any, value: number, min: number, max: number, data: any, x: any, y: any) => ({
            background: `rgb(0, 151, 230, ${1 - (max - value) / (max - min)})`,
            fontSize: "11.5px",
            color: "#444"
        })
    }
}