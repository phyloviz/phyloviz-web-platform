import * as React from "react";
import {StyledTreeItem} from "./StyledTreeItem";
import {FilePresent} from "@mui/icons-material";
import {Menu, MenuItem} from "@mui/material";
import UploadIcon from "@mui/icons-material/Upload";
import {useNavigate} from "react-router-dom";
import {WebUiUris} from "../../../Utils/navigation/WebUiUris";

/**
 * Props for the FilesTreeItem component.
 *
 * @param nodeId id of the tree item
 * @param children children of the tree item
 */
interface FilesTreeItemProps {
    nodeId: string;
    children?: React.ReactNode;
}

/**
 * Tree item for the files of a project.
 *
 * @param nodeId id of the tree item
 * @param children children of the tree item
 */
export function FilesTreeItem({nodeId, children}: FilesTreeItemProps) {
    const [contextMenu, setContextMenu] = React.useState<{
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
                labelText="Files"
                labelIcon={FilePresent}
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
                    handleClose();
                    navigate(WebUiUris.UPLOAD_FILES);
                }}>
                    <UploadIcon color={"primary"}/>
                    Upload Files
                </MenuItem>
            </Menu>
        </>
    );
}