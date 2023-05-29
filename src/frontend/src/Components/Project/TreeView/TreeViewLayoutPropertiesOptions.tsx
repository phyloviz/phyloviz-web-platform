import {Button, Collapse, Typography} from "@mui/material";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import {InputSlider} from "./InputSlider";
import * as React from "react";
import {SimulationConfig} from "../../../Pages/Project/TreeView/useSimulationConfig";

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

interface TreeViewLayoutPropertiesOptionsProps {
    expanded: boolean
    simulationConfig: SimulationConfig

    onClick: () => void
}

export function TreeViewLayoutPropertiesOptions(
    {
        expanded,
        simulationConfig,

        onClick
    }: TreeViewLayoutPropertiesOptionsProps
) {
    return <>
        <Button
            onClick={() => onClick()}
            size={"small"}
            startIcon={<ExpandMoreIcon color={"inherit"}/>}
        >
            Layout Properties
        </Button>
        <Collapse in={expanded} timeout={"auto"}
                  sx={{border: 1, borderColor: 'divider', borderRadius: 1, p: 3}}
                  unmountOnExit>
            <Typography variant={"body2"}>Link Spring</Typography>
            <InputSlider
                value={simulationConfig.linkSpring}
                onChange={simulationConfig.setLinkSpring}
                min={LINK_SPRING_MIN} max={LINK_SPRING_MAX} step={LINK_SPRING_STEP}/>

            <Typography variant={"body2"}>Link Distance</Typography>
            <InputSlider
                value={simulationConfig.linkDistance}
                onChange={simulationConfig.setLinkDistance}
                min={LINK_DISTANCE_MIN} max={LINK_DISTANCE_MAX} step={LINK_DISTANCE_STEP}/>

            <Typography variant={"body2"}>Gravity</Typography>
            <InputSlider
                value={simulationConfig.gravity}
                onChange={simulationConfig.setGravity}
                min={GRAVITY_MIN} max={GRAVITY_MAX} step={GRAVITY_STEP}/>

            <Typography variant={"body2"}>Friction</Typography>
            <InputSlider
                value={simulationConfig.friction}
                onChange={simulationConfig.setFriction}
                min={FRICTION_MIN} max={FRICTION_MAX} step={FRICTION_STEP}/>

            <Typography variant={"body2"}>Repulsion</Typography>
            <InputSlider
                value={simulationConfig.repulsion}
                onChange={simulationConfig.setRepulsion}
                min={REPULSION_MIN} max={REPULSION_MAX} step={REPULSION_STEP}/>

            <Typography variant={"body2"}>Repulsion Theta</Typography>
            <InputSlider
                value={simulationConfig.repulsionTheta}
                onChange={simulationConfig.setRepulsionTheta}
                min={REPULSION_THETA_MIN} max={REPULSION_THETA_MAX} step={REPULSION_THETA_STEP}/>

            {/* Decay Doesn't seem to work */}
            {/* <Typography variant={"body2"}>Decay</Typography>
        <InputSlider value={decay} onChange={onChangeDecay} min={DECAY_MIN} max={DECAY_MAX} step={DECAY_STEP}></InputSlider> */}
        </Collapse>
    </>
}