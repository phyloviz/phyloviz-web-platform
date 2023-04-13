import {useContextMenu} from "../../useContextMenu"
import {StyledTreeItem} from "../../StyledTreeItem"
import {Description, Download, Visibility} from "@mui/icons-material"
import {Menu, MenuItem} from "@mui/material"
import * as React from "react"
import {useNavigate, useParams} from "react-router-dom"
import {WebUiUris} from "../../../../../Utils/WebUiUris"
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";

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
    const {
        contextMenu,
        handleContextMenu,
        handleClose
    } = useContextMenu()


    const navigate = useNavigate()
    const {projectId} = useParams<{ projectId: string }>()

    const handleViewTypingData = () => {
        handleClose()
        navigate(WebUiUris.typingData(projectId!, typingDataId))
    }

    return (<>
        <StyledTreeItem
            nodeId={nodeId}
            labelText="Typing Data"
            labelIcon={Description}
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
            <MenuItem onClick={handleViewTypingData}>
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