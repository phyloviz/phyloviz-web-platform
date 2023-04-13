import {useContextMenu} from "../../useContextMenu"
import {StyledTreeItem} from "../../StyledTreeItem"
import {Download, FilePresent, Visibility} from "@mui/icons-material"
import {Menu, MenuItem} from "@mui/material"
import * as React from "react"
import {useNavigate, useParams} from "react-router-dom"
import {WebUiUris} from "../../../../../Utils/WebUiUris"
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";

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
    const {
        contextMenu,
        handleContextMenu,
        handleClose
    } = useContextMenu()

    const navigate = useNavigate()
    const {projectId} = useParams<{ projectId: string }>()

    const handleViewIsolateData = () => {
        handleClose()
        navigate(WebUiUris.isolateData(projectId!, isolateDataId))
    }

    return (<>
        <StyledTreeItem
            nodeId={nodeId}
            labelText="Isolate Data"
            labelIcon={FilePresent}
            handleContextMenu={handleContextMenu}
        />
        <Menu
            open={contextMenu !== null}
            onClose={handleClose}
            anchorReference="anchorPosition"
            anchorPosition={
                contextMenu !== null
                    ? {top: contextMenu.mouseY, left: contextMenu.mouseX}
                    : undefined
            }
        >
            <MenuItem onClick={handleViewIsolateData}>
                <ListItemIcon><Visibility color={"primary"}/></ListItemIcon>
                <ListItemText>View</ListItemText>
            </MenuItem>
            <MenuItem onClick={() => {/*TODO: To be implemented*/
            }}>
                <ListItemIcon><Download color={"primary"}/></ListItemIcon>
                <ListItemText>Export</ListItemText>
            </MenuItem>
        </Menu>
    </>)
}