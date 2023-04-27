import {Box, Input, Slider} from "@mui/material"
import React from "react"

interface InputSliderProps {
    value: number
    onChange: (value: number) => void
    min: number
    max: number
    step: number
}

export function InputSlider({value, onChange, min, max, step}: InputSliderProps) {
    return <Box sx={{
        display: 'flex',
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'space-between',
    }}>
        <Slider
            value={value}
            size="small"
            onChange={(event, newValue) => {
                newValue = newValue as number
                if (newValue >= min && newValue <= max)
                    onChange(newValue)
            }}
            aria-labelledby="input-slider"
            min={min}
            max={max}
            step={step}
            sx={{mr: 2}}
        />
        <Input
            value={value}
            size="small"
            onChange={(event) => {
                if (event.target.value !== '') {
                    const newValue = Number(event.target.value)
                    if (newValue >= min && newValue <= max)
                        onChange(newValue)
                }
            }}
            inputProps={{
                step: step,
                min: min,
                max: max,
                type: 'number',
                'aria-labelledby': 'input-slider',
            }}
        />
    </Box>
}