import {Tree} from "../../../../../Services/administration/models/getProject/GetProjectOutputModel"
import {useContextMenu} from "../../useContextMenu"
import {StyledTreeItem} from "../../StyledTreeItem"
import {ScatterPlot, Visibility} from "@mui/icons-material"
import {Menu, MenuItem} from "@mui/material"
import * as React from "react"
import {useNavigate, useParams} from "react-router-dom"
import {WebUiUris} from "../../../../../Utils/WebUiUris"

/**
 * Props for the TreeTreeItem component.
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
            labelText="Tree"
            labelIcon={ScatterPlot}
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
                <Visibility color={"primary"}/>
                View
            </MenuItem>
        </Menu>
    </>)
}