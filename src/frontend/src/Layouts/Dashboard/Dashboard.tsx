import * as React from 'react';
import {useState} from 'react';
import CssBaseline from '@mui/material/CssBaseline';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import Container from '@mui/material/Container';
import MenuIcon from '@mui/icons-material/Menu';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import {AppBar} from "./AppBar";
import {Drawer, mainListItems, secondaryListItems} from "./Sidebar";
import {useNavigate} from "react-router-dom";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import {Avatar, Menu, MenuItem, Tooltip} from "@mui/material";
import Logo from "../../Assets/logo.png";
import {Uris} from "../../Utils/navigation/Uris";


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
 *
 * @param children the main content.
 */
export default function Dashboard({children}: DashboardProps) {
    const [open, setOpen] = useState(true);
    const navigate = useNavigate();
    const toggleDrawer = () => {
        setOpen(!open);
    };
    const [anchorElUser, setAnchorElUser] = useState<null | HTMLElement>(null)

    const handleOpenUserMenu = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorElUser(event.currentTarget)
    }
    const handleCloseUserMenu = () => setAnchorElUser(null)

    const settings = [
        {
            name: 'Profile',
            callback: () => navigate(Uris.PROFILE),
            auth: true
        },
        {
            name: 'Logout',
            callback: async () => {
                // TODO: logout
                navigate(Uris.HOME)
            },
            auth: true
        }
    ]


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
                            marginRight: '36px',
                            ...(open && {display: 'none'}),
                        }}
                    >
                        <MenuIcon/>
                    </IconButton>
                    <Box sx={{flexGrow: 1, display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
                        <Avatar alt="logo" src={Logo} sx={{
                            mr: 1,
                        }}
                        />
                        <Typography
                            component="h1"
                            variant="body1"
                            color="inherit"
                            noWrap
                        >
                            <strong>PHYLOViZ Web Platform</strong>
                        </Typography>
                    </Box>
                    <Box sx={{flexGrow: 0}}>
                        <Tooltip title="Open settings">
                            <IconButton onClick={handleOpenUserMenu} sx={{p: 0}}>
                                <Avatar alt="User Avatar" src=""/>
                            </IconButton>
                        </Tooltip>
                        <Menu
                            sx={{mt: '45px'}}
                            id="menu-appbar"
                            anchorEl={anchorElUser}
                            anchorOrigin={{
                                vertical: 'top',
                                horizontal: 'right',
                            }}
                            keepMounted
                            transformOrigin={{
                                vertical: 'top',
                                horizontal: 'right',
                            }}
                            open={Boolean(anchorElUser)}
                            onClose={handleCloseUserMenu}
                        >
                            {settings.map((setting) => (
                                <MenuItem key={setting.name} onClick={() => {
                                    handleCloseUserMenu()
                                    setting.callback()
                                }}>
                                    <Typography textAlign="center">{setting.name}</Typography>
                                </MenuItem>
                            ))}
                        </Menu>
                    </Box>
                </Toolbar>
            </AppBar>
            <Drawer variant="permanent" open={open}>
                <Toolbar
                    sx={{
                        display: 'flex',
                        alignItems: 'center',
                        justifyContent: 'flex-end',
                        px: [1],
                    }}
                >
                    <IconButton onClick={toggleDrawer}>
                        <ChevronLeftIcon/>
                    </IconButton>
                </Toolbar>
                <Divider/>
                <List component="nav">
                    {
                        mainListItems.map((item) => {
                            return (
                                <ListItemButton onClick={() => navigate(item.href)}>
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
                                <ListItemButton onClick={() => navigate(item.href)}>
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
            <Box
                component="main"
                sx={{
                    backgroundColor: (theme) =>
                        theme.palette.mode === 'light'
                            ? theme.palette.grey[100]
                            : theme.palette.grey[900],
                    flexGrow: 1,
                    height: '100vh',
                    overflow: 'auto',
                }}
            >
                <Toolbar/>
                <Container maxWidth="lg">
                    {children}
                </Container>
            </Box>
        </Box>
    );
}
