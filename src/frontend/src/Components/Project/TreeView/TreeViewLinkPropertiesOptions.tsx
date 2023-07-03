import {
    Button,
    Checkbox,
    Collapse,
    FormControl,
    FormControlLabel,
    FormGroup,
    InputLabel,
    MenuItem,
    Select,
    Typography
} from "@mui/material";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import {InputSlider} from "./InputSlider";
import * as React from "react";
import {
    GraphTransformationsConfig
} from "../../../Pages/Project/TreeView/Layouts/ForceDirectedLayout/utils/useGraphTransformationsConfig";

const LINK_WIDTH_MIN = 0.1
const LINK_WIDTH_MAX = 5
const LINK_WIDTH_STEP = 0.1

const LINK_LABEL_SIZE_MIN = 1
const LINK_LABEL_SIZE_MAX = 100
const LINK_LABEL_SIZE_STEP = 1

enum LinkLabelType {
    ABSOLUTE_DISTANCE = "Absolute Distance",
    RELATIVE_DISTANCE = "Relative Distance",
}

interface TreeViewLinkPropertiesOptionsProps {
    expanded: boolean
    graphTransformationsConfig: GraphTransformationsConfig

    onClick: () => void
}

export function TreeViewLinkPropertiesOptions(
    {
        expanded,
        graphTransformationsConfig,

        onClick
    }: TreeViewLinkPropertiesOptionsProps
) {
    return <>
        <Button
            onClick={() => onClick()}
            size={"small"}
            startIcon={<ExpandMoreIcon color={"inherit"}/>}>
            Link Properties
        </Button>
        <Collapse in={expanded} timeout={"auto"}
                  sx={{border: 1, borderColor: 'divider', borderRadius: 1, p: 3}}
                  unmountOnExit>
            <Typography variant={"body2"}>Link Width</Typography>
            <InputSlider
                value={graphTransformationsConfig.linkWidth}
                onChange={graphTransformationsConfig.setLinkWidth}
                min={LINK_WIDTH_MIN} max={LINK_WIDTH_MAX} step={LINK_WIDTH_STEP}/>

            <FormGroup>
                <FormControlLabel label="Link Label" control={<Checkbox
                    size="small"
                    checked={graphTransformationsConfig.linkLabel}
                    onChange={(event) => graphTransformationsConfig.setLinkLabel(event.target.checked)}/>}/>
            </FormGroup>

            <Collapse in={graphTransformationsConfig.linkLabel}>
                <Typography variant={"body2"}>Link Label Size</Typography>
                <InputSlider
                    value={graphTransformationsConfig.linkLabelSize}
                    onChange={graphTransformationsConfig.setLinkLabelSize}
                    min={LINK_LABEL_SIZE_MIN} max={LINK_LABEL_SIZE_MAX} step={LINK_LABEL_SIZE_STEP}/>

                <FormControl sx={{width: "100%", mb: 1, mt: 2}} size="small">
                    <InputLabel id="link-label-type">Link Label Type</InputLabel>
                    <Select
                        labelId="link-label-type"
                        label="Link Label Type"
                        value={graphTransformationsConfig.linkLabelType}
                        onChange={(event) => graphTransformationsConfig.setLinkLabelType(event.target.value)}
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
    </>
}