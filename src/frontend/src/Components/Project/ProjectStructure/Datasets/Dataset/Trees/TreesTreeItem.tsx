import {Tree} from "../../../../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {StyledTreeItem} from "../../../Utils/StyledTreeItem"
import {TreeTreeItem} from "./TreeTreeItem"
import * as React from "react"
import {TreesIcon} from "../../../../../Shared/Icons";

/**
 * Props for the TreeViewsTreeItem component.
 *
 * @property nodeId id of the tree item
 * @property datasetId id of the dataset
 * @property trees trees of the dataset
 */
interface TreesTreeItemProps {
    nodeId: string
    datasetId: string
    trees: Tree[]
}

/**
 * Tree item for the trees of a dataset.
 */
export function TreesTreeItem({nodeId, datasetId, trees}: TreesTreeItemProps) {
    return (
        <StyledTreeItem
            nodeId={nodeId}
            labelText="Trees"
            labelIcon={TreesIcon}
        >
            {
                trees.map((tree, index) => {
                    return (
                        <TreeTreeItem
                            key={tree.treeId}
                            nodeId={nodeId + index.toString()}
                            datasetId={datasetId}
                            tree={tree}
                        />
                    )
                })
            }
        </StyledTreeItem>
    )
}