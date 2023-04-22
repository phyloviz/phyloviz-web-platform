import {TreeView} from "../../../../../../Services/administration/models/getProject/GetProjectOutputModel"
import {StyledTreeItem} from "../../../Utils/StyledTreeItem"
import {ScatterPlot} from "@mui/icons-material"
import {TreeViewTreeItem} from "./TreeViewTreeItem"
import * as React from "react"

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
    return (
        <StyledTreeItem nodeId={nodeId} labelText="Tree Views" labelIcon={ScatterPlot}>
            {
                treeViews.map((treeView, index) => {
                    return (
                        <TreeViewTreeItem
                            key={index}
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