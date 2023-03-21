import * as React from "react";
import UploadIcon from "@mui/icons-material/Upload";
import NewProjectIcon from "@mui/icons-material/CreateNewFolder";
import OpenProjectIcon from "@mui/icons-material/FolderOpen";
import {styled} from "@mui/material/styles";
import MuiDrawer from "@mui/material/Drawer";
import {drawerWidth} from "./AppBar";
import HomeIcon from "@mui/icons-material/Home";
import AboutIcon from "@mui/icons-material/Info";
import NewsIcon from "@mui/icons-material/Newspaper";
import ApiIcon from "@mui/icons-material/Api";
import {Uris} from "../../Utils/navigation/Uris";

/**
 * List of the main items in the sidebar.
 */
export const mainListItems = [
    {
        name: 'Home',
        href: Uris.HOME,
        icon: <HomeIcon/>,
    },
    {
        name: 'About',
        href: Uris.ABOUT,
        icon: <AboutIcon/>,
    },
    {
        name: 'News',
        href: Uris.NEWS,
        icon: <NewsIcon/>,
    },
    {
        name: 'API',
        href: Uris.API_INFO,
        icon: <ApiIcon/>,
    },
];

/**
 * List of the secondary items in the sidebar.
 */
export const secondaryListItems = [
    {
        name: 'Load Dataset',
        href: Uris.LOAD_DATASET,
        icon: <UploadIcon/>,
    },
    {
        name: 'New Project',
        href: Uris.NEW_PROJECT,
        icon: <NewProjectIcon/>,
    },
    {
        name: 'Open Project',
        href: Uris.OPEN_PROJECT,
        icon: <OpenProjectIcon/>,
    },
];

/**
 * Drawer component.
 */
export const Drawer = styled(MuiDrawer, {shouldForwardProp: (prop) => prop !== 'open'})(
    ({theme, open}) => ({
        '& .MuiDrawer-paper': {
            position: 'relative',
            whiteSpace: 'nowrap',
            width: drawerWidth,
            transition: theme.transitions.create('width', {
                easing: theme.transitions.easing.sharp,
                duration: theme.transitions.duration.enteringScreen,
            }),
            boxSizing: 'border-box',
            ...(!open && {
                overflowX: 'hidden',
                transition: theme.transitions.create('width', {
                    easing: theme.transitions.easing.sharp,
                    duration: theme.transitions.duration.leavingScreen,
                }),
                width: theme.spacing(7),
                [theme.breakpoints.up('sm')]: {
                    width: theme.spacing(9),
                },
            }),
        },
    }),
);
