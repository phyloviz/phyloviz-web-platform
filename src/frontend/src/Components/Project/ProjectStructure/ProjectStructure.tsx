import * as React from "react"
import {TreeView} from "@mui/lab"
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown'
import ArrowRightIcon from '@mui/icons-material/ArrowRight'
import {WorkflowsTreeItem} from "./Workflows/WorkflowsTreeItem"
import {Project} from "../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {Workflow} from "../../../Services/Compute/models/getWorkflowStatus/GetWorkflowStatusOutputModel"
import {Info} from "@mui/icons-material"
import {WebUiUris} from "../../../Pages/WebUiUris"
import {DatasetsTreeItem} from "./Datasets/DatasetsTreeItem"
import {StyledTreeItem} from "./Utils/StyledTreeItem"
import {useNavigate} from "react-router-dom"
import LoadingSpinner from "../../Shared/LoadingSpinner"
import {FilesTreeItem} from "./Files/FilesTreeItem"
import {ProjectIcon} from "../../Shared/Icons";

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
    loading: boolean
}


/**
 * ProjectStructureProps of a project.
 */
export function ProjectStructure({project, workflows, loading}: ProjectStructureProps) {
    const navigate = useNavigate()
    // TODO: Make the tree view responsive (collapse, or hide, idk)

    return (<>
        <TreeView
            defaultExpanded={['fileManager', 'workflows']}
            defaultCollapseIcon={<ArrowDropDownIcon/>}
            defaultExpandIcon={<ArrowRightIcon/>}
            sx={{
                height: '100%',
                maxWidth: 300,
                flexGrow: 1,
                borderRight: 1,
                borderBottom: 1,
                borderColor: 'divider',
                overflowY: 'auto'
            }}
        >
            <StyledTreeItem
                nodeId="fileManager"
                labelText={project?.name ?? "Project"}
                labelIcon={ProjectIcon}
                contextMenuItems={[
                    {
                        label: "Project Details",
                        icon: Info,
                        onClick: () => navigate(WebUiUris.project(project?.projectId!))
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
    </>)
}

