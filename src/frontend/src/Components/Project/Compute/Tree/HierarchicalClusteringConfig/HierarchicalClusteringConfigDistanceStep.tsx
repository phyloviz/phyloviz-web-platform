import Typography from "@mui/material/Typography"
import {FormControl, InputLabel, MenuItem, Select, SelectChangeEvent} from "@mui/material"
import * as React from "react"
import {ReactNode} from "react"
import {DistanceMatrix} from "../../../../../Services/administration/models/getProject/GetProjectOutputModel"

/**
 * Props for the HierarchicalClusteringConfigDistanceStepStep component.
 *
 * @property distances the distances of the project
 * @property selectedDistance the selected distance
 * @property onDistanceChange the function to call when the distance changes
 */
interface HierarchicalClusteringConfigDistanceStepProps {
    distances: DistanceMatrix[]
    selectedDistance: string | null
    onDistanceChange: (event: SelectChangeEvent, child: ReactNode) => void
}


/**
 * Card for the distance step in the HierarchicalClusteringConfig page.
 */
export function HierarchicalClusteringConfigDistanceStep(
    {
        distances,
        selectedDistance,
        onDistanceChange
    }: HierarchicalClusteringConfigDistanceStepProps
) {
    return (
        <>
            <Typography variant="caption" align={"justify"} sx={{mb: 1, width: "100%"}}>
                This list contains only the distances compatible with the current dataset:
            </Typography>
            <FormControl sx={{width: "100%", mb: 1}}>
                <InputLabel id="distance">Distance</InputLabel>
                <Select
                    labelId="distance"
                    label="Distance"
                    value={selectedDistance ?? ""}
                    onChange={onDistanceChange}
                >
                    {distances.map((distance) => (
                        <MenuItem key={distance.distanceMatrixId}
                                  value={distance.distanceMatrixId}>{distance.name}</MenuItem>
                    ))}
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