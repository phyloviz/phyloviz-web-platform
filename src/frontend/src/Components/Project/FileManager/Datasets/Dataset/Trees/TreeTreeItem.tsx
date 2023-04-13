import {Tree} from "../../../../../../Services/administration/models/getProject/GetProjectOutputModel"
import {useContextMenu} from "../../../useContextMenu"
import {StyledTreeItem} from "../../../StyledTreeItem"
import {Download, Forest, ScatterPlot, Visibility} from "@mui/icons-material"
import {Menu, MenuItem} from "@mui/material"
import * as React from "react"
import {useNavigate, useParams} from "react-router-dom"
import {WebUiUris} from "../../../../../../Utils/WebUiUris"
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";

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
        contextMenu,
        handleContextMenu,
        handleClose
    } = useContextMenu()

    const navigate = useNavigate()
    const {projectId} = useParams<{ projectId: string }>()

    const handleViewTree = () => {
        handleClose()
        navigate(WebUiUris.tree(projectId!, datasetId, tree.treeId))
    }

    return (<>
        <StyledTreeItem
            nodeId={nodeId}
            labelText={tree.name}
            labelIcon={Forest}
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
            <MenuItem onClick={handleViewTree}>
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