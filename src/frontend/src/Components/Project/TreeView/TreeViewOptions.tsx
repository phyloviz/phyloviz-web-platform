import {
    Box,
    Button,
    Checkbox,
    Collapse,
    FormControl,
    FormControlLabel,
    FormGroup,
    FormHelperText,
    InputLabel,
    MenuItem,
    Select,
    SelectChangeEvent,
    Typography
} from "@mui/material";
import {InputSlider} from "./InputSlider";
import * as React from "react";
import {ReactNode} from "react";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";

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

const NODE_SIZE_MIN = 1
const NODE_SIZE_MAX = 20
const NODE_SIZE_STEP = 0.5

const NODE_LABEL_SIZE_MIN = 1
const NODE_LABEL_SIZE_MAX = 100
const NODE_LABEL_SIZE_STEP = 1

const LINK_LENGTH_MIN = 0.1
const LINK_LENGTH_MAX = 5
const LINK_LENGTH_STEP = 0.1

const LINK_LABEL_SIZE_MIN = 1
const LINK_LABEL_SIZE_MAX = 100
const LINK_LABEL_SIZE_STEP = 1

enum LinkLabelType {
    ABSOLUTE_DISTANCE = "Absolute Distance",
    RELATIVE_DISTANCE = "Relative Distance",
}

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

    nodeSize: number
    nodeLabel: boolean
    nodeLabelSize: number
    linkLength: number
    linkLabel: boolean
    linkLabelSize: number
    linkLabelType: string

    onChangeNodeSize: (nodeSize: number) => void
    onChangeNodeLabel: (event: React.ChangeEvent<HTMLInputElement>) => void
    onChangeNodeLabelSize: (nodeLabelSize: number) => void
    onChangeLinkLength: (linkLength: number) => void
    onChangeLinkLabel: (event: React.ChangeEvent<HTMLInputElement>) => void
    onChangeLinkLabelSize: (linkLabelSize: number) => void
    onChangeLinkLabelType: (event: SelectChangeEvent, child: ReactNode) => void

    isolateDataHeaders: string[]

    colorByIsolateData: string
    onChangeColorByIsolateData: (event: SelectChangeEvent, child: ReactNode) => void


    onExport: () => void,
    resetSimulationConfig: () => void
}

// TODO: Organize this Tree View code, separate each section into its own component
// Maybe create multiple hooks or even a context to manage the state of the tree view
export function TreeViewOptions(
    {
        linkSpring, onChangeLinkSpring,
        linkDistance, onChangeLinkDistance,
        gravity, onChangeGravity,
        friction, onChangeFriction,
        repulsion, onChangeRepulsion,
        repulsionTheta, onChangeRepulsionTheta,

        nodeSize,
        nodeLabel,
        nodeLabelSize,
        linkLength,
        linkLabel,
        linkLabelSize,
        linkLabelType,

        onChangeNodeSize,
        onChangeNodeLabel,
        onChangeNodeLabelSize,
        onChangeLinkLength,
        onChangeLinkLabel,
        onChangeLinkLabelSize,
        onChangeLinkLabelType,

        isolateDataHeaders,

        colorByIsolateData,
        onChangeColorByIsolateData,

        onExport,
        resetSimulationConfig
    }: TreeViewOptionsProps
) {
    const [layoutPropertiesExpanded, setLayoutPropertiesExpanded] = React.useState(false)
    const [nodePropertiesExpanded, setNodePropertiesExpanded] = React.useState(false)
    const [linkPropertiesExpanded, setLinkPropertiesExpanded] = React.useState(false)
    const [assignColorsExpanded, setAssignColorsExpanded] = React.useState(false)

    return <Box sx={{
        display: "flex",
        flexDirection: "column",
        justifyContent: "space-between",
    }}>
        <Button
            onClick={() => {
                setLayoutPropertiesExpanded((prev) => !prev)
                setNodePropertiesExpanded(false)
                setAssignColorsExpanded(false)
                setLinkPropertiesExpanded(false)
            }}
            size={"small"}
            startIcon={<ExpandMoreIcon color={"inherit"}/>}
        >
            Layout Properties
        </Button>
        <Collapse in={layoutPropertiesExpanded} timeout={"auto"}
                  sx={{border: 1, borderColor: 'divider', borderRadius: 1, p: 3}}
                  unmountOnExit>
            <Typography variant={"body2"}>Link Spring</Typography>
            <InputSlider
                value={linkSpring}
                onChange={onChangeLinkSpring}
                min={LINK_SPRING_MIN} max={LINK_SPRING_MAX} step={LINK_SPRING_STEP}
            />

            <Typography variant={"body2"}>Link Distance</Typography>
            <InputSlider
                value={linkDistance}
                onChange={onChangeLinkDistance}
                min={LINK_DISTANCE_MIN} max={LINK_DISTANCE_MAX} step={LINK_DISTANCE_STEP}
            />

            <Typography variant={"body2"}>Gravity</Typography>
            <InputSlider
                value={gravity}
                onChange={onChangeGravity}
                min={GRAVITY_MIN} max={GRAVITY_MAX} step={GRAVITY_STEP}
            />

            <Typography variant={"body2"}>Friction</Typography>
            < InputSlider
                value={friction}
                onChange={onChangeFriction}
                min={FRICTION_MIN} max={FRICTION_MAX} step={FRICTION_STEP}
            />

            <Typography variant={"body2"}>Repulsion</Typography>
            <InputSlider
                value={repulsion}
                onChange={onChangeRepulsion}
                min={REPULSION_MIN} max={REPULSION_MAX} step={REPULSION_STEP}
            />

            <Typography variant={"body2"}>Repulsion Theta</Typography>
            <InputSlider
                value={repulsionTheta}
                onChange={onChangeRepulsionTheta}
                min={REPULSION_THETA_MIN} max={REPULSION_THETA_MAX} step={REPULSION_THETA_STEP}
            />

            {/* Decay Doesn't seem to work */}
            {/* <Typography variant={"body2"}>Decay</Typography>
            <InputSlider value={decay} onChange={onChangeDecay} min={DECAY_MIN} max={DECAY_MAX} step={DECAY_STEP}></InputSlider> */}
        </Collapse>

        <Button
            onClick={() => {
                setNodePropertiesExpanded((prev) => !prev)
                setLayoutPropertiesExpanded(false)
                setAssignColorsExpanded(false)
                setLinkPropertiesExpanded(false)
            }}
            size={"small"}
            startIcon={<ExpandMoreIcon color={"inherit"}/>
            }>
            Node Properties
        </Button>
        <Collapse in={nodePropertiesExpanded} timeout={"auto"}
                  sx={{border: 1, borderColor: 'divider', borderRadius: 1, p: 3}}
                  unmountOnExit>
            <Typography variant={"body2"}>Node Size</Typography>
            <InputSlider
                value={nodeSize}
                onChange={onChangeNodeSize}
                min={NODE_SIZE_MIN} max={NODE_SIZE_MAX} step={NODE_SIZE_STEP}
            />

            <FormGroup>
                <FormControlLabel label="Node Label" control={
                    <Checkbox
                        size="small"
                        checked={nodeLabel}
                        onChange={onChangeNodeLabel}
                    />
                }/>
            </FormGroup>

            <Collapse in={nodeLabel}>
                <Typography variant={"body2"}>Node Label Size</Typography>
                <InputSlider
                    value={nodeLabelSize}
                    onChange={onChangeNodeLabelSize}
                    min={NODE_LABEL_SIZE_MIN} max={NODE_LABEL_SIZE_MAX} step={NODE_LABEL_SIZE_STEP}
                />
            </Collapse>
        </Collapse>

        <Button
            onClick={() => {
                setLinkPropertiesExpanded((prev) => !prev)
                setLayoutPropertiesExpanded(false)
                setAssignColorsExpanded(false)
                setNodePropertiesExpanded(false)
            }}
            size={"small"}
            startIcon={<ExpandMoreIcon color={"inherit"}/>
            }>
            Link Properties
        </Button>
        <Collapse in={linkPropertiesExpanded} timeout={"auto"}
                  sx={{border: 1, borderColor: 'divider', borderRadius: 1, p: 3}}
                  unmountOnExit>
            <Typography variant={"body2"}>Link Length</Typography>
            <InputSlider
                value={linkLength}
                onChange={onChangeLinkLength}
                min={LINK_LENGTH_MIN} max={LINK_LENGTH_MAX} step={LINK_LENGTH_STEP}
            />

            <FormGroup>
                <FormControlLabel label="Link Label" control={
                    <Checkbox
                        size="small"
                        checked={linkLabel}
                        onChange={onChangeLinkLabel}
                    />
                }/>
            </FormGroup>

            <Collapse in={linkLabel}>
                <Typography variant={"body2"}>Link Label Size</Typography>
                <InputSlider
                    value={linkLabelSize}
                    onChange={onChangeLinkLabelSize}
                    min={LINK_LABEL_SIZE_MIN} max={LINK_LABEL_SIZE_MAX} step={LINK_LABEL_SIZE_STEP}
                />

                <FormControl sx={{width: "100%", mb: 1, mt: 2}} size="small">
                    <InputLabel id="link-label-type">Link Label Type</InputLabel>
                    <Select
                        labelId="link-label-type"
                        label="Link Label Type"
                        value={linkLabelType}
                        onChange={onChangeLinkLabelType}
                        MenuProps={{PaperProps: {sx: {maxHeight: 150}}}}
                    >
                        {Object.values(LinkLabelType).map((value) => (
                            <MenuItem key={value} value={value}>
                                {value}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>
            </Collapse>
        </Collapse>

        <Button
            onClick={() => {
                setAssignColorsExpanded((prev) => !prev)
                setLinkPropertiesExpanded(false)
                setLayoutPropertiesExpanded(false)
                setNodePropertiesExpanded(false)
            }}
            size={"small"}
            startIcon={<ExpandMoreIcon color={"inherit"}/>
            }>
            Assign Colors
        </Button>
        <Collapse in={assignColorsExpanded} timeout={"auto"}
                  sx={{border: 1, borderColor: 'divider', borderRadius: 1, p: 3}}
                  unmountOnExit>
            <FormControl sx={{width: "100%", mb: 1, mt: 2}} size="small">
                <InputLabel id="color-by-profile">Color by Profile</InputLabel>
                <Select
                    labelId="color-by-profile"
                    label="Color by Profile"
                    disabled={true} // TODO: Implement
                    // value={colorByProfile}
                    // onChange={onChangeColorByProfile}
                    MenuProps={{PaperProps: {sx: {maxHeight: 150}}}}
                >
                    {/*{profiles != null && profiles.map((value) => (*/}
                    {/*    <MenuItem key={value} value={value}>*/}
                    {/*        {value}*/}
                    {/*    </MenuItem>*/}
                    {/*))}*/}
                </Select>
                <FormHelperText>Not implemented yet.</FormHelperText>
            </FormControl>
            <FormControl sx={{width: "100%", mb: 1, mt: 2}} size="small">
                <InputLabel id="color-by-isolate-data">Color by Isolate Data</InputLabel>
                <Select
                    labelId="color-by-isolate-data"
                    label="Color by Isolate Data"
                    value={colorByIsolateData}
                    disabled={isolateDataHeaders.length === 0}
                    onChange={onChangeColorByIsolateData}
                    MenuProps={{PaperProps: {sx: {maxHeight: 150}}}}
                >
                    {isolateDataHeaders.map((value) => (
                        <MenuItem key={value} value={value}>
                            {value}
                        </MenuItem>
                    ))}
                </Select>
                {
                    isolateDataHeaders.length === 0 &&
                    <FormHelperText>Can't select. Isolate Data not Indexed</FormHelperText>
                }
            </FormControl>
        </Collapse>

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