import {DistanceMatrix} from "../../../../../../Services/administration/models/getProject/GetProjectOutputModel"
import {useContextMenu} from "../../../useContextMenu"
import {StyledTreeItem} from "../../../StyledTreeItem"
import {Download, TableView, Visibility} from "@mui/icons-material"
import {Menu, MenuItem} from "@mui/material"
import * as React from "react"
import {useNavigate, useParams} from "react-router-dom"
import {WebUiUris} from "../../../../../../Utils/WebUiUris"
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";

/**
 * Props for the DistanceMatrixTreeItem component.
 *
 * @property nodeId id of the tree item
 * @property datasetId id of the dataset
 * @property distanceMatrix distance matrix
 */
interface DistanceMatrixTreeItemProps {
    nodeId: string
    datasetId: string
    distanceMatrix: DistanceMatrix
}

/**
 * Tree item for a distance matrix.
 */
export function DistanceMatrixTreeItem({nodeId, datasetId, distanceMatrix}: DistanceMatrixTreeItemProps) {
    const {
        contextMenu,
        handleContextMenu,
        handleClose
    } = useContextMenu()

    const navigate = useNavigate()
    const {projectId} = useParams<{ projectId: string }>()

    const handleViewDistanceMatrix = () => {
        handleClose()
        navigate(WebUiUris.distanceMatrix(projectId!, datasetId, distanceMatrix.distanceMatrixId))
    }

    return (<>
        <StyledTreeItem
            nodeId={nodeId}
            labelText={distanceMatrix.name}
            labelIcon={TableView}
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
            <MenuItem onClick={handleViewDistanceMatrix}>
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