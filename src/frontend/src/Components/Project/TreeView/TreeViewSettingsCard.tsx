import * as React from "react"
import {useState} from "react"
import {Box, Button, MenuItem} from "@mui/material"
import Menu from "@mui/material/Menu"
import IconButton from "@mui/material/IconButton"
import {Pause, PhotoCamera, PlayArrow, ZoomIn, ZoomOut} from "@mui/icons-material"

/**
 * Card with settings for the tree view.
 */
export function TreeViewSettingsCard() {
    const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null)
    const open = Boolean(anchorEl)
    const animationRunning = useState(true)

    return <Box sx={{
        position: "absolute",
        bottom: 0,
        left: 0,
        zIndex: 1,
        backgroundColor: "white",
        borderRadius: 1,
        p: 1,
        m: 1,
        border: 1,
        borderColor: 'divider',
    }}>
        <Box sx={{
            display: "flex",
            flexDirection: "row",
            justifyContent: "space-between",
        }}>
            <Button onClick={(event) => setAnchorEl(event.currentTarget)} size="small">
                Options
            </Button>
            <Menu
                anchorEl={anchorEl}
                open={open}
                onClose={() => setAnchorEl(null)}
            >
                <MenuItem onClick={() => setAnchorEl(null)}>Export</MenuItem>
            </Menu>
            <IconButton size="small" onClick={() => {
                // TODO: zoom out
            }}
            >
                <ZoomOut/>
            </IconButton>
            <IconButton size="small" onClick={() => {
                // TODO: zoom in
            }}
            >
                <ZoomIn/>
            </IconButton>
            <IconButton size="small" onClick={() => {
                // TODO: take screenshot
            }}
            >
                <PhotoCamera/>
            </IconButton>
            <IconButton size="small" onClick={() => {
                // TODO: start/stop animation
            }}
            >
                {
                    animationRunning
                        ? <Pause/>
                        : <PlayArrow/>
                }
            </IconButton>
        </Box>
    </Box>
}