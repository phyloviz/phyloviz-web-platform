import {Tree} from "../../../../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {StyledTreeItem} from "../../../Utils/StyledTreeItem"
import * as React from "react"
import {useTreeTreeItem} from "./useTreeTreeItem"
import {DeleteResourceBackdrop} from "../../../../../Shared/DeleteResourceBackdrop"
import {TreeIcon} from "../../../../../Shared/Icons";

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
    const {
        contextMenuItems,
        deleteBackdropOpen,
        handleDeleteBackdropClose,
        handleDelete,
        error,
        clearError
    } = useTreeTreeItem(datasetId, tree)

    return (<>
        <StyledTreeItem
            nodeId={nodeId}
            labelText={tree.name}
            labelIcon={TreeIcon}
            contextMenuItems={contextMenuItems}
        />
        <DeleteResourceBackdrop
            open={deleteBackdropOpen}
            title={"Delete Tree?"}
            subheader={"Are you sure you want to delete this tree? This action cannot be undone."}
            handleClose={handleDeleteBackdropClose}
            handleDelete={handleDelete}
            error={error}
            clearError={clearError}
        />
    </>)
}

