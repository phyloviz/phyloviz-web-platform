import * as React from "react"
import {TreeView} from "@mui/lab"
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown'
import ArrowRightIcon from '@mui/icons-material/ArrowRight'
import {Project} from "../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {Workflow} from "../../../Services/Compute/models/getWorkflowStatus/GetWorkflowStatusOutputModel"
import {Edit, Info} from "@mui/icons-material"
import {WebUiUris} from "../../../Pages/WebUiUris"
import {StyledTreeItem} from "./Utils/StyledTreeItem"
import {ProjectIcon} from "../../Shared/Icons";
import {useNavigate} from "react-router-dom"
import {Resizable} from "re-resizable"
import LoadingSpinner from "../../Shared/LoadingSpinner";
import {DatasetsTreeItem} from "./Datasets/DatasetsTreeItem";
import {FilesTreeItem} from "./Files/FilesTreeItem";
import {WorkflowsTreeItem} from "./Workflows/WorkflowsTreeItem";

/**
 * Properties of the project structure.
 *
 * @property project project to display the files and folders of
 * @property workflows workflows to display
 * @property loading whether the project is loading
 */
interface ProjectStructureProps {
    project: Project | null
    workflows: Workflow[]
    loading: boolean,
    width: number,
    onWidthChange: (width: number) => void
}


/**
 * ProjectStructureProps of a project.
 */
export function ProjectStructure({project, workflows, loading, width, onWidthChange}: ProjectStructureProps) {
    const navigate = useNavigate()
    // TODO: Make the tree view responsive (collapse, or hide, idk)

    return <Resizable
        size={{width: width, height: "100%"}}
        minWidth={100}
        maxWidth={1000}
        onResizeStop={(e, direction, ref, d) => {
            onWidthChange(width + d.width)
        }}
        enable={{
            top: false,
            right: true,
            bottom: false,
            left: false,
        }}
    >
        <TreeView
            defaultExpanded={['fileManager']}
            defaultCollapseIcon={<ArrowDropDownIcon/>}
            defaultExpandIcon={<ArrowRightIcon/>}
            sx={{
                height: '100%',
                width: '100%',
                flexGrow: 1,
                borderRight: 1,
                borderBottom: 1,
                borderColor: 'divider',
                overflowX: 'auto',
                overflowY: 'auto'
            }}
        >
            <StyledTreeItem
                nodeId="fileManager"
                labelText={project?.name ?? "Project"}
                labelIcon={ProjectIcon}
                contextMenuItems={[
                    {
                        label: "Details",
                        icon: Info,
                        onClick: () => navigate(WebUiUris.project(project?.projectId!))
                    },
                    {
                        label: "Edit",
                        icon: Edit,
                        onClick: () => navigate(WebUiUris.editProject(project?.projectId!))
                    }
                ]}
            >
                {
                    loading
                        ? <LoadingSpinner text={"Loading project..."}/>
                        : <>
                            <DatasetsTreeItem nodeId="datasets" datasets={project?.datasets!}/>
                            <FilesTreeItem nodeId="files" files={project?.files!}/>
                            <WorkflowsTreeItem workflows={workflows}/>
                        </>
                }
            </StyledTreeItem>
        </TreeView>
    </Resizable>
}

