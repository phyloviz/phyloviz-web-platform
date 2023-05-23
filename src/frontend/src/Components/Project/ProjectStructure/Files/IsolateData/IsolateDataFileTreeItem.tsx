import {IsolateDataFile} from "../../../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {StyledTreeItem} from "../../Utils/StyledTreeItem"
import * as React from "react"
import {useIsolateDataFileTreeItem} from "./useIsolateDataFileTreeItem"
import {DeleteResourceBackdrop} from "../../../../Shared/DeleteResourceBackdrop"
import {IsolateDataIcon} from "../../../../Shared/Icons";

/**
 * Props for the IsolateDataFileTreeItem component.
 *
 * @property nodeId id of the tree item
 * @property file file to display
 */
interface IsolateDataFileTreeItemProps {
    nodeId: string
    file: IsolateDataFile
}

/**
 * Tree item for an isolate data file.
 */
export function IsolateDataFileTreeItem({nodeId, file}: IsolateDataFileTreeItemProps) {
    const {
        contextMenuItems,
        deleteBackdropOpen,
        handleDeleteBackdropClose,
        handleDelete,
        error,
        clearError
    } = useIsolateDataFileTreeItem(file)

    return (<>
        <StyledTreeItem
            nodeId={nodeId}
            labelText={file.name}
            labelIcon={IsolateDataIcon}
            contextMenuItems={contextMenuItems}
        />
        <DeleteResourceBackdrop
            open={deleteBackdropOpen}
            title={`Delete Isolate Data File with name ${file.name}?`}
            subheader={"Are you sure you want to delete this isolate data file? This action cannot be undone."}
            handleClose={handleDeleteBackdropClose}
            handleDelete={handleDelete}
            error={error}
            clearError={clearError}
        />
    </>)
}