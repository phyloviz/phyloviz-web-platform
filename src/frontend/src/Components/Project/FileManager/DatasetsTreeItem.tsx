import {Dataset} from "../../../Services/administration/models/getProject/GetProjectOutputModel";
import {StyledTreeItem} from "./StyledTreeItem";
import {Add, Inventory2} from "@mui/icons-material";
import {DatasetTreeItem} from "./DatasetTreeItem";
import * as React from "react";
import {useNavigate, useParams} from "react-router-dom";
import {WebUiUris} from "../../../Utils/WebUiUris";
import {Menu, MenuItem} from "@mui/material";
import {useContextMenu} from "./useContextMenu";

/**
 * Props for the DatasetsTreeItem component.
 *
 * @param nodeId id of the tree item
 * @param datasets datasets to display
 */
interface DatasetsTreeItemProps {
    nodeId: string;
    datasets: Dataset[];
}

/**
 * Tree item for the datasets of a project.
 *
 * @param nodeId id of the tree item
 * @param datasets datasets to display
 */
export function DatasetsTreeItem({nodeId, datasets}: DatasetsTreeItemProps) {
    const {projectId} = useParams<{ projectId: string }>();

    const {
        contextMenu,
        handleContextMenu,
        handleClose
    } = useContextMenu(); // TODO: useContextMenu is not working when the tree item is nested in another tree item with the other context menu (they both open at the same time)

    const navigate = useNavigate();
    const handleCreateDataset = () => {
        handleClose();
        navigate(WebUiUris.createDataset(projectId!));
    }

    return (
        <>
            <StyledTreeItem
                nodeId={nodeId}
                labelText="Datasets"
                labelIcon={Inventory2}
                onContextMenu={handleContextMenu}
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
    );
}
