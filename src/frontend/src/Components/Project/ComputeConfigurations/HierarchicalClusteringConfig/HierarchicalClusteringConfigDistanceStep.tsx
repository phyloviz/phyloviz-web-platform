import Typography from "@mui/material/Typography"
import {FormControl, InputLabel, Select} from "@mui/material"
import * as React from "react"

/**
 * Card for the distance step in the HierarchicalClusteringConfig page.
 */
export function HierarchicalClusteringConfigDistanceStep() {
    return (
        <>
            <Typography variant="caption" align={"justify"} sx={{mb: 1, width: "100%"}}>
                This list contains only the distances compatible with the current dataset:
            </Typography>
            <FormControl sx={{width: "100%", mb: 1}}>
                <InputLabel id="distance">Distance</InputLabel>
                <Select
                    labelId="distance"
                    //value={}
                    label="Distance"
                >
                </Select>
            </FormControl>

            <Typography display="inline" variant="caption" align={"left"} sx={{width: "100%", whiteSpace: "pre-wrap"}}>
                {
                    "In information theory, the Hamming distance between two strings of equal length is the number of positions at which the corresponding symbols are different. In another way, it measures the minimum number of substitutions required to change one string into the other, or the minimum number of errors that could have transformed one string into the other.\n" +
                    "This distance is used as a measure of genetic distance.\n" +
                    "The Hamming distance is named after Richard W. Hamming"
                }
            </Typography>
        </>
    )
}