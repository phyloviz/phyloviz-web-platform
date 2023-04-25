import {StyledTreeItem} from "../../../Utils/StyledTreeItem"
import * as React from "react"
import {useIsolateDataTreeItem} from "./useIsolateDataTreeItem"
import {IsolateDataIcon} from "../../../../../Shared/Icons";

/**
 * Props for the IsolateDataTreeItem component.
 *
 * @property nodeId id of the tree item
 * @property isolateDataId id of the isolate data
 */
interface IsolateDataTreeItemProps {
    nodeId: string
    isolateDataId: string
}

/**
 * Tree item for the isolate data of a dataset.
 */
export function IsolateDataTreeItem({nodeId, isolateDataId}: IsolateDataTreeItemProps) {
    const {contextMenuItems} = useIsolateDataTreeItem(isolateDataId)

    return (
        <StyledTreeItem
            nodeId={nodeId}
            labelText="Isolate Data"
            labelIcon={IsolateDataIcon}
            contextMenuItems={contextMenuItems}
        />
    )
}

