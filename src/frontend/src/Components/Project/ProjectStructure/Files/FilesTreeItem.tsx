import * as React from "react"
import {Description, FilePresent, Folder} from "@mui/icons-material"
import {ProjectFiles} from "../../../../Services/administration/models/getProject/GetProjectOutputModel"
import {StyledTreeItem} from "../Utils/StyledTreeItem"
import {useFilesTreeItem} from "./useFilesTreeItem"

/**
 * Props for the FilesTreeItem component.
 *
 * @property nodeId id of the tree item
 * @property files files to display
 */
interface FilesTreeItemProps {
    nodeId: string
    files: ProjectFiles
}

/**
 * Tree item for the files of a project.
 */
export function FilesTreeItem({nodeId, files}: FilesTreeItemProps) {
    const {contextMenuItems} = useFilesTreeItem()

    return (
        <StyledTreeItem
            nodeId={nodeId}
            labelText="Files"
            labelIcon={Folder}
            contextMenuItems={contextMenuItems}
        >
            {
                files.typingData.map((file, index) => {
                    return <StyledTreeItem
                        nodeId={"0" + index.toString()}
                        key={"0" + index.toString()}
                        labelText={file.name}
                        labelIcon={Description}
                    />
                })
            }
            {
                files.isolateData.map((file, index) => {
                    return <StyledTreeItem
                        nodeId={"1" + index.toString()}
                        key={"1" + index.toString()}
                        labelText={file.name}
                        labelIcon={FilePresent}
                    />
                })
            }
        </StyledTreeItem>
    )
}
