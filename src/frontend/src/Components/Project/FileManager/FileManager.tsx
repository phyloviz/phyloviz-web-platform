import * as React from "react"
import {TreeView} from "@mui/lab"
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown'
import ArrowRightIcon from '@mui/icons-material/ArrowRight'
import {AccountTree} from "@mui/icons-material"
import {StyledTreeItem} from "./StyledTreeItem"
import {FilesTreeItem} from "./Files/FilesTreeItem"
import {GetProjectOutputModel} from "../../../Services/administration/models/getProject/GetProjectOutputModel"
import {DatasetsTreeItem} from "./Datasets/DatasetsTreeItem"

// TODO: maybe improve the styling of the file manager to be more responsive, maybe use
//  https://mui.com/material-ui/react-drawer/ to make it collapsible, and check the height and other properties

/**
 * Properties of the file manager.
 *
 * @property project project to display the files and folders of
 */
interface FileManagerProps {
    project: GetProjectOutputModel | null
}

/**
 * File manager of a project.
 * Uses the TreeView component from Material-UI to display the files and folders.
 */
export function FileManager({project}: FileManagerProps) {
    return <TreeView
        defaultExpanded={['1']}
        defaultCollapseIcon={<ArrowDropDownIcon/>}
        defaultExpandIcon={<ArrowRightIcon/>}
        defaultEndIcon={<div style={{width: 24}}/>}
        sx={{
            height: '100%',
            flexGrow: 1,
            maxWidth: 300,
            borderRight: 1,
            borderColor: 'divider',
            overflowY: 'auto'
        }}
    >
        <StyledTreeItem nodeId="0" labelText={project?.name ?? "Project"} labelIcon={AccountTree}>
            <DatasetsTreeItem nodeId="2" datasets={project?.datasets!}/>
            <FilesTreeItem nodeId="3" files={project?.files!}/>
        </StyledTreeItem>
    </TreeView>
}
