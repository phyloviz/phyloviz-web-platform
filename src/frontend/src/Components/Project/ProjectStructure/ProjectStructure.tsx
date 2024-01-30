import * as React from "react"
import {TreeView} from "@mui/lab"
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown'
import ArrowRightIcon from '@mui/icons-material/ArrowRight'
import {Project} from "../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {GetWorkflowStatusOutputModel} from "../../../Services/Compute/models/getWorkflow/GetWorkflowOutputModel"
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
    workflows: GetWorkflowStatusOutputModel[]
    loading: boolean
}


/**
 * ProjectStructureProps of a project.
 */
export function ProjectStructure({project, workflows, loading}: ProjectStructureProps) {
    const navigate = useNavigate()

    const [projectStructureWidth, setProjectStructureWidth] = React.useState("20%")

    return <Resizable
        size={{width: projectStructureWidth, height: "100%"}}
        minWidth={"5%"}
        maxWidth={"50%"}
        onResizeStop={(e, direction, ref, d) => {
            setProjectStructureWidth((width) => width + d.width)
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
                    loading || !project
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

