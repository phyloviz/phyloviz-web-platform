import * as React from "react"
import {Folder} from "@mui/icons-material"
import {StyledTreeItem} from "../Utils/StyledTreeItem"
import {FilesTreeItem} from "./Files/FilesTreeItem"
import {Project} from "../../../../Services/administration/models/getProject/GetProjectOutputModel"
import {DatasetsTreeItem} from "./Datasets/DatasetsTreeItem"

/**
 * Properties of the file manager.
 *
 * @property project project to display the files and folders of
 */
interface FileManagerProps {
    project: Project | null
}

/**
 * File manager of a project.
 */
export function FileManager({project}: FileManagerProps) {
    return (
        <StyledTreeItem nodeId="0" labelText={project?.name ?? "Project"} labelIcon={Folder}>
            <DatasetsTreeItem nodeId="1" datasets={project?.datasets!}/>
            <FilesTreeItem nodeId="2" files={project?.files!}/>
        </StyledTreeItem>
    )
}
