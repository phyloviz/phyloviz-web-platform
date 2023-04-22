import {TreeView} from "../../../../../../Services/Administration/models/getProject/GetProjectOutputModel"
import {StyledTreeItem} from "../../../Utils/StyledTreeItem"
import {ScatterPlot} from "@mui/icons-material"
import * as React from "react"
import {useTreeViewTreeItem} from "./useTreeViewTreeItem"
import {DeleteResourceBackdrop} from "../../../../../Shared/DeleteResourceBackdrop"

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
    const {
        contextMenuItems,
        deleteBackdropOpen,
        handleDeleteBackdropClose,
        handleDelete,
        error,
        clearError
    } = useTreeViewTreeItem(datasetId, treeView)

    return (<>
        <StyledTreeItem
            nodeId={nodeId}
            labelText={treeView.name}
            labelIcon={ScatterPlot}
            contextMenuItems={contextMenuItems}
        />
        <DeleteResourceBackdrop
            open={deleteBackdropOpen}
            title={"Delete Tree View?"}
            subheader={"Are you sure you want to delete this tree view? This action cannot be undone."}
            handleClose={handleDeleteBackdropClose}
            handleDelete={handleDelete}
            error={error}
            clearError={clearError}
        />
    </>)
}
