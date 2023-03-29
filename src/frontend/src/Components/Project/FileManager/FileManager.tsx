import * as React from "react"
import {TreeView} from "@mui/lab";
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown';
import ArrowRightIcon from '@mui/icons-material/ArrowRight';
import {AccountTree, Dataset, Description, FilePresent, Inventory2, ScatterPlot, TableView} from "@mui/icons-material";
import {StyledTreeItem} from "./StyledTreeItem";
import {FilesTreeItem} from "./FilesTreeItem";
import {GetProjectOutputModel} from "../../../Services/administration/models/getProject/GetProjectOutputModel";
import {DatasetTreeItem} from "./DatasetTreeItem";

/**
 * Properties of the file manager.
 *
 * @param project project to display the files and folders of
 */
interface FileManagerProps {
    project: GetProjectOutputModel | null;
}

// TODO: maybe improve the styling of the file manager to be more responsive, maybe use
//  https://mui.com/material-ui/react-drawer/ to make it collapsible, and check the height and other properties

/**
 * File manager of a project.
 * Uses the TreeView component from Material-UI to display the files and folders.
 */
export function FileManager({project}: FileManagerProps) { // TODO: replace with actual project files
    return <TreeView
        defaultExpanded={['2']}
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
        <StyledTreeItem nodeId="0" labelText="Project Test" labelIcon={AccountTree}>
            <StyledTreeItem nodeId="1" labelText="Datasets" labelIcon={Inventory2}>
                {
                    project?.datasets.map((dataset, index) => {
                        return <DatasetTreeItem nodeId={index.toString()} dataset={dataset}/>
                    })
                }
                <StyledTreeItem nodeId="2" labelText="Dataset 1" labelIcon={Dataset}>

                    <StyledTreeItem nodeId="5" labelText="Isolate Data" labelIcon={FilePresent}/>
                    <StyledTreeItem nodeId="4" labelText="Typing Data" labelIcon={Description}/>
                    <StyledTreeItem nodeId="7" labelText="Hamming" labelIcon={TableView}/>
                    <StyledTreeItem nodeId="10" labelText="goeBURST" labelIcon={ScatterPlot}/>
                    <StyledTreeItem nodeId="11" labelText="Neighbor Joining" labelIcon={ScatterPlot}/>
                </StyledTreeItem>
            </StyledTreeItem>
            <FilesTreeItem nodeId="3">
                <StyledTreeItem nodeId="13" labelText="Typing Data1" labelIcon={Description}/>
                <StyledTreeItem nodeId="14" labelText="Isolated Data1" labelIcon={FilePresent}/>
                {
                    project?.files.typingData.map((file, index) => {
                        return <StyledTreeItem nodeId={index.toString()} labelText={file.name} labelIcon={Description}/>
                    })
                }
                {
                    project?.files.isolateData.map((file, index) => {
                        return <StyledTreeItem nodeId={index.toString()} labelText={file.name} labelIcon={FilePresent}/>
                    })
                }
            </FilesTreeItem>
        </StyledTreeItem>
    </TreeView>
}
