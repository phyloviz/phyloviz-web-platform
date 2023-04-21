import {Tree} from "../../../../../../Services/administration/models/getProject/GetProjectOutputModel"
import {StyledTreeItem} from "../../../Utils/StyledTreeItem"
import {Forest} from "@mui/icons-material"
import {TreeTreeItem} from "./TreeTreeItem"
import * as React from "react"

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
        <StyledTreeItem nodeId={nodeId} labelText="Trees" labelIcon={Forest}>
            {
                trees.map((tree, index) => {
                    return (
                        <TreeTreeItem
                            key={index}
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