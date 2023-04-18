import * as React from "react"
import {StyledTreeItem} from "../../Utils/StyledTreeItem"
import DatasetIcon from "@mui/icons-material/Dataset"
import {Dataset} from "../../../../../Services/administration/models/getProject/GetProjectOutputModel"
import {TreesTreeItem} from "./Trees/TreesTreeItem"
import {DistancesTreeItem} from "./Distances/DistancesTreeItem"
import {IsolateDataTreeItem} from "./IsolateData/IsolateDataTreeItem"
import {TypingDataTreeItem} from "./TypingData/TypingDataTreeItem"
import {TreeViewsTreeItem} from "./TreeViews/TreeViewsTreeItem"
import {useDatasetTreeItem} from "./useDatasetTreeItem"

/**
 * Props for the DatasetTreeItem component.
 *
 * @property nodeId id of the tree item
 * @property dataset dataset of the tree item
 * @property children children of the tree item
 */
interface DatasetTreeItemProps {
    nodeId: string
    dataset: Dataset
}


/**
 * Tree item for a dataset of a project.
 */
export function DatasetTreeItem({nodeId, dataset}: DatasetTreeItemProps) {
    const {contextMenuItems} = useDatasetTreeItem(dataset)

    return (
        <StyledTreeItem
            nodeId={nodeId}
            labelText={dataset.name}
            labelIcon={DatasetIcon}
            contextMenuItems={contextMenuItems}
        >
            <TypingDataTreeItem
                nodeId={nodeId + "typingData"}
                typingDataId={dataset.typingDataId}
            />

            {dataset.isolateDataId &&
                <IsolateDataTreeItem
                    nodeId={nodeId + "isolateData"}
                    isolateDataId={dataset.isolateDataId}
                />
            }

            <DistancesTreeItem
                nodeId={nodeId + "distances"}
                datasetId={dataset.datasetId}
                distanceMatrices={dataset.distanceMatrices}
            />

            <TreesTreeItem
                nodeId={nodeId + "trees"}
                datasetId={dataset.datasetId}
                trees={dataset.trees}
            />

            <TreeViewsTreeItem
                nodeId={nodeId + "treeViews"}
                datasetId={dataset.datasetId}
                treeViews={dataset.treeViews}
            />
        </StyledTreeItem>
    )
}

