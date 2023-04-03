import * as React from 'react';
import CssBaseline from '@mui/material/CssBaseline';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import {AppBar} from "./components/AppBar";
import {Drawer, mainListItems, secondaryListItems} from "./components/Drawer";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import Logo from "../../Assets/logo.png";
import AccountMenu from "./components/AccountMenu";
import {useDashboard} from "./useDashboard";


/**
 * Props of the Dashboard component.
 *
 * @param children the main content of the dashboard
 */
interface DashboardProps {
    children: React.ReactNode;
}

/**
 * Dashboard component that contains the sidebar and the main content.
 */
export default function Dashboard({children}: DashboardProps) {
    const {
        open,
        toggleDrawer,
        loggedIn,
        session,
        authSettings,
        nonAuthSettings,
        navigate
    } = useDashboard()

    return (
        <Box sx={{display: 'flex'}}>
            <CssBaseline/>
            <AppBar position="absolute" open={open}>
                <Toolbar sx={{pr: '24px'}}>
                    <IconButton
                        edge="start"
                        color="inherit"
                        aria-label="open drawer"
                        onClick={toggleDrawer}
                        sx={{
                            mr: '36px',
                            ...(open && {display: 'none'}),
                        }}
                    >
                        <MenuIcon/>
                    </IconButton>
                    <Box sx={{flexGrow: 1, display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
                        <img alt="logo" src={Logo} width={40} height={40} style={{marginRight: '10px'}}/>
                        <Typography
                            component="h1"
                            variant="body1"
                            color="inherit"
                            noWrap
                        >
                            <strong>PHYLOViZ Web Platform</strong>
                        </Typography>
                    </Box>
                    <AccountMenu
                        avatar={loggedIn && session?.picture ? session!.picture : ""}
                        settings={loggedIn ? authSettings : nonAuthSettings}
                    />
                </Toolbar>
            </AppBar>
            <Drawer variant="permanent" open={open}>
                <Toolbar sx={{
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'flex-end',
                    px: [1],
                }}>
                    <IconButton onClick={toggleDrawer}>
                        <ChevronLeftIcon/>
                    </IconButton>
                </Toolbar>
                <Divider/>
                <List component="nav">
                    {
                        mainListItems.map((item) => {
                            return (
                                <ListItemButton onClick={() => navigate(item.href)} key={item.name}>
                                    <ListItemIcon>
                                        {item.icon}
                                    </ListItemIcon>
                                    <ListItemText primary={item.name}/>
                                </ListItemButton>
                            );
                        })
                    }
                    <Divider sx={{my: 1}}/>
                    {
                        secondaryListItems.map((item) => {
                            return (
                                <ListItemButton onClick={() => navigate(item.href)} key={item.name}>
                                    <ListItemIcon>
                                        {item.icon}
                                    </ListItemIcon>
                                    <ListItemText primary={item.name}/>
                                </ListItemButton>
                            );
                        })
                    }
                </List>
            </Drawer>
            <Box component="main" sx={{
                backgroundColor: (theme) =>
                    theme.palette.mode === 'light'
                        ? theme.palette.grey[100]
                        : theme.palette.grey[900],
                flexGrow: 1,
                height: '100vh',
                overflow: 'auto',
            }}>
                <Toolbar/>
                {children}
            </Box>
        </Box>
    );
}
