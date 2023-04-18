import {TreeView} from "../../../../../../Services/administration/models/getProject/GetProjectOutputModel"
import {StyledTreeItem} from "../../../Utils/StyledTreeItem"
import {ScatterPlot} from "@mui/icons-material"
import * as React from "react"
import {useTreeViewTreeItem} from "./useTreeViewTreeItem"

/**
 * Props for the TreeViewTreeItem component.
 *
 * @property nodeId id of the tree view tree item
 * @property datasetId id of the dataset
 * @property treeView tree view
 */
interface TreeViewTreeItemProps {
    nodeId: string
    datasetId: string
    treeView: TreeView
}

/**
 * Tree item for a tree view.
 */
export function TreeViewTreeItem({nodeId, datasetId, treeView}: TreeViewTreeItemProps) {
    const {contextMenuItems} = useTreeViewTreeItem(datasetId, treeView)

    return (
        <StyledTreeItem
            nodeId={nodeId}
            labelText={treeView.name}
            labelIcon={ScatterPlot}
            contextMenuItems={contextMenuItems}
        />
    )
}
