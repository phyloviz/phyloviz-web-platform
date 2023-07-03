import {Button, Checkbox, Collapse, FormControlLabel, FormGroup, Typography} from "@mui/material";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import {InputSlider} from "./InputSlider";
import * as React from "react";
import {
    GraphTransformationsConfig
} from "../../../Pages/Project/TreeView/Layouts/ForceDirectedLayout/utils/useGraphTransformationsConfig";

const NODE_SIZE_MIN = 1
const NODE_SIZE_MAX = 20
const NODE_SIZE_STEP = 0.5

const NODE_LABEL_SIZE_MIN = 1
const NODE_LABEL_SIZE_MAX = 100
const NODE_LABEL_SIZE_STEP = 1

interface TreeViewNodePropertiesOptionsProps {
    expanded: boolean
    graphTransformationsConfig: GraphTransformationsConfig

    onClick: () => void
}

export function TreeViewNodePropertiesOptions(
    {
        expanded,
        graphTransformationsConfig,

        onClick
    }: TreeViewNodePropertiesOptionsProps
) {
    return <>
        <Button
            onClick={() => onClick()}
            size={"small"}
            startIcon={<ExpandMoreIcon color={"inherit"}/>}
        >
            Node Properties
        </Button>
        <Collapse in={expanded} timeout={"auto"}
                  sx={{border: 1, borderColor: 'divider', borderRadius: 1, p: 3}}
                  unmountOnExit>
            <Typography variant={"body2"}>Node Size</Typography>
            <InputSlider
                value={graphTransformationsConfig.nodeSize}
                onChange={graphTransformationsConfig.setNodeSize}
                min={NODE_SIZE_MIN} max={NODE_SIZE_MAX} step={NODE_SIZE_STEP}/>

            <FormGroup>
                <FormControlLabel label="Node Label" control={<Checkbox
                    size="small"
                    checked={graphTransformationsConfig.nodeLabel}
                    onChange={(event) => graphTransformationsConfig.setNodeLabel(event.target.checked)}/>}/>
            </FormGroup>

            <Collapse in={graphTransformationsConfig.nodeLabel}>
                <Typography variant={"body2"}>Node Label Size</Typography>
                <InputSlider
                    value={graphTransformationsConfig.nodeLabelSize}
                    onChange={graphTransformationsConfig.setNodeLabelSize}
                    min={NODE_LABEL_SIZE_MIN} max={NODE_LABEL_SIZE_MAX} step={NODE_LABEL_SIZE_STEP}/>
            </Collapse>
        </Collapse>
    </>
}