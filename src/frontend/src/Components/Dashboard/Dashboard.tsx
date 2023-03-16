import * as React from 'react';
import {createTheme, styled, ThemeProvider} from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import MuiDrawer from '@mui/material/Drawer';
import Box from '@mui/material/Box';
import MuiAppBar, {AppBarProps as MuiAppBarProps} from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import Container from '@mui/material/Container';
import Paper from '@mui/material/Paper';
import MenuIcon from '@mui/icons-material/Menu';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import HomeIcon from '@mui/icons-material/Home';
import AboutIcon from '@mui/icons-material/Info';
import NewsIcon from '@mui/icons-material/Newspaper';
import ApiIcon from '@mui/icons-material/Api';
import UploadIcon from '@mui/icons-material/Upload';
import OpenProjectIcon from '@mui/icons-material/FolderOpen';
import NewProjectIcon from '@mui/icons-material/CreateNewFolder';
import Logo from "../../Assets/logo.png"


const drawerWidth: number = 240;

interface AppBarProps extends MuiAppBarProps {
    open?: boolean;
}

const AppBar = styled(MuiAppBar, {
    shouldForwardProp: (prop) => prop !== 'open',
})<AppBarProps>(({theme, open}) => ({
    zIndex: theme.zIndex.drawer + 1,
    transition: theme.transitions.create(['width', 'margin'], {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.leavingScreen,
    }),
    ...(open && {
        marginLeft: drawerWidth,
        width: `calc(100% - ${drawerWidth}px)`,
        transition: theme.transitions.create(['width', 'margin'], {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.enteringScreen,
        }),
    }),
}));


const mainListItems = (
    <React.Fragment>
        <ListItemButton>
            <ListItemIcon>
                <HomeIcon/>
            </ListItemIcon>
            <ListItemText primary="Home"/>
        </ListItemButton>
        <ListItemButton>
            <ListItemIcon>
                <AboutIcon/>
            </ListItemIcon>
            <ListItemText primary="About"/>
        </ListItemButton>
        <ListItemButton>
            <ListItemIcon>
                <NewsIcon/>
            </ListItemIcon>
            <ListItemText primary="News"/>
        </ListItemButton>
        <ListItemButton>
            <ListItemIcon>
                <ApiIcon/>
            </ListItemIcon>
            <ListItemText primary="API"/>
        </ListItemButton>
    </React.Fragment>
);

const secondaryListItems = (
    <React.Fragment>
        <ListItemButton>
            <ListItemIcon>
                <UploadIcon/>
            </ListItemIcon>
            <ListItemText primary="Load Datasets"/>
        </ListItemButton>
        <ListItemButton>
            <ListItemIcon>
                <NewProjectIcon/>
            </ListItemIcon>
            <ListItemText primary="New Project"/>
        </ListItemButton>
        <ListItemButton>
            <ListItemIcon>
                <OpenProjectIcon/>
            </ListItemIcon>
            <ListItemText primary="Open Project"/>
        </ListItemButton>
    </React.Fragment>
);

const Drawer = styled(MuiDrawer, {shouldForwardProp: (prop) => prop !== 'open'})(
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

const mdTheme = createTheme();

function DashboardContent() {
    const [open, setOpen] = React.useState(true);
    const toggleDrawer = () => {
        setOpen(!open);
    };

    return (
        <ThemeProvider theme={mdTheme}>
            <Box sx={{display: 'flex'}}>
                <CssBaseline/>
                <AppBar position="absolute" open={open}>
                    <Toolbar
                        sx={{
                            pr: '24px', // keep right padding when drawer closed
                        }}
                    >
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
                        <Typography
                            component="h1"
                            variant="h5"
                            color="inherit"
                            noWrap
                            sx={{flexGrow: 1}}
                        >
                            <strong>PHYLOViZ Web Platform</strong>
                        </Typography>
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
                        {mainListItems}
                        <Divider sx={{my: 1}}/>
                        {secondaryListItems}
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
                        <Paper
                            sx={{
                                p: 4,
                                display: 'flex',
                                flexDirection: 'column',
                                marginTop: 4,
                                alignItems: 'center'
                            }}
                        >
                            <img src={Logo} alt="Logo" width="10%"/>
                            <Typography component="h1" variant="h4">
                                <strong>PHYLOViZ Web Platform</strong>
                            </Typography>
                            <Typography component="h1" variant="h5">
                                A Modular and Web-Based Tool for Phylogenetic Analysis
                            </Typography>
                            <Typography component="h1" variant="body1" sx={{marginTop: 4}}>
                                PHYLOViZ Web Platform is a modular and web-based tool for phylogenetic analysis. It is
                                a modular tool that unifies both <a href={"https://www.phyloviz.net"}>PHYLOViZ</a> and
                                <a href={"https://online.phyloviz.net"}> PHYLOViZ Online</a> into a single and new
                                platform.
                            </Typography>
                        </Paper>
                    </Container>
                </Box>
            </Box>
        </ThemeProvider>
    );
}

export default function Dashboard() {
    return <DashboardContent/>;
}
