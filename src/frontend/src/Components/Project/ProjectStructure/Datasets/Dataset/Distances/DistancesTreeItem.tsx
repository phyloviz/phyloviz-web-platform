import {StyledTreeItem} from "../../../Utils/StyledTreeItem"
import {DistanceMatrixTreeItem} from "./DistanceMatrixTreeItem"
import * as React from "react"
import {
    DistanceMatrix
} from "../../../../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {DistanceMatricesIcon} from "../../../../../Shared/Icons";
import {useDistancesTreeItem} from "./useDistancesTreeItem";


/**
 * Props for the DistancesTreeItem component.
 *
 * @property nodeId id of the tree item
 * @property datasetId id of the dataset
 * @property distanceMatrices distance matrices of the dataset
 */
interface DistancesTreeItemProps {
    nodeId: string
    datasetId: string
    distanceMatrices: DistanceMatrix[]
}

/**
 * Tree item for the distance matrices of a dataset.
 */
export function DistancesTreeItem({nodeId, datasetId, distanceMatrices}: DistancesTreeItemProps) {

    const {contextMenuItems} = useDistancesTreeItem(datasetId)

    return (
        <StyledTreeItem
            nodeId={nodeId}
            labelText="Distances"
            labelIcon={DistanceMatricesIcon}
            contextMenuItems={contextMenuItems}
        >
            {
                distanceMatrices.map((distance, index) => {
                    return (
                        <DistanceMatrixTreeItem
                            key={distance.distanceMatrixId}
                            nodeId={nodeId + index.toString()}
                            datasetId={datasetId}
                            distanceMatrix={distance}
                        />
                    )
                })
            }
        </StyledTreeItem>
    )
}