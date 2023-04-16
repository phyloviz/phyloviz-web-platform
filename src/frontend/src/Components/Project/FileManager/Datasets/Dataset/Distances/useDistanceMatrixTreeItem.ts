import {DistanceMatrix} from "../../../../../../Services/administration/models/getProject/GetProjectOutputModel";
import {Download, Visibility} from "@mui/icons-material";
import {useNavigate, useParams} from "react-router-dom";
import {WebUiUris} from "../../../../../../Pages/WebUiUris";

/**
 * Hook for the DistanceMatrixTreeItem component.
 */
export function useDistanceMatrixTreeItem(datasetId: string, distanceMatrix: DistanceMatrix) {
    const navigate = useNavigate()
    const {projectId} = useParams<{ projectId: string }>()

    return {
        contextMenuItems: [
            {
                label: "View",
                icon: Visibility,
                onClick: () => navigate(WebUiUris.distanceMatrix(projectId!, datasetId, distanceMatrix.distanceMatrixId))
            },
            {
                label: "Export",
                icon: Download,
                onClick: () => {/*TODO: To be implemented*/
                }
            }
        ]
    }
}