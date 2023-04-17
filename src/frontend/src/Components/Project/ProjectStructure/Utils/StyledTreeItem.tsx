import {TreeItem, treeItemClasses, TreeItemProps} from "@mui/lab"
import * as React from "react"
import {MouseEventHandler} from "react"
import {Menu, MenuItem, SvgIconProps} from "@mui/material"
import {styled} from "@mui/material/styles"
import Box from "@mui/material/Box"
import Typography from "@mui/material/Typography"
import {useContextMenu} from "./useContextMenu";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import ArrowRightIcon from "@mui/icons-material/ArrowRight";
import {NestedMenuItem} from "mui-nested-menu";

declare module 'react' {
    interface CSSProperties {
        '--tree-view-color'?: string
        '--tree-view-bg-color'?: string
    }
}

/**
 * Props for the StyledTreeItem component.
 *
 * @property bgColor background color of the tree item
 * @property color color of the tree item
 * @property labelIcon icon of the tree item
 * @property labelInfo info of the tree item
 * @property labelText text of the tree item
 * @property contextMenuItems items to display in the context menu
 */
type StyledTreeItemProps = TreeItemProps & {
    bgColor?: string
    color?: string
    labelIcon: React.ElementType<SvgIconProps>
    labelInfo?: string
    labelText: string
    contextMenuItems?: ContextMenuItem[]
}

/**
 * An item in the context menu.
 *
 * @property label label of the item
 * @property icon icon of the item
 * @property onClick click handler of the item
 * @property nestedItems nested items of the item
 */
interface ContextMenuItem {
    label: string
    icon: React.ElementType<SvgIconProps>
    onClick?: MouseEventHandler<HTMLLIElement>
    nestedItems?: ContextMenuItem[]
}

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
}))

/**
 * Styled tree item for the files tree.
 */
export function StyledTreeItem(
    {bgColor, color, labelIcon: LabelIcon, labelInfo, labelText, contextMenuItems, ...other}: StyledTreeItemProps
) {
    const {
        contextMenu,
        handleContextMenu,
        handleClose
    } = useContextMenu()

    return (
        <>
            <StyledTreeItemRoot
                label={
                    <div
                        style={{'cursor': contextMenuItems ? 'context-menu' : 'default'}}
                        onContextMenu={contextMenuItems ? handleContextMenu : undefined}
                    >
                        <Box sx={{display: 'flex', alignItems: 'center', p: 0.5, pr: 0}}>
                            <Box component={LabelIcon} color="inherit" sx={{mr: 1}}/>
                            <Typography variant="body2" sx={{fontWeight: 'inherit'}}>
                                {labelText}
                            </Typography>
                            <Typography variant="caption" color="inherit">
                                {labelInfo}
                            </Typography>
                        </Box>
                    </div>
                }
                style={{
                    '--tree-view-color': color,
                    '--tree-view-bg-color': bgColor
                }}
                {...other}
            />
            {
                contextMenuItems &&
                <Menu
                    open={contextMenu !== null}
                    onClose={handleClose}
                    anchorReference="anchorPosition"
                    anchorPosition={
                        contextMenu !== null
                            ? {top: contextMenu.mouseY, left: contextMenu.mouseX}
                            : undefined
                    }
                >
                    <ContextMenuItems items={contextMenuItems!} handleClose={handleClose}/>
                </Menu>
            }
        </>
    )
}

/**
 * Context menu items.
 *
 * @param items items to display in the context menu
 * @param handleClose close handler of the context menu
 */
function ContextMenuItems({items, handleClose}: { items: ContextMenuItem[], handleClose: () => void }) {
    return (
        <>
            {
                items.map((item, index) => (
                    item.nestedItems
                        ? <NestedMenuItem
                            leftIcon={<item.icon color={"primary"}/>}
                            rightIcon={<ArrowRightIcon/>}
                            label={item.label}
                            key={index}
                            parentMenuOpen={true}
                            sx={{pl: 2}}
                        >
                            <ContextMenuItems items={item.nestedItems} handleClose={handleClose}/>
                        </NestedMenuItem>
                        : <MenuItem key={index} onClick={(event) => {
                            handleClose()
                            if (item.onClick)
                                item.onClick(event)
                        }}>
                            <ListItemIcon>
                                {item.icon && <item.icon color={"primary"}/>}
                            </ListItemIcon>
                            <ListItemText>{item.label}</ListItemText>
                        </MenuItem>
                ))
            }
        </>
    )
}
