import {Dataset} from "../../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {StyledTreeItem} from "../Utils/StyledTreeItem"
import {DatasetTreeItem} from "./Dataset/DatasetTreeItem"
import * as React from "react"
import {useDatasetsTreeItem} from "./useDatasetsTreeItem"
import {DatasetsIcon} from "../../../Shared/Icons";

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
            labelIcon={DatasetsIcon}
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
