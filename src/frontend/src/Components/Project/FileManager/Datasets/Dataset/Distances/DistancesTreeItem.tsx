import {StyledTreeItem} from "../../../StyledTreeItem"
import {TableView} from "@mui/icons-material"
import {DistanceMatrixTreeItem} from "./DistanceMatrixTreeItem"
import * as React from "react"
import {DistanceMatrix} from "../../../../../../Services/administration/models/getProject/GetProjectOutputModel"


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
    return (
        <StyledTreeItem nodeId={nodeId} labelText="Distances" labelIcon={TableView}>
            <DistanceMatrixTreeItem
                nodeId={"100"}
                datasetId={datasetId}
                distanceMatrix={{
                    distanceMatrixId: "test",
                    name: "Hamming Distance",
                    sourceType: "test",
                    source: {
                        function: "hamming"
                    }
                }}/>
            {
                // TODO: Remove the distance matrix above. Just for testing.
                distanceMatrices.map((distance, index) => {
                    return (
                        <DistanceMatrixTreeItem
                            nodeId={index.toString()}
                            datasetId={datasetId}
                            distanceMatrix={distance}
                        />
                    )
                })
            }
        </StyledTreeItem>
    )
}