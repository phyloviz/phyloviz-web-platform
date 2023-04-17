import {StyledTreeItem} from "../../../../Utils/StyledTreeItem"
import {Description} from "@mui/icons-material"
import * as React from "react"
import {useTypingDataTreeItem} from "./useTypingDataTreeItem";

/**
 * Props for the TypingDataTreeItem component.
 *
 * @property nodeId id of the tree item
 * @property typingDataId id of the typing data
 */
interface TypingDataTreeItemProps {
    nodeId: string
    typingDataId: string
}

/**
 * Tree item for the typing data of a dataset.
 */
export function TypingDataTreeItem({nodeId, typingDataId}: TypingDataTreeItemProps) {
    const {contextMenuItems} = useTypingDataTreeItem(typingDataId)

    return (
        <StyledTreeItem
            nodeId={nodeId}
            labelText="Typing Data"
            labelIcon={Description}
            contextMenuItems={contextMenuItems}
        />
    )
}

