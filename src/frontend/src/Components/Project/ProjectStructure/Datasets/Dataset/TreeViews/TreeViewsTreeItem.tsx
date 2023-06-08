import {TreeView} from "../../../../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {StyledTreeItem} from "../../../Utils/StyledTreeItem"
import {TreeViewTreeItem} from "./TreeViewTreeItem"
import * as React from "react"
import {TreeViewsIcon} from "../../../../../Shared/Icons";
import {useTreeViewsTreeItem} from "./useTreeViewsTreeItem";

/**
 * Props for the TreeViewsTreeItem component.
 *
 * @property nodeId id of the tree view item
 * @property datasetId id of the dataset
 * @property treeViews tree views of the dataset
 */
interface TreeViewsTreeItemProps {
    nodeId: string
    datasetId: string
    treeViews: TreeView[]
}

/**
 * Tree item for the tree views of a dataset.
 */
export function TreeViewsTreeItem({nodeId, datasetId, treeViews}: TreeViewsTreeItemProps) {
    const {contextMenuItems} = useTreeViewsTreeItem(datasetId)

    return (
        <StyledTreeItem
            nodeId={nodeId}
            labelText="Tree Views"
            labelIcon={TreeViewsIcon}
            contextMenuItems={contextMenuItems}
        >
            {
                treeViews.map((treeView, index) => {
                    return (
                        <TreeViewTreeItem
                            key={treeView.treeViewId}
                            nodeId={nodeId + index.toString()}
                            datasetId={datasetId}
                            treeView={treeView}
                        />
                    )
                })
            }
        </StyledTreeItem>
    )
}