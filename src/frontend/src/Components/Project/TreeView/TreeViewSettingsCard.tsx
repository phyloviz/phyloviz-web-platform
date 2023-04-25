import * as React from "react"
import {useState} from "react"
import {Box, Button, Collapse, Grid, Input, Slider, Typography} from "@mui/material"
import IconButton from "@mui/material/IconButton"
import {Pause, PhotoCamera, PlayArrow, ZoomIn, ZoomOut} from "@mui/icons-material"


interface TreeViewSettingsCardProps {
    onPauseAnimation: () => void
    onRestartAnimation: () => void
    linkSpring: number
    linkDistance: number
    onChangeLinkSpring: (linkSpring: number) => void
    onChangeLinkDistance: (linkDistance: number) => void
}

const LINK_SPRING_STEP = 0.1
const LINK_SPRING_MIN = 0
const LINK_SPRING_MAX = 2

/**
 * Card with settings for the tree view.
 */
export function TreeViewSettingsCard({
                                         onPauseAnimation, onRestartAnimation,
                                         linkSpring, linkDistance, onChangeLinkSpring, onChangeLinkDistance
                                     }: TreeViewSettingsCardProps) {
    const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null)
    const open = Boolean(anchorEl)
    const [animationRunning, setAnimationRunning] = useState(true)
    const [settingsOpen, setSettingsOpen] = useState(false)

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
        <Collapse in={settingsOpen}>
            <Box>
                <Typography id="input-slider" gutterBottom>
                    Link Spring
                </Typography>
                <Grid container spacing={2} alignItems="center">
                    <Grid item xs>
                        <Slider
                            value={linkSpring}
                            onChange={(event, value) => onChangeLinkSpring(value as number)}
                            aria-labelledby="input-slider"
                            min={LINK_SPRING_MIN}
                            max={LINK_SPRING_MAX}
                            step={LINK_SPRING_STEP}
                        ></Slider>
                    </Grid>
                    <Grid item>
                        <Input
                            value={linkSpring}
                            size="small"
                            onChange={(event) => {
                                if (event.target.value !== '')
                                    onChangeLinkSpring(Number(event.target.value))
                            }}
                            onBlur={() => {
                                onChangeLinkDistance
                            }}
                            inputProps={{
                                step: LINK_SPRING_STEP,
                                min: LINK_SPRING_MIN,
                                max: LINK_SPRING_MAX,
                                type: 'number',
                                'aria-labelledby': 'input-slider',
                            }}
                        />
                    </Grid>
                </Grid>
                {/* <Slider value={linkDistance} onChange={(event, value) => onChangeLinkDistance(value as number)}></Slider> */}
            </Box>
        </Collapse>
        <Box sx={{
            display: "flex",
            flexDirection: "row",
            justifyContent: "space-between",
        }}>
            <Button onClick={() => setSettingsOpen((settingsOpen) => !settingsOpen)} size="small">
                Options
            </Button>
            {/* <Menu
                anchorEl={anchorEl}
                open={open}
                onClose={() => setAnchorEl(null)}
            >
                <MenuItem onClick={() => setAnchorEl(null)}>Export</MenuItem>
            </Menu> */}
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
                if (animationRunning)
                    onPauseAnimation()
                else
                    onRestartAnimation()

                setAnimationRunning((animationRunning) => !animationRunning)
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