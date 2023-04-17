import * as React from "react"
import {Slider} from "@mui/material"

/**
 * Card for the level step in the GoeBURSTConfig page.
 */
export function GoeBURSTConfigLevelStep() {
    return <>
        <Slider
            defaultValue={1}
            min={1}
            max={3}
            step={1}
            valueLabelDisplay="auto"
            marks={marks}
        />
    </>
}

const marks = [
    {
        value: 1,
        label: 'SLV',
    },
    {
        value: 2,
        label: 'DLV',
    },
    {
        value: 3,
        label: 'TLV',
    }
]