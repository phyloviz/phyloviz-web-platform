import {Tree} from "../../../../../../../Services/administration/models/getProject/GetProjectOutputModel"
import {StyledTreeItem} from "../../../../Utils/StyledTreeItem"
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
            <TreeTreeItem nodeId={"102"} datasetId={datasetId} tree={{
                treeId: "test",
                name: "goeBURST",
                sourceType: "algorithmDistanceMatrix",
                source: {
                    algorithm: "goeBURST",
                    distanceMatrixId: "test",
                    parameters: "test"
                }
            }}/>
            <TreeTreeItem nodeId={"103"} datasetId={datasetId} tree={{
                treeId: "test",
                name: "goeBURST (Dynamical)",
                sourceType: "algorithmTypingData",
                source: {
                    algorithm: "goeBURST",
                    parameters: "test"
                }
            }}/>
            <TreeTreeItem nodeId={"104"} datasetId={datasetId} tree={{
                treeId: "test",
                name: "Newick",
                sourceType: "file",
                source: {
                    algorithm: "newick",
                    parameters: "newick002.newick"
                }
            }}/>
            {
                trees.map((tree, index) => {
                    return (
                        <TreeTreeItem nodeId={index.toString()} datasetId={datasetId} tree={tree}/>
                    )
                })
            }
        </StyledTreeItem>
    )
}