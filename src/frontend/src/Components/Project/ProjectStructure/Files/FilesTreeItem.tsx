import * as React from "react"
import {ProjectFiles} from "../../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {StyledTreeItem} from "../Utils/StyledTreeItem"
import {useFilesTreeItem} from "./useFilesTreeItem"
import {TypingDataFileTreeItem} from "./TypingData/TypingDataFileTreeItem"
import {IsolateDataFileTreeItem} from "./IsolateData/IsolateDataFileTreeItem"
import {FilesIcon} from "../../../Shared/Icons";

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
            labelIcon={FilesIcon}
            contextMenuItems={contextMenuItems}
        >
            {
                files.typingData.map((file, index) => {
                    return <TypingDataFileTreeItem
                        nodeId={"typingDataFile" + index.toString()}
                        key={"typingDataFile" + index.toString()}
                        file={file}
                    />
                })
            }
            {
                files.isolateData.map((file, index) => {
                    return <IsolateDataFileTreeItem
                        nodeId={"isolateDataFile" + index.toString()}
                        key={"isolateDataFile" + index.toString()}
                        file={file}
                    />
                })
            }
        </StyledTreeItem>
    )
}
