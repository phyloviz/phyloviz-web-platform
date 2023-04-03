import * as React from "react";
import {StyledTreeItem} from "./StyledTreeItem";
import {Description, FilePresent, Folder} from "@mui/icons-material";
import {Menu, MenuItem} from "@mui/material";
import UploadIcon from "@mui/icons-material/Upload";
import {useNavigate, useParams} from "react-router-dom";
import {WebUiUris} from "../../../Utils/WebUiUris";
import {ProjectFiles} from "../../../Services/administration/models/getProject/GetProjectOutputModel";
import {useContextMenu} from "./useContextMenu";

/**
 * Props for the FilesTreeItem component.
 *
 * @param nodeId id of the tree item
 * @param files files to display
 */
interface FilesTreeItemProps {
    nodeId: string;
    files: ProjectFiles;
}

/**
 * Tree item for the files of a project.
 *
 * @param nodeId id of the tree item
 * @param files files to display
 */
export function FilesTreeItem({nodeId, files}: FilesTreeItemProps) {
    const navigate = useNavigate();
    const {projectId} = useParams<{ projectId: string }>();

    const {
        contextMenu,
        handleContextMenu,
        handleClose
    } = useContextMenu();

    const handleUploadFiles = () => {
        handleClose();
        navigate(WebUiUris.uploadFiles(projectId!));
    };

    return (
        <>
            <StyledTreeItem
                nodeId={nodeId}
                labelText="Files"
                labelIcon={Folder}
                onContextMenu={handleContextMenu}
            >
                {
                    files.typingData.map((file, index) => {
                        return <StyledTreeItem nodeId={"0" + index.toString()} key={"0" + index.toString()}
                                               labelText={file.name} labelIcon={Description}/>
                    })
                }
                {
                    files.isolateData.map((file, index) => {
                        return <StyledTreeItem nodeId={"1" + index.toString()} key={"1" + index.toString()}
                                               labelText={file.name} labelIcon={FilePresent}/>
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
                <MenuItem onClick={handleUploadFiles}>
                    <UploadIcon color={"primary"}/>
                    Upload Files
                </MenuItem>
            </Menu>
        </>
    );
}