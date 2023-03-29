import Typography from "@mui/material/Typography";
import {FormControl, InputLabel, MenuItem, Select} from "@mui/material";
import * as React from "react";

enum NeighborJoiningCriteria {
    SAILOU_AND_NEI = "Sailou-Nei",
    STUDIER_AND_KEPPLER = "Studier-Keppler",
}

/**
 * Card for the method step in the NeighborJoiningConfig page.
 */
export function NeighborJoiningConfigMethodStep() {
    return (
        <>
            <Typography variant="caption" align={"justify"} sx={{mb: 1, width: "100%"}}>
                This list contains only the criteria compatible with the current dataset:
            </Typography>
            <FormControl sx={{width: "100%", mb: 1}}>
                <InputLabel id="criteria">Criteria</InputLabel>
                <Select
                    labelId="criteria"
                    //value={}
                    label="Criteria"
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
                    "This method is based on the minimum evolution principle and provides trees with near-minimal sum of branch-length estimates proposed by Saitou and Nei" // TODO change this using the selected criteria
                }
            </Typography>
        </>
    );
}