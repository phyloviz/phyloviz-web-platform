import {Dataset} from "../../../../Services/administration/models/getProject/GetProjectOutputModel"
import {StyledTreeItem} from "../StyledTreeItem"
import {Add, Inventory2} from "@mui/icons-material"
import {DatasetTreeItem} from "./Dataset/DatasetTreeItem"
import * as React from "react"
import {useNavigate, useParams} from "react-router-dom"
import {WebUiUris} from "../../../../Utils/WebUiUris"
import {Menu, MenuItem} from "@mui/material"
import {useContextMenu} from "../useContextMenu"

/**
 * Props for the DatasetsTreeItem component.
 *
 * @property nodeId id of the tree item
 * @property datasets datasets to display
 */
interface DatasetsTreeItemProps {
    nodeId: string
    datasets: Dataset[]
}

/**
 * Tree item for the datasets of a project.
 */
export function DatasetsTreeItem({nodeId, datasets}: DatasetsTreeItemProps) {
    const {projectId} = useParams<{ projectId: string }>()

    const {
        contextMenu,
        handleContextMenu,
        handleClose
    } = useContextMenu()

    const navigate = useNavigate()
    const handleCreateDataset = () => {
        handleClose()
        navigate(WebUiUris.createDataset(projectId!))
    }

    return (
        <>
            <StyledTreeItem
                nodeId={nodeId}
                labelText="Datasets"
                labelIcon={Inventory2}
                handleContextMenu={handleContextMenu}
            >
                {
                    datasets.map((dataset, index) => {
                        return <DatasetTreeItem key={"5" + index.toString()} nodeId={"5" + index.toString()}
                                                dataset={dataset}/>
                    })
                }
            </StyledTreeItem>
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
                <MenuItem onClick={handleCreateDataset}>
                    <Add color={"primary"}/>
                    Create Dataset
                </MenuItem>
            </Menu>
        </>
    )
}
