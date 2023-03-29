import * as React from "react";
import {useState} from "react";
import {StyledTreeItem} from "./StyledTreeItem";
import {Sync} from "@mui/icons-material";
import {Menu, MenuItem} from "@mui/material";
import DatasetIcon from "@mui/icons-material/Dataset";
import {useNavigate} from "react-router-dom";
import {Dataset} from "../../../Services/administration/models/getProject/GetProjectOutputModel";

/**
 * Props for the DatasetTreeItem component.
 *
 * @param nodeId id of the tree item
 * @param dataset dataset of the tree item
 * @param children children of the tree item
 */
interface DatasetTreeItemProps {
    nodeId: string;
    dataset: Dataset;
    children?: React.ReactNode;
}

/**
 * Tree item for a dataset of a project.
 *
 * @param nodeId id of the tree item
 * @param children children of the tree item
 */
export function DatasetTreeItem({nodeId, dataset, children}: DatasetTreeItemProps) {
    const [contextMenu, setContextMenu] = useState<{
        mouseX: number;
        mouseY: number;
    } | null>(null);

    const navigate = useNavigate();

    const handleContextMenu = (event: React.MouseEvent) => {
        event.preventDefault();
        setContextMenu(
            contextMenu === null
                ? {
                    mouseX: event.clientX + 2,
                    mouseY: event.clientY - 6,
                }
                : // repeated contextmenu when it is already open closes it with Chrome 84 on Ubuntu
                  // Other native context menus might behave different.
                  // With this behavior we prevent contextmenu from the backdrop to re-locale existing context menus.
                null,
        );
    };

    const handleClose = () => {
        setContextMenu(null);
    };

    return (
        <>
            <StyledTreeItem
                nodeId={nodeId}
                labelText={"Dataset" + dataset.datasetId} // TODO: Change to dataset name
                labelIcon={DatasetIcon}
                onContextMenu={handleContextMenu}
            >
                {children}
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
                <MenuItem onClick={() => {
                    // TODO
                }}>
                    <Sync color={"primary"}/>
                    Compute
                </MenuItem>
            </Menu>
        </>
    );
}