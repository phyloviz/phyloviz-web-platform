import {Tree} from "../../../../../../../Services/administration/models/getProject/GetProjectOutputModel"
import {StyledTreeItem} from "../../../../Utils/StyledTreeItem"
import {Forest} from "@mui/icons-material"
import * as React from "react"
import {useTreeTreeItem} from "./useTreeTreeItem";

/**
 * Props for the TreeViewTreeItem component.
 *
 * @property nodeId id of the tree item
 * @property datasetId id of the dataset
 * @property tree tree
 */
interface TreeTreeItemProps {
    nodeId: string
    datasetId: string
    tree: Tree
}

/**
 * Tree item for a tree.
 */
export function TreeTreeItem({nodeId, datasetId, tree}: TreeTreeItemProps) {
    const {contextMenuItems} = useTreeTreeItem(datasetId, tree)

    return (
        <StyledTreeItem
            nodeId={nodeId}
            labelText={tree.name}
            labelIcon={Forest}
            contextMenuItems={contextMenuItems}
        />
    )
}

