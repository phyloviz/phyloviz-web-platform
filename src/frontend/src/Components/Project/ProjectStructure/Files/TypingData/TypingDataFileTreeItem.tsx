import {TypingDataFile} from "../../../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {StyledTreeItem} from "../../Utils/StyledTreeItem"
import * as React from "react"
import {useTypingDataFileTreeItem} from "./useTypingDataFileTreeItem"
import {DeleteResourceBackdrop} from "../../../../Shared/DeleteResourceBackdrop"
import {TypingDataIcon} from "../../../../Shared/Icons";

/**
 * Props for the TypingDataFileTreeItem component.
 *
 * @property nodeId id of the tree item
 * @property file file to display
 */
interface TypingDataFileTreeItemProps {
    nodeId: string
    file: TypingDataFile
}

/**
 * Tree item for a typing data file.
 */
export function TypingDataFileTreeItem({nodeId, file}: TypingDataFileTreeItemProps) {
    const {
        contextMenuItems,
        deleteBackdropOpen,
        handleDeleteBackdropClose,
        handleDelete,
        error,
        clearError
    } = useTypingDataFileTreeItem(file)

    return (<>
        <StyledTreeItem
            nodeId={nodeId}
            labelText={file.name}
            labelIcon={TypingDataIcon}
            contextMenuItems={contextMenuItems}
        />
        <DeleteResourceBackdrop
            open={deleteBackdropOpen}
            title={`Delete Typing Data File with name '${file.name}'?`}
            subheader={"Are you sure you want to delete this typing data file? This action cannot be undone."}
            handleClose={handleDeleteBackdropClose}
            handleDelete={handleDelete}
            error={error}
            clearError={clearError}
        />
    </>)
}