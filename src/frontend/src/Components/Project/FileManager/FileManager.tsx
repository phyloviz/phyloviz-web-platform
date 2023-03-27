import * as React from "react"
import {TreeView} from "@mui/lab";
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown';
import ArrowRightIcon from '@mui/icons-material/ArrowRight';
import {AccountTree, Dataset, Description, FilePresent, FolderOpen, ScatterPlot, TableView} from "@mui/icons-material";
import {StyledTreeItem} from "./StyledTreeItem";
import {FilesTreeItem} from "./FilesTreeItem";


/**
 * File manager of a project.
 * Uses the TreeView component from Material-UI to display the files and folders.
 */
export function FileManager() { // TODO: replace with actual project files
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
        <StyledTreeItem nodeId="0" labelText="Project Test" labelIcon={FolderOpen}>
            <StyledTreeItem nodeId="1" labelText="Datasets" labelIcon={AccountTree}>
                <StyledTreeItem nodeId="2" labelText="Dataset 1" labelIcon={Dataset}>
                    <StyledTreeItem nodeId="5" labelText="Isolated Data" labelIcon={FilePresent}/>
                    <StyledTreeItem nodeId="4" labelText="Typing Data" labelIcon={Description}>
                        <StyledTreeItem nodeId="7" labelText="Hamming" labelIcon={TableView}>
                            <StyledTreeItem nodeId="10" labelText="goeBURST" labelIcon={ScatterPlot}/>
                            <StyledTreeItem nodeId="11" labelText="Neighbor Joining" labelIcon={ScatterPlot}/>
                        </StyledTreeItem>
                    </StyledTreeItem>
                </StyledTreeItem>
            </StyledTreeItem>
            <FilesTreeItem nodeId="3">
                <StyledTreeItem nodeId="13" labelText="Typing Data1" labelIcon={Description}/>
                <StyledTreeItem nodeId="14" labelText="Isolated Data1" labelIcon={FilePresent}/>
            </FilesTreeItem>
        </StyledTreeItem>
    </TreeView>
}
