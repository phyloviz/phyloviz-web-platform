import {TreeItem, treeItemClasses, TreeItemProps} from "@mui/lab";
import * as React from "react";
import {MouseEventHandler} from "react";
import {SvgIconProps} from "@mui/material";
import {styled} from "@mui/material/styles";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";

declare module 'react' {
    interface CSSProperties {
        '--tree-view-color'?: string;
        '--tree-view-bg-color'?: string;
    }
}

/**
 * Props for the StyledTreeItem component.
 *
 * @param bgColor background color of the tree item
 * @param color color of the tree item
 * @param labelIcon icon of the tree item
 * @param labelInfo info of the tree item
 * @param labelText text of the tree item
 * @param handleContextMenu handler for the context menu (right click)
 */
type StyledTreeItemProps = TreeItemProps & {
    bgColor?: string;
    color?: string;
    labelIcon: React.ElementType<SvgIconProps>;
    labelInfo?: string;
    labelText: string;
    handleContextMenu?: MouseEventHandler<HTMLLIElement>;
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

/**
 * Styled tree item for the files tree.
 *
 * @param props props for the StyledTreeItem component
 */
export function StyledTreeItem(props: StyledTreeItemProps) {
    const {
        bgColor,
        color,
        labelIcon: LabelIcon,
        labelInfo,
        labelText,
        handleContextMenu,
        ...other
    } = props;

    return (
        <StyledTreeItemRoot
            label={
                <Box sx={{display: 'flex', alignItems: 'center', p: 0.5, pr: 0}}>
                    <Box component={LabelIcon} color="inherit" sx={{mr: 1}}/>
                    <Typography variant="body2" sx={{fontWeight: 'inherit'}}>
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
                'cursor': handleContextMenu ? 'context-menu' : 'default'
            }}
            onContextMenu={handleContextMenu ? handleContextMenu : undefined}
            {...other}
        />
    );
}
