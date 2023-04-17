import * as React from "react"
import {TreeView} from "@mui/lab"
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown'
import ArrowRightIcon from '@mui/icons-material/ArrowRight'
import {AccountTree} from "@mui/icons-material"
import {FileManager} from "./FileManager/FileManager";
import {WorkflowManager} from "./WorkflowManager/WorkflowManager";
import {Project} from "../../../Services/administration/models/getProject/GetProjectOutputModel";
import {StyledTreeItem} from "./Utils/StyledTreeItem";

/**
 * Properties of the project structure.
 *
 * @property project project to display the files and folders of
 */
interface ProjectStructureProps {
    project: Project | null
}

/**
 * ProjectStructureProps of a project.
 */
export function ProjectStructure({project}: ProjectStructureProps) {
    return <TreeView
        defaultExpanded={['3']}
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
        <StyledTreeItem nodeId="projectStructure" labelText={project?.name ?? "Project"} labelIcon={AccountTree}>
            <FileManager project={project}/>
            <WorkflowManager/>
        </StyledTreeItem>
    </TreeView>
}
