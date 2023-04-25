import Typography from "@mui/material/Typography"
import {FormControl, InputLabel, MenuItem, Select, SelectChangeEvent} from "@mui/material"
import * as React from "react"
import {
    NeighborJoiningCriteria
} from "../../../../../Pages/Project/Compute/Tree/NeighborJoiningConfig/useNeighborJoiningConfig"

/**
 * Props of the NeighborJoiningConfigMethodStep.
 *
 * @property selectedCriteria selected criteria
 * @property onCriteriaChange callback to handle the criteria change
 */
interface NeighborJoiningConfigMethodStepProps {
    selectedCriteria: NeighborJoiningCriteria | null
    onCriteriaChange: (event: SelectChangeEvent) => void
}

/**
 * Card for the method step in the NeighborJoiningConfig page.
 */
export function NeighborJoiningConfigMethodStep(
    {
        selectedCriteria,
        onCriteriaChange
    }: NeighborJoiningConfigMethodStepProps
) {

    return (
        <>
            <Typography variant="caption" align={"justify"} sx={{mb: 1, width: "100%"}}>
                This list contains only the criteria compatible with the current dataset:
            </Typography>
            <FormControl sx={{width: "100%", mb: 1}}>
                <InputLabel id="criteria">Criteria</InputLabel>
                <Select
                    labelId="criteria"
                    label="Criteria"
                    value={selectedCriteria ?? ""}
                    onChange={onCriteriaChange}
                    MenuProps={{PaperProps: {sx: {maxHeight: 150}}}}
                >
                    {
                        Object.values(NeighborJoiningCriteria).map((criteria) => (
                            <MenuItem key={criteria} value={criteria}>{criteria}</MenuItem>
                        ))
                    }
                </Select>
            </FormControl>

            <Typography display="inline" variant="caption" align={"left"} sx={{width: "100%", whiteSpace: "pre-wrap"}}>
                {
                    "The Neighbor-Joining algorithm\n" +
                    `This method is based on the minimum evolution principle and provides trees with near-minimal sum of branch-length estimates proposed by ${selectedCriteria == NeighborJoiningCriteria.SAILOU_AND_NEI ? "Sailou and Nei" : "Studier and Keppler"}.`
                }
            </Typography>
        </>
    )
}