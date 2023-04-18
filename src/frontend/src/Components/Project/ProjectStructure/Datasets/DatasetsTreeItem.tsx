import {Dataset} from "../../../../Services/administration/models/getProject/GetProjectOutputModel"
import {StyledTreeItem} from "../Utils/StyledTreeItem"
import {Inventory2} from "@mui/icons-material"
import {DatasetTreeItem} from "./Dataset/DatasetTreeItem"
import * as React from "react"
import {useDatasetsTreeItem} from "./useDatasetsTreeItem"

/**
 * Props for the DatasetsTreeItem component.
 *
 * @property nodeId id of the tree item
 * @property datasets datasets to display
 */
interface DatasetsTreeItemProps {
    nodeId: string
    datasets: Dataset[]
}

/**
 * Tree item for the datasets of a project.
 */
export function DatasetsTreeItem({nodeId, datasets}: DatasetsTreeItemProps) {
    const {contextMenuItems} = useDatasetsTreeItem()

    return (
        <StyledTreeItem
            nodeId={nodeId}
            labelText="Datasets"
            labelIcon={Inventory2}
            contextMenuItems={contextMenuItems}
        >
            {
                datasets.map((dataset, index) => {
                    return <DatasetTreeItem
                        key={"dataset" + index.toString()}
                        nodeId={"dataset" + index.toString()}
                        dataset={dataset}
                    />
                })
            }
        </StyledTreeItem>
    )
}
