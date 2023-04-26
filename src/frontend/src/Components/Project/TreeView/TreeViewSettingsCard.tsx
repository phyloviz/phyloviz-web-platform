import * as React from "react"
import {useState} from "react"
import {Box, Button, Collapse, Typography} from "@mui/material"
import IconButton from "@mui/material/IconButton"
import {Pause, PhotoCamera, PlayArrow, ZoomIn, ZoomOut} from "@mui/icons-material"
import {InputSlider} from './InputSlider';


interface TreeViewSettingsCardProps {
    onPauseAnimation: () => void
    onRestartAnimation: () => void
    resetSimulationConfig: () => void

    linkSpring: number
    linkDistance: number
    gravity: number
    friction: number
    repulsion: number
    repulsionTheta: number
    decay: number

    onChangeLinkSpring: (linkSpring: number) => void
    onChangeLinkDistance: (linkDistance: number) => void
    onChangeGravity: (gravity: number) => void
    onChangeFriction: (friction: number) => void
    onChangeRepulsion: (repulsion: number) => void
    onChangeRepulsionTheta: (repulsionTheta: number) => void
    onChangeDecay: (decay: number) => void
    onPrint: () => void

}

const LINK_SPRING_STEP = 0.1
const LINK_SPRING_MIN = 0
const LINK_SPRING_MAX = 2

const LINK_DISTANCE_STEP = 1
const LINK_DISTANCE_MIN = 0
const LINK_DISTANCE_MAX = 100

const GRAVITY_STEP = 0.01
const GRAVITY_MIN = 0
const GRAVITY_MAX = 1

const FRICTION_STEP = 0.01
const FRICTION_MIN = 0
const FRICTION_MAX = 1

const REPULSION_STEP = 0.01
const REPULSION_MIN = 0
const REPULSION_MAX = 2

const REPULSION_THETA_STEP = 0.01
const REPULSION_THETA_MIN = 0
const REPULSION_THETA_MAX = 2

const DECAY_STEP = 1000
const DECAY_MIN = 1000
const DECAY_MAX = 1000000

/**
 * Card with settings for the tree view.
 */
export function TreeViewSettingsCard({
                                         onPauseAnimation, onRestartAnimation,
                                         resetSimulationConfig,

                                         linkSpring,
                                         linkDistance,
                                         gravity,
                                         friction,
                                         repulsion,
                                         repulsionTheta,
                                         decay,

                                         onChangeLinkSpring,
                                         onChangeLinkDistance,
                                         onChangeGravity,
                                         onChangeFriction,
                                         onChangeRepulsion,
                                         onChangeRepulsionTheta,
                                         onChangeDecay,
                                         onPrint
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

                <Typography gutterBottom>
                    Link Spring
                </Typography>
                <InputSlider value={linkSpring} onChange={onChangeLinkSpring} min={LINK_SPRING_MIN}
                             max={LINK_SPRING_MAX} step={LINK_SPRING_STEP}></InputSlider>

                <Typography gutterBottom>
                    Link Distance
                </Typography>
                <InputSlider value={linkDistance} onChange={onChangeLinkDistance} min={LINK_DISTANCE_MIN}
                             max={LINK_DISTANCE_MAX} step={LINK_DISTANCE_STEP}></InputSlider>

                <Typography gutterBottom>
                    Gravity
                </Typography>
                <InputSlider value={gravity} onChange={onChangeGravity} min={GRAVITY_MIN} max={GRAVITY_MAX}
                             step={GRAVITY_STEP}></InputSlider>

                <Typography gutterBottom>
                    Friction
                </Typography>
                <InputSlider value={friction} onChange={onChangeFriction} min={FRICTION_MIN} max={FRICTION_MAX}
                             step={FRICTION_STEP}></InputSlider>

                <Typography gutterBottom>
                    Repulsion
                </Typography>
                <InputSlider value={repulsion} onChange={onChangeRepulsion} min={REPULSION_MIN} max={REPULSION_MAX}
                             step={REPULSION_STEP}></InputSlider>

                <Typography gutterBottom>
                    Repulsion Theta
                </Typography>
                <InputSlider value={repulsionTheta} onChange={onChangeRepulsionTheta} min={REPULSION_THETA_MIN}
                             max={REPULSION_THETA_MAX} step={REPULSION_THETA_STEP}></InputSlider>

                {/* Decay Doesn't seem to work */}
                {/* <Typography gutterBottom>
                    Decay
                </Typography>
                <InputSlider value={decay} onChange={onChangeDecay} min={DECAY_MIN} max={DECAY_MAX} step={DECAY_STEP}></InputSlider> */}
                <Box sx={{display: 'flex'}}>
                    <Button
                        //small
                        size="small"
                        sx={{
                            flex: 1,
                        }}
                        onClick={() => {
                        }
                        }>
                        Export
                    </Button>
                    <Button
                        sx={{
                            flex: 1,
                        }}
                        onClick={resetSimulationConfig}
                        size="small"
                    >
                        Reset
                    </Button>
                </Box>
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
            <IconButton size="small" onClick={onPrint}>
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