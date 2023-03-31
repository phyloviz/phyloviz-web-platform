import {styled} from "@mui/material/styles";
import MuiAppBar, {AppBarProps as MuiAppBarProps} from "@mui/material/AppBar";

export const drawerWidth: number = 200;

/**
 * Props for the AppBar component.
 *
 * @param open whether the drawer is open
 */
interface AppBarProps extends MuiAppBarProps {
    open?: boolean;
}

/**
 * Customized AppBar component.
 */
export const AppBar = styled(MuiAppBar, {
    shouldForwardProp: (prop) => prop !== 'open',
})<AppBarProps>(({theme, open}) => ({
    zIndex: theme.zIndex.drawer + 1,
    transition: theme.transitions.create(['width', 'margin'], {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.leavingScreen,
    }),
    ...(open && {
        ml: drawerWidth,
        width: `calc(100% - ${drawerWidth}px)`,
        transition: theme.transitions.create(['width', 'margin'], {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.enteringScreen,
        }),
    }),
}));
