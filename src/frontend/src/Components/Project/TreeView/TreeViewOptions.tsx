import {Box, Button, Typography} from "@mui/material";
import {InputSlider} from "./InputSlider";
import * as React from "react";

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

interface TreeViewOptionsProps {
    linkSpring: number,
    onChangeLinkSpring: (linkSpring: number) => void
    linkDistance: number,
    onChangeLinkDistance: (linkDistance: number) => void
    gravity: number,
    onChangeGravity: (gravity: number) => void
    friction: number,
    onChangeFriction: (friction: number) => void
    repulsion: number,
    onChangeRepulsion: (repulsion: number) => void,
    repulsionTheta: number,
    onChangeRepulsionTheta: (repulsionTheta: number) => void,
    onExport: () => void,
    resetSimulationConfig: () => void
}

export function TreeViewOptions(
    {
        linkSpring, onChangeLinkSpring,
        linkDistance, onChangeLinkDistance,
        gravity, onChangeGravity,
        friction, onChangeFriction,
        repulsion, onChangeRepulsion,
        repulsionTheta, onChangeRepulsionTheta,
        onExport,
        resetSimulationConfig
    }: TreeViewOptionsProps
) {
    return <Box>
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
        <Box sx={{display: "flex"}}>
            <Button size="small" sx={{flex: 1,}} onClick={onExport}>
                Export
            </Button>
            <Button size="small" sx={{flex: 1,}} onClick={resetSimulationConfig}>
                Reset
            </Button>
        </Box>
    </Box>;
}