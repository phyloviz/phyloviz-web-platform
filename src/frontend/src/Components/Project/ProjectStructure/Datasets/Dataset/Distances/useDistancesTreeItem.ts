import {useNavigate, useParams} from "react-router-dom"
import {DistanceMatricesIcon, DistanceMatrixIcon} from "../../../../../Shared/Icons";
import {WebUiUris} from "../../../../../../Pages/WebUiUris";

export const computeDistanceMatrixOptions = (projectId: string, datasetId: string) => [
    {
        label: "Hamming Distance",
        url: WebUiUris.computeHammingDistance(projectId, datasetId)
    }
]

/**
 * Hook for the DistancesTreeItem component.
 */
export function useDistancesTreeItem(datasetId: string) {
    const {projectId} = useParams<{ projectId: string }>()
    const navigate = useNavigate()

    return {
        contextMenuItems: [
            {
                label: "Compute Distances",
                icon: DistanceMatricesIcon,
                nestedItems: computeDistanceMatrixOptions(projectId!, datasetId).map((option) => {
                    return {
                        label: option.label,
                        icon: DistanceMatrixIcon,
                        onClick: () => navigate(option.url)
                    }
                })
            },
        ]
    }
}
