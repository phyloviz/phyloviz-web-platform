import {DistanceMatrix} from "../../../../../../../Services/administration/models/getProject/GetProjectOutputModel"
import {StyledTreeItem} from "../../../../Utils/StyledTreeItem"
import {TableView} from "@mui/icons-material"
import * as React from "react"
import {useDistanceMatrixTreeItem} from "./useDistanceMatrixTreeItem";

/**
 * Props for the DistanceMatrixTreeItem component.
 *
 * @property nodeId id of the tree item
 * @property datasetId id of the dataset
 * @property distanceMatrix distance matrix
 */
interface DistanceMatrixTreeItemProps {
    nodeId: string
    datasetId: string
    distanceMatrix: DistanceMatrix
}

/**
 * Tree item for a distance matrix.
 */
export function DistanceMatrixTreeItem({nodeId, datasetId, distanceMatrix}: DistanceMatrixTreeItemProps) {
    const {contextMenuItems} = useDistanceMatrixTreeItem(datasetId, distanceMatrix)

    return (
        <StyledTreeItem
            nodeId={nodeId}
            labelText={distanceMatrix.name}
            labelIcon={TableView}
            contextMenuItems={contextMenuItems}
        />
    )
}

