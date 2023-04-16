import {Tree, TreeView} from "../../../../../../Services/administration/models/getProject/GetProjectOutputModel"
import {StyledTreeItem} from "../../../StyledTreeItem"
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
            <TreeViewTreeItem nodeId={"102"} datasetId={datasetId} treeView={{
                treeViewId: "test",
                name: "Radial Tree View",
                layout: "radial",
                source: {
                    treeId: "abc",
                    typingDataId: "test",
                    isolateDataId: "test"
                }
            }}/>
            {
                treeViews.map((treeView, index) => {
                    return (
                        <TreeViewTreeItem nodeId={index.toString()} datasetId={datasetId} treeView={treeView}/>
                    )
                })
            }
        </StyledTreeItem>
    )
}