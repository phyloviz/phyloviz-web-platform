import * as React from "react"
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import {SvgIconProps} from "@mui/material";
import {TreeItem, treeItemClasses, TreeItemProps, TreeView} from "@mui/lab";
import {styled} from "@mui/material/styles";
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown';
import ArrowRightIcon from '@mui/icons-material/ArrowRight';
import {
    AccountTree,
    Dataset,
    Description,
    FilePresent,
    Forest,
    Inventory2,
    ScatterPlot,
    TableBar,
    TableView
} from "@mui/icons-material";


declare module 'react' {
    interface CSSProperties {
        '--tree-view-color'?: string;
        '--tree-view-bg-color'?: string;
    }
}

type StyledTreeItemProps = TreeItemProps & {
    bgColor?: string;
    color?: string;
    labelIcon: React.ElementType<SvgIconProps>;
    labelInfo?: string;
    labelText: string;
};

const StyledTreeItemRoot = styled(TreeItem)(({theme}) => ({
    color: theme.palette.text.secondary,
    [`& .${treeItemClasses.content}`]: {
        color: theme.palette.text.secondary,
        borderTopRightRadius: theme.spacing(2),
        borderBottomRightRadius: theme.spacing(2),
        paddingRight: theme.spacing(1),
        fontWeight: theme.typography.fontWeightMedium,
        '&.Mui-expanded': {
            fontWeight: theme.typography.fontWeightRegular,
        },
        '&:hover': {
            backgroundColor: theme.palette.action.hover,
        },
        '&.Mui-focused, &.Mui-selected, &.Mui-selected.Mui-focused': {
            backgroundColor: `var(--tree-view-bg-color, ${theme.palette.action.selected})`,
            color: 'var(--tree-view-color)',
        },
        [`& .${treeItemClasses.label}`]: {
            fontWeight: 'inherit',
            color: 'inherit',
        },
    }
}));

function StyledTreeItem(props: StyledTreeItemProps) {
    const {
        bgColor,
        color,
        labelIcon: LabelIcon,
        labelInfo,
        labelText,
        ...other
    } = props;

    return (
        <StyledTreeItemRoot
            label={
                <Box sx={{display: 'flex', alignItems: 'center', p: 0.5, pr: 0}}>
                    <Box component={LabelIcon} color="inherit" sx={{mr: 1}}/>
                    <Typography variant="body2" sx={{fontWeight: 'inherit', flexGrow: 1}}>
                        {labelText}
                    </Typography>
                    <Typography variant="caption" color="inherit">
                        {labelInfo}
                    </Typography>
                </Box>
            }
            style={{
                '--tree-view-color': color,
                '--tree-view-bg-color': bgColor,
            }}
            {...other}
        />
    );
}

/**
 * File manager of a project.
 * Uses the TreeView component from Material-UI to display the files and folders.
 */
export function FileManager() {
    return <TreeView
        defaultExpanded={['3']}
        defaultCollapseIcon={<ArrowDropDownIcon/>}
        defaultExpandIcon={<ArrowRightIcon/>}
        defaultEndIcon={<div style={{width: 24}}/>}
        sx={{height: 264, flexGrow: 1, maxWidth: 400, overflowY: 'auto'}}
    >
        <StyledTreeItem nodeId="1" labelText="Datasets" labelIcon={AccountTree}>
            <StyledTreeItem nodeId="2" labelText="Dataset 1" labelIcon={Dataset}>
                <StyledTreeItem nodeId="3" labelText="Data" labelIcon={Inventory2}>
                    <StyledTreeItem nodeId="4" labelText="Typing Data" labelIcon={Description}/>
                    <StyledTreeItem nodeId="5" labelText="Isolated Data" labelIcon={FilePresent}/>
                </StyledTreeItem>
                <StyledTreeItem nodeId="6" labelText="Distance Matrices" labelIcon={TableBar}>
                    <StyledTreeItem nodeId="7" labelText="Hamming" labelIcon={TableView}/>
                    <StyledTreeItem nodeId="8" labelText="Levenshtein" labelIcon={TableView}/>
                </StyledTreeItem>
                <StyledTreeItem nodeId="9" labelText="Phylogenetic Trees" labelIcon={Forest}>
                    <StyledTreeItem nodeId="10" labelText="goeBURST" labelIcon={ScatterPlot}/>
                    <StyledTreeItem nodeId="11" labelText="Neighbor Joining" labelIcon={ScatterPlot}/>
                </StyledTreeItem>
            </StyledTreeItem>
        </StyledTreeItem>
    </TreeView>
}